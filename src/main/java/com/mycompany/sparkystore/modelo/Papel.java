/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkystore.modelo;

public class Papel extends Suministro {
    private int gramaje; // Ej: 75gr, 90gr

    public Papel(int id, String nombre, double precio, int stock, String marca, int gramaje) {
        super(id, nombre, precio, stock, marca);
        this.gramaje = gramaje;
    }

    @Override
    public String getCategoria() { return "Suministros"; }

    @Override
    public String mostrarDetalle() {
        return "RESMA PAPEL " + nombre + " (" + gramaje + "gr)";
    }
}