/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkystore.modelo;

public class AllInOne extends Computador {
    private boolean esTactil;

    public AllInOne(int id, String nombre, double precio, int stock, String proc, int ram, boolean tactil) {
        super(id, nombre, precio, stock, proc, ram);
        this.esTactil = tactil;
    }

    @Override
    public String getCategoria() { return "Computo"; }

    @Override
    public String mostrarDetalle() {
        return "AIO: " + nombre + " | Pantalla Táctil: " + (esTactil ? "SÍ" : "NO");
    }
}
