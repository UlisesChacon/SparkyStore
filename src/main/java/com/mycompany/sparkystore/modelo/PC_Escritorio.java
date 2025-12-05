/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkystore.modelo;

public class PC_Escritorio extends Computador {
    private boolean tieneGpuDedicada;

    public PC_Escritorio(int id, String nombre, double precio, int stock, String proc, int ram, boolean gpu) {
        super(id, nombre, precio, stock, proc, ram);
        this.tieneGpuDedicada = gpu;
    }

    @Override
    public String getCategoria() { return "Computo"; }

    @Override
    public String mostrarDetalle() {
        return "PC DESKTOP: " + nombre + (tieneGpuDedicada ? " [GAMER]" : " [OFICINA]");
    }
}
