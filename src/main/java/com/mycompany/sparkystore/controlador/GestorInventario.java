/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkystore.controlador;

/**
 *
 * @author USULISES
 */
import com.mycompany.sparkystore.modelo.Producto;
import java.io.*;
import java.util.ArrayList;

// Asumimos que tus clases modelo están en un paquete 'modelo'
// import modelo.Producto; 

public class GestorInventario {

    // 1. La "Base de Datos" en memoria (Polimorfismo puro: guarda Laptops y Mouses)
    private ArrayList<Producto> listaProductos;
    
    // 2. La ruta del archivo binario
    private final String ARCHIVO = "inventario.dat";

    // CONSTRUCTOR
    public GestorInventario() {
        this.listaProductos = new ArrayList<>();
        cargarInventario(); // Al instanciar la clase, recuperamos los datos del archivo
    }

    /**
     * MÉTODO 1: GUARDAR (Serialización)
     * Escribe la lista completa en el disco duro.
     * Se debe llamar cada vez que agregamos o modificamos un producto.
     */
    public void guardarInventario() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(listaProductos);
            System.out.println("--> Datos guardados correctamente en " + ARCHIVO);
        } catch (IOException e) {
            System.err.println("Error al guardar el inventario: " + e.getMessage());
        }
    }

    /**
     * MÉTODO 2: CARGAR (Deserialización)
     * Lee el archivo binario y reconstruye los objetos en memoria.
     */
    @SuppressWarnings("unchecked")
    private void cargarInventario() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            listaProductos = (ArrayList<Producto>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Es normal la primera vez, ya que el archivo no existe.
            System.out.println("--> Archivo de inventario no encontrado. Se creará uno nuevo al guardar.");
            listaProductos = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar inventario: " + e.getMessage());
        }
    }

    /**
     * MÉTODO 3: AGREGAR PRODUCTO
     * Añade al ArrayList y actualiza el archivo binario.
     */
    public void agregarProducto(Producto nuevoProducto) {
        listaProductos.add(nuevoProducto);
        guardarInventario(); // Persistencia inmediata
    }

    /**
     * MÉTODO 4: LISTAR
     * Muestra todos los productos usando el toString() de cada clase hija.
     */
    public void mostrarInventario() {
        if (listaProductos.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }
        
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        for (Producto p : listaProductos) {
            System.out.println(p.toString()); // Polimorfismo en acción
        }
    }

    /**
     * MÉTODO 5: BUSCAR POR ID
     * Útil para cuando el cliente selecciona un producto para comprar.
     */
    public Producto buscarProductoPorId(int id) {
        for (Producto p : listaProductos) {
            // Asumimos que tu clase Producto tiene un método getId()
            if (p.getId() == id) {
                return p;
            }
        }
        return null; // No encontrado
    }

    /**
     * MÉTODO 6: VALIDAR Y DESCONTAR STOCK
     * Regla de Negocio: Validar stock antes de agregar al carrito.
     */
    public boolean disminuirStock(int idProducto, int cantidadRequerida) {
        Producto p = buscarProductoPorId(idProducto);
        
        if (p != null) {
            if (p.getStock() >= cantidadRequerida) {
                p.setStock(p.getStock() - cantidadRequerida);
                guardarInventario(); // Guardamos el nuevo stock en el archivo
                return true;
            } else {
                System.out.println("Error: Stock insuficiente (Disponible: " + p.getStock() + ")");
                return false;
            }
        }
        return false;
    }
    
    // Getter para obtener la lista si fuera necesario externamente
    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }
}