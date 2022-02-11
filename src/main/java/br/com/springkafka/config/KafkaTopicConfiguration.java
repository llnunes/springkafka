package br.com.springkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfiguration {
	
	@Value("${spring.kafka.bootstrap-servers}")	
	private String bootstrapAddress;	
	
	@Value("${topic.name}")
	private String topic;
	
	@Bean
	public NewTopic createTopic() {
		return new NewTopic(topic, 1, (short)1);
	}
	
//	@Bean
//	public ProducerFactory<String, People> producerFactory() {
//		Map<String, Object> config = new HashMap<>();
//		config.put(ProducerConfig.R, bootstrapAddress);
//		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
//		return new DefaultKafkaProducerFactory<>(config);
//	}
//	
//	@Bean
//	public KafkaTemplate<String, People> kafkaTemplate() {
//		return new KafkaTemplate<>(producerFactory());
//	}
	
}
