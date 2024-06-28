package com.tienda_libros.view;

import com.tienda_libros.service.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Component
public class LibroForm extends JFrame {
    LibroServicio libroServicio;
    private JPanel panel;
    private JTable tablaLibros;
    private DefaultTableModel tablaModelLibros;

    @Autowired
    public LibroForm(LibroServicio libroServicio){
        this.libroServicio = libroServicio;
        iniciarForma();
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.tablaModelLibros = new DefaultTableModel(0,5);
        String[] cabeceros = {"ID","LIBRO","AUTOR","PRECIO","EXISTENCIAS"};
        this.tablaModelLibros.setColumnIdentifiers(cabeceros);

        //Se crea una instancia el objeto de JTable
        this.tablaLibros = new JTable(tablaModelLibros);
    }
}
