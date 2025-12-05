/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkystore.modelo;

public class Tinta extends Suministro {
    private String color;

    public Tinta(int id, String nombre, double precio, int stock, String marcaCompatible, String color) {
        super(id, nombre, precio, stock, marcaCompatible);
        this.color = color;
    }

    @Override
    public String getCategoria() { return "Suministros"; }

    @Override
    public String mostrarDetalle() {
        return "TINTA: " + nombre + 
               " | Precio: S/" + precioBase + 
               " | Marca: " + marcaCompatible + 
               " | Color: " + color;
    }
}