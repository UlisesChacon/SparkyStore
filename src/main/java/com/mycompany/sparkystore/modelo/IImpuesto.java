/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sparkystore.modelo;

/**
 *
 * @author USULISES
 */
public interface IImpuesto {
    // Constante del IGV en Perú
    double IGV = 0.18;
    
    // Método que deben tener todos los productos
    double calcularIGV(double precioBase);
}