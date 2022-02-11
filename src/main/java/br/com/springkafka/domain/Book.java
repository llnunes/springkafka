package br.com.springkafka.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Builder;

@Builder
@Entity
public class Book {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "people_id")
	private People people;
	
	public Book() {
	}
	
	public Book(String id, String name, People people) {
		super();
		this.id = id;
		this.name = name;
		this.people = people;
	}
	
	public Book(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Book(CharSequence dname, People people) {
		this.name = dname != null ? dname.toString() : null;
		this.people = people;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
