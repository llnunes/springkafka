package br.com.springkafka.consumer;

import java.util.stream.Collectors;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import br.com.springkafka.People;
import br.com.springkafka.domain.Book;
import br.com.springkafka.repositories.PeopleRepository;

@Component
public class PeopleConsumer {

	private static final Logger log = LoggerFactory.getLogger(PeopleConsumer.class);

	@Autowired
	private PeopleRepository repository;

	@KafkaListener(topics = "${topic.name}")
	public void consumer(ConsumerRecord<String, People> record, Acknowledgment ack) {
		log.info("Received Message - Partition: " + record.partition());
		log.info("Received Message - Value: " + record.value());

		var people = record.value();

		var entity = br.com.springkafka.domain.People.builder().build();
		entity.setId(people.getId().toString());
		entity.setName(people.getName().toString());
		entity.setCpf(people.getCpf().toString());
		entity.setBooks(people.getBooks().stream()
				.map(d -> new Book(d, entity)).collect(Collectors.toList()));
		
		repository.save(entity);

		ack.acknowledge();
	}
}
