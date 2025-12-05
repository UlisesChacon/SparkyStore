/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkystore.modelo;

public class Laptop extends Computador {
    private int bateriaMAh;
    private double tamanoPantalla;

    public Laptop(int id, String nombre, double precio, int stock, String proc, int ram, int bateria, double pantalla) {
        super(id, nombre, precio, stock, proc, ram);
        this.bateriaMAh = bateria;
        this.tamanoPantalla = pantalla;
    }

    @Override
    public String getCategoria() { return "Computo"; }

    @Override
    public String mostrarDetalle() {
        
        return "LAPTOP: " + nombre +  
               " | CPU: " + procesador + 
               " | RAM: " + ram + "GB" + 
               " | Bat: " + bateriaMAh + "mAh" + 
               " | Pantalla: " + tamanoPantalla + 
               " | Precio: S/" + precioBase + "\"";
    }
}