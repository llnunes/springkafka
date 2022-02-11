package br.com.springkafka.controllers;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springkafka.People;
import br.com.springkafka.dtos.PeopleDTO;
import br.com.springkafka.producer.PeopleProducer;

@RestController
@RequestMapping(value = "/peoples")
public class PeopleController {

	@Autowired
	private PeopleProducer producer;
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody PeopleDTO dto) {
		var id = UUID.randomUUID().toString();
		var message = People.newBuilder()
				.setId(id)
				.setName(dto.getName())
				.setCpf(dto.getCpf())
				.setBooks(dto.getBooks().stream().map(b -> (CharSequence) b).collect(Collectors.toList()))
				.build();
		producer.sendMessage(message);		

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
