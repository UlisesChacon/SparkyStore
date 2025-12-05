/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkystore.modelo;

public abstract class Periferico extends Producto {
    protected String tipoConexion; // Ej: "USB", "Bluetooth"

    public Periferico(int id, String nombre, double precio, int stock, String tipoConexion) {
        super(id, nombre, precio, stock);
        this.tipoConexion = tipoConexion;
    }
}
