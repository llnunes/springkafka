package br.com.springkafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import br.com.springkafka.People;

@Component
public class PeopleProducer {	
	
	private static final Logger log = LoggerFactory.getLogger(PeopleProducer.class);
	
	@Value("${topic.name}")
	private String topic;
	
	@Autowired
	private KafkaTemplate<String, People> kafkaTemplate;
	
//	public PeopleProducer(@Value("${topic.name}") String topic, KafkaTemplate<String, People> kafkaTemplate) {
//		super();
//		this.topic = topic;
//		this.kafkaTemplate = kafkaTemplate;
//	}

	public void sendMessage (People people) {
		kafkaTemplate.send(topic, people).addCallback(
				success -> log.info("Mensage send: " + success.getProducerRecord().value()),
				failure -> log.info("Mensage failure: " + failure.getMessage())
		);
	}
}
