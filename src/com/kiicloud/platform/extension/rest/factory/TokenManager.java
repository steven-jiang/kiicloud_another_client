package com.kiicloud.platform.extension.rest.factory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.kiicloud.platform.extension.rest.commons.StringTemplate;
import com.kiicloud.platform.extension.rest.entity.ScopeType;

@Component
public class TokenManager {

	private final Logger log = LoggerFactory.getLogger(TokenManager.class);

	private final ThreadLocal<TokenInfo> featureLoc = new ThreadLocal<TokenInfo>();

	private final ConcurrentMap<String, TokenInfo> tokenCache = new ConcurrentHashMap<String, TokenInfo>();

	private TokenInfo adminToken;

	static class TokenInfo {

		String sign;

		String token;

		String type;

		ScopeType scope;

		public TokenInfo(String token) {
			this.token = token;
			this.scope = ScopeType.USER;
			this.type = "Bearer";
			this.sign = "me";
		}

		public TokenInfo(Map<String, String> result) {
			token = result.get("access_token");

			type = result.get("token_type");
		}

		public TokenInfo(TokenInfo info) {
			this.sign = info.sign;
			this.token = info.token;
			this.type = info.type;
			this.scope = info.scope;
		}

	}

	@Qualifier("nologinTarget")
	@Autowired
	private WebTarget appTarget;

	@Autowired
	private AppConfigStore factory;

	private void initAdminToken() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("client_id", factory.getAppInfo().clientID);
		param.put("client_secret", factory.getAppInfo().clientKey);

		adminToken = getToken(param, factory.getAppInfo().clientID);

		adminToken.scope = ScopeType.APP;
		adminToken.sign = "";

		this.featureLoc.set(adminToken);
	}

	public void logout() {
		featureLoc.remove();
	}

	public void login(String userName, String pwd) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("username", userName);
		param.put("password", pwd);
		TokenInfo info = getToken(param, userName);

		info.sign = "me";
		info.scope = ScopeType.USER;
		featureLoc.set(info);
	}

	public void su() {
		if (adminToken == null) {
			initAdminToken();
		}
	}

	public void setTokenDirect(String token) {
		TokenInfo info = new TokenInfo(token);
		this.featureLoc.set(info);
	}

	public void sudo(String userName) {
		// "EMAIL", "PHONE" or "LOGIN_NAME".
		TokenInfo token = featureLoc.get();
		if (token == null) {
			token = new TokenInfo(adminToken);
		}
		token.sign = "LOGIN_NAME:" + userName;

		featureLoc.set(token);
	}

	public void exitSudo() {
		TokenInfo token = featureLoc.get();
		if (token.scope == ScopeType.USER) {
			token.sign = "me";
		} else {
			token.sign = "";
		}

		featureLoc.set(token);
	}

	private TokenInfo getToken(Map<String, String> param, String userKey) {

		if (tokenCache.containsKey(userKey)) {
			return tokenCache.get(userKey);
		}

		Map<String, String> result = appTarget.path("oauth2/token")
				.register(MetaTypeFeature.getKiiFeature("OauthTokenRequest"))
				.request().post(Entity.json(param)).readEntity(Map.class);

		TokenInfo info = new TokenInfo(result);

		tokenCache.put(userKey, info);

		return info;
	}

	public TokenInfo getToken() {
		return featureLoc.get();
	}

	
	public ClientRequestFilter getFilter(){
		return new TokenFilter();
	}
	
	private class TokenFilter implements ClientRequestFilter {

		@Override
		public void filter(ClientRequestContext requestContext)
				throws IOException {

			TokenInfo token = getToken();

			if (token == null) {
				return;
			}

			Map<String, String> params = new HashMap<String, String>();
			if (token != null) {
				params.put("USER_SIGN", token.sign);
			} else {
				params.put("USER_SIGN", "");
			}
			URI uri = requestContext.getUri();

			String fullUrl = StringTemplate.generUrl(uri.toString(), params);
			log.info("full url:" + fullUrl);
			try {

				URI newUri = new URI(fullUrl);
				requestContext.setUri(newUri);

			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			if (token != null) {
				requestContext.getHeaders().add("Authorization",
						token.type + " " + token.token);
			}
		}
	}

}