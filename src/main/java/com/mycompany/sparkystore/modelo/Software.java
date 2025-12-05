/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkystore.modelo;

public abstract class Software extends Producto {
    protected String tipoLicencia; // Ej: "Suscripción", "Perpetua", "Open Source"

    public Software(int id, String nombre, double precio, int stock, String tipoLicencia) {
        super(id, nombre, precio, stock);
        this.tipoLicencia = tipoLicencia;
    }
}