/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkystore.modelo;

public class Mouse extends Periferico {
    private int dpi;

    public Mouse(int id, String nombre, double precio, int stock, String conexion, int dpi) {
        super(id, nombre, precio, stock, conexion);
        this.dpi = dpi;
    }

    @Override
    public String getCategoria() { return "Perifericos"; }

    @Override
    public String mostrarDetalle() {
        return "MOUSE: " + nombre + 
               " | Precio: S/" + precioBase + 
               " | " + dpi + " DPI" + 
               " | Conexión: " + tipoConexion;
    }
}