package com.kiicloud.platform.extension.rest.factory;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import org.glassfish.jersey.CommonProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

@Component
public class KiiJacksonFeature implements Feature {

	@Autowired
	private ObjectMapperContextResolver resolver;
	
	
	private final Logger log=LoggerFactory.getLogger(KiiJacksonFeature.class);
	
	public KiiJacksonFeature(){
		log.info("construct kii jackson feature");
	}
	
	@PostConstruct
	public void init(){
		log.info("init kii jackson feature");
	}
	
	@Override
	public boolean configure(final FeatureContext context) {
		final String disableMoxy = CommonProperties.MOXY_JSON_FEATURE_DISABLE
				+ '.'
				+ context.getConfiguration().getRuntimeType().name()
						.toLowerCase();
		context.property(disableMoxy, true);

		context.register(resolver);
		context.register(JacksonJaxbJsonProvider.class, MessageBodyReader.class,
				MessageBodyWriter.class);
		return true;
	}

}
