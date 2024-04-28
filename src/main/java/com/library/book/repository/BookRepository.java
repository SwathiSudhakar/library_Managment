package com.library.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.book.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	Optional<List<Book>> findByBookName(String rn);

    //Optional<Room> findByIdAndAvailableTrue(Long id);
}