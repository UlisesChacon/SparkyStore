/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkystore.modelo;

import java.io.Serializable;

public abstract class Producto implements IImpuesto, Serializable {
    protected int id;
    protected String nombre;
    protected double precioBase;
    protected int stock;

    public Producto(int id, String nombre, double precioBase, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.stock = stock;
    }

    // --- MÉTODOS DE NEGOCIO ---
    
    // Método abstracto: Obliga a los hijos a decir a qué categoría pertenecen
    public abstract String getCategoria();
    
    // Método abstracto: Cada hijo decide cómo mostrar sus detalles
    public abstract String mostrarDetalle();

    // Método para restar stock de forma segura (Boolean)
    public boolean restarStock(int cantidad) {
        if (cantidad <= this.stock) {
            this.stock -= cantidad;
            return true; // Éxito
        }
        return false; // Error: No hay suficiente stock
    }

    // Implementación de IImpuesto
    @Override
    public double calcularIGV(double precio) {
        return precio * IGV;
    }

    // --- GETTERS Y SETTERS BÁSICOS ---
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecioBase() { return precioBase; }
    public int getStock() { return stock; }
    
    // Necesarios para el CRUD (Editar)
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecioBase(double precioBase) { this.precioBase = precioBase; }
    public void setStock(int stock) { this.stock = stock; }
}
