/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkystore.modelo;

public class Antivirus extends Software {
    private int diasVigencia;

    public Antivirus(int id, String nombre, double precio, int stock, String licencia, int dias) {
        super(id, nombre, precio, stock, licencia);
        this.diasVigencia = dias;
    }

    @Override
    public String getCategoria() { return "Software"; }

    @Override
    public String mostrarDetalle() {
        return "ANTIVIRUS: " + nombre + 
               " | Precio: S/" + precioBase + 
               " | Licencia: " + tipoLicencia + 
               " | Vigencia: " + diasVigencia + " dias";
    }
}