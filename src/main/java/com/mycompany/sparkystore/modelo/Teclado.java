/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkystore.modelo;

public class Teclado extends Periferico {
    private String idioma; // Ej: "Español", "Inglés US"

    public Teclado(int id, String nombre, double precio, int stock, String conexion, String idioma) {
        super(id, nombre, precio, stock, conexion);
        this.idioma = idioma;
    }

    @Override
    public String getCategoria() { return "Perifericos"; }

    @Override
    public String mostrarDetalle() {
        return "TECLADO: " + nombre + " [" + idioma + "] Conexión: " + tipoConexion;
    }
}