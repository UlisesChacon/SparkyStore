/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkystore.modelo;

public class SistemaOperativo extends Software {
    private String arquitectura; // Ej: "64 bits", "32 bits"

    public SistemaOperativo(int id, String nombre, double precio, int stock, String licencia, String arq) {
        super(id, nombre, precio, stock, licencia);
        this.arquitectura = arq;
    }

    @Override
    public String getCategoria() { return "Software"; }

    @Override
    public String mostrarDetalle() {
        return "S.O.: " + nombre + " (" + arquitectura + ") - " + tipoLicencia;
    }
}
