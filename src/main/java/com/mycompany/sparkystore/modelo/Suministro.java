/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkystore.modelo;

public abstract class Suministro extends Producto {
    protected String marcaCompatible;

    public Suministro(int id, String nombre, double precio, int stock, String marcaCompatible) {
        super(id, nombre, precio, stock);
        this.marcaCompatible = marcaCompatible;
    }
}