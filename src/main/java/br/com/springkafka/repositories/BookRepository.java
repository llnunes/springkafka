package br.com.springkafka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springkafka.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String>{

}
