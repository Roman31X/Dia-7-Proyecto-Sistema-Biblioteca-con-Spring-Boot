package com.tienda_libros.repository;

import com.tienda_libros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepositorio extends JpaRepository<Libro,Integer> {
}
