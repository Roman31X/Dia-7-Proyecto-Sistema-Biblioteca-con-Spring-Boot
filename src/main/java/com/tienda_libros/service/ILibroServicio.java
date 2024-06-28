package com.tienda_libros.service;

import com.tienda_libros.model.Libro;

import java.util.List;

public interface ILibroServicio {

    public List<Libro> listarLibro();

    public Libro buscraLibroPorId(Integer idLibro);

    public void guardarLibro(Libro libro);

    public void eliminarLibro(Libro libro);

}
