package com.tienda_libros.view;

import com.tienda_libros.model.Libro;
import com.tienda_libros.service.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class LibroForm extends JFrame {
    LibroServicio libroServicio;
    private JPanel panel;
    private JTable tablaLibros;
    private JTextField idTexto;
    private JTextField libroTexto;
    private JTextField autorTexto;
    private JTextField precioTexto;
    private JTextField existenciasTexto;
    private JButton agregarButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private DefaultTableModel tablaModeloLibros;

    @Autowired
    public LibroForm(LibroServicio libroServicio){
        this.libroServicio = libroServicio;
        iniciarForma();
        agregarButton.addActionListener(e -> agregarLibro());
        tablaLibros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarLibroSeleccionado();
            }
        });
        modificarButton.addActionListener(e -> modificarLibro());
    }

    private void iniciarForma(){
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(800,600);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension tamanioPantalla = toolkit.getScreenSize();
        int x = ((tamanioPantalla.width - getWidth()) / 2);
        int y = ((tamanioPantalla.height - getHeight()) / 2);
        setLocation(x,y);
    }

    private void agregarLibro(){
        //Leer los valores del formulario
        if(libroTexto.getText().equals("")){
            mostrarMensaje("Proporciona el nombre del libro");
            libroTexto.requestFocusInWindow();
            return;
        }
        var nombreLibro = libroTexto.getText();
        var autor = autorTexto.getText();
        var precio = Double.parseDouble(precioTexto.getText());
        var existencias = Integer.parseInt(existenciasTexto.getText());

        // Creamos el objeto libro;
        var libro = new Libro(null, nombreLibro,autor,precio,existencias);

        this.libroServicio.guardarLibro(libro);
        mostrarMensaje("Se agrego el libro");

        //Función para limpiar el formulario
        limpiarFormulario();

        // Recargar la latbla
        listarLibros();
    }

    private void cargarLibroSeleccionado(){
        // Los índices de las columnas de las tablas inician en cero
        var reglon = tablaLibros.getSelectedRow();
        if(reglon != 1){  // Regresa -1 si no se seleccionó ningún registro
            // Llenamos la información de los demás
            String idLibro = tablaLibros.getModel().getValueAt(reglon,0).toString();
            idTexto.setText(idLibro);
            String nombreLibro = tablaLibros.getModel().getValueAt(reglon,1).toString();
            libroTexto.setText(nombreLibro);
            String autor = tablaLibros.getModel().getValueAt(reglon,2).toString();
            autorTexto.setText(autor);
            String precio = tablaLibros.getModel().getValueAt(reglon,3).toString();
            precioTexto.setText(precio);
            String existentes = tablaLibros.getModel().getValueAt(reglon,4).toString();
            existenciasTexto.setText(existentes);
        }
    }

    private void modificarLibro(){
        if(this.idTexto.getText().equals("")){
            mostrarMensaje("Debe seleccionar un registro de la tabla...");
        }else{
            // Se verifica que el nombre del libro no sea nulo o null
            if(libroTexto.getText().equals("")){
                mostrarMensaje("Proporciona el nombre del libro...");
                libroTexto.requestFocusInWindow();
                return;
            }else{
                // Llenamos el objeto del libro actualizar
                int idLibro = Integer.parseInt(idTexto.getText());
                var nombre = libroTexto.getText();
                var autor = autorTexto.getText();
                var precio = Double.parseDouble(precioTexto.getText());
                var existencias = Integer.parseInt(existenciasTexto.getText());
                var libro = new Libro(idLibro,nombre,autor,precio,existencias);

                libroServicio.guardarLibro(libro);
                mostrarMensaje("Se modifico correctamente los datos del Libro");
                limpiarFormulario();
                listarLibros();
            }
        }
    }

    private void limpiarFormulario(){
        libroTexto.setText("");
        autorTexto.setText("");
        precioTexto.setText("");
        existenciasTexto.setText("");

    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this,mensaje);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        // Creamos el elemento idTexto Oculto
        idTexto = new JTextField("");
        idTexto.setVisible(false);

        this.tablaModeloLibros = new DefaultTableModel(0,5);
        String[] cabeceros = {"ID","LIBRO","AUTOR","PRECIO","EXISTENCIAS"};
        this.tablaModeloLibros.setColumnIdentifiers(cabeceros);

        //Se crea una instancia el objeto de JTable
        this.tablaLibros = new JTable(tablaModeloLibros);
        listarLibros();
    }

    private void listarLibros(){
        // Limpiar la tabla
        tablaModeloLibros.setRowCount(0);

        // Obtener los libros
        var libros = libroServicio.listarLibro();
        libros.forEach((libro) -> {
            Object[] renglonLibro = {
                    libro.getIdLibro(),
                    libro.getNombreLibro(),
                    libro.getAutor(),
                    libro.getPrecio(),
                    libro.getExistencias()
            };

            this.tablaModeloLibros.addRow(renglonLibro);
        });
    }
}
