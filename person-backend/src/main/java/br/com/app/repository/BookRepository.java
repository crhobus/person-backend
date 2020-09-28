package br.com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.data.model.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
