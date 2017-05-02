package com.inj0hn.kafka;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;

public class SimpleConsumer {
	
	private static Logger LOG = LoggerFactory.getLogger(SimpleConsumer.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws Exception {
		String topic = System.getProperty("topic");
		String configFile = System.getProperty("configFile");
		System.out.println("Topic: " + topic);
		System.out.println("Config file: " + configFile);
		Properties props = PropertiesLoaderUtils
				.loadProperties(new FileSystemResource(configFile));
		
		ContainerProperties containerProperties = new ContainerProperties(topic);
		containerProperties.setMessageListener(new MessageListener() {

			public void onMessage(Object value) {
				LOG.info("Payload: {}", value);
			}
		});
		KafkaMessageListenerContainer container = new KafkaMessageListenerContainer(
				new DefaultKafkaConsumerFactory(props), containerProperties);
		container.setBeanName("testListenerContainer");
		container.start();
		
		
	}

}
