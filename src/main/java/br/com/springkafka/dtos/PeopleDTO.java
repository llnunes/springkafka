package br.com.springkafka.dtos;

import java.io.Serializable;
import java.util.List;

public class PeopleDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String cpf;
	
	private List<String> books;

	public PeopleDTO() {
	}
	
	public PeopleDTO(String name, String cpf, List<String> books) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.books = books;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<String> getBooks() {
		return books;
	}
}
