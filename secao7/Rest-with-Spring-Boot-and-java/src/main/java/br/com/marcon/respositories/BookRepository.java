package br.com.marcon.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.marcon.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
}
