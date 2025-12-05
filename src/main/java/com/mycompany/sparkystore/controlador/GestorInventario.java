/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkystore.controlador;

import com.mycompany.sparkystore.modelo.Producto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; // Necesario para filtrar listas

public class GestorInventario {

    private ArrayList<Producto> listaProductos;
    private final String ARCHIVO = "inventario.dat";

    public GestorInventario() {
        this.listaProductos = new ArrayList<>();
        cargarInventario();
    }

    // --- 1. PERSISTENCIA (Guardar y Cargar) ---
    
    public void guardarInventario() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(listaProductos);
            // System.out.println("--> Datos guardados en disco."); // Opcional: para depurar
        } catch (IOException e) {
            System.err.println("Error crítico al guardar: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void cargarInventario() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            listaProductos = (ArrayList<Producto>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("--> Iniciando sistema nuevo (Archivo no existe aún).");
            listaProductos = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar inventario: " + e.getMessage());
            listaProductos = new ArrayList<>();
        }
    }

    // --- 2. MÉTODOS CRUD (Crear, Leer, Eliminar) ---

    public void agregarProducto(Producto nuevoProducto) {
        listaProductos.add(nuevoProducto);
        guardarInventario(); // Guardado automático
    }

    public List<Producto> listarProductos() {
        return listaProductos;
    }

    public Producto buscarProducto(int id) {
        for (Producto p : listaProductos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null; // No encontrado
    }

    public boolean eliminarProducto(int id) {
        Producto p = buscarProducto(id);
        if (p != null) {
            listaProductos.remove(p);
            guardarInventario(); // Actualizamos el archivo tras borrar
            return true;
        }
        return false;
    }

    // --- 3. MÉTODOS DE NEGOCIO (Para el Menú y Ventas) ---

    /**
     * Filtra la lista completa y devuelve solo los de la categoría pedida.
     * @param categoria Ej: "Computo", "Suministros", "Software"
     */
    public List<Producto> listarPorCategoria(String categoria) {
        // Usamos Streams de Java 8+ para filtrar rápido
        return listaProductos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    /**
     * Valida si hay suficiente stock sin restarlo todavía.
     */
    public boolean validarStock(int idProducto, int cantidadRequerida) {
        Producto p = buscarProducto(idProducto);
        if (p != null) {
            return p.getStock() >= cantidadRequerida;
        }
        return false;
    }
}