/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkystore.modelo;

public abstract class Computador extends Producto {
    protected String procesador;
    protected int ram;

    public Computador(int id, String nombre, double precio, int stock, String procesador, int ram) {
        super(id, nombre, precio, stock);
        this.procesador = procesador;
        this.ram = ram;
    }
}
