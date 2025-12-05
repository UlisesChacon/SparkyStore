package com.mycompany.sparkystore;

import com.mycompany.sparkystore.controlador.GestorInventario;
import com.mycompany.sparkystore.modelo.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SparkyStore {

    private static GestorInventario gestor = new GestorInventario();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;
        
        do {
            System.out.println("\n=== SPARKY STORE - GESTIÓN & VENTAS ===");
            System.out.println("1. Punto de Venta (Vender)");
            System.out.println("2. Ver Stock por Categorías");
            System.out.println("3. Agregar Nuevo Producto (Admin)");
            System.out.println("4. Eliminar Producto (Admin)");
            System.out.println("5. Salir");
            System.out.print("Seleccione opción: ");
            
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1: realizarVenta(); break;
                case 2: mostrarStockPorCategoria(); break;
                case 3: registrarProducto(); break;
                case 4: eliminarProducto(); break;
                case 5: System.out.println("Guardando datos... ¡Adiós!"); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    // --- OPCIÓN 3: REGISTRAR PRODUCTO ---
    private static void registrarProducto() {
        System.out.println("\n--- REGISTRO DE NUEVO PRODUCTO ---");
        System.out.println("1. Laptop | 2. Mouse | 3. Tinta | 4. Antivirus");
        System.out.print("Tipo: ");
        
        try {
            int tipo = Integer.parseInt(sc.nextLine());
            System.out.print("ID Único: ");
            int id = Integer.parseInt(sc.nextLine());
            
            if (gestor.buscarProducto(id) != null) {
                System.out.println("❌ Error: ID ya existe.");
                return;
            }

            System.out.print("Nombre: "); String nombre = sc.nextLine();
            System.out.print("Precio: "); double precio = Double.parseDouble(sc.nextLine());
            System.out.print("Stock: "); int stock = Integer.parseInt(sc.nextLine());

            Producto p = null;
            switch(tipo) {
                case 1: // Laptop
                    System.out.print("Procesador: "); String proc = sc.nextLine();
                    System.out.print("RAM (GB): "); int ram = Integer.parseInt(sc.nextLine());
                    System.out.print("Batería (mAh): "); int bat = Integer.parseInt(sc.nextLine());
                    System.out.print("Pantalla (\"): "); double pant = Double.parseDouble(sc.nextLine());
                    p = new Laptop(id, nombre, precio, stock, proc, ram, bat, pant);
                    break;
                case 2: // Mouse
                    System.out.print("Conexión: "); String conn = sc.nextLine();
                    System.out.print("DPI: "); int dpi = Integer.parseInt(sc.nextLine());
                    p = new Mouse(id, nombre, precio, stock, conn, dpi);
                    break;
                case 3: // Tinta
                    System.out.print("Marca Compatible: "); String marca = sc.nextLine();
                    System.out.print("Color: "); String color = sc.nextLine();
                    p = new Tinta(id, nombre, precio, stock, marca, color);
                    break;
                case 4: // Antivirus
                    System.out.print("Licencia: "); String lic = sc.nextLine();
                    System.out.print("Días Vigencia: "); int dias = Integer.parseInt(sc.nextLine());
                    p = new Antivirus(id, nombre, precio, stock, lic, dias);
                    break;
            }

            if (p != null) {
                gestor.agregarProducto(p);
                System.out.println("✅ Producto registrado correctamente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Error de formato.");
        }
    }

    // --- OPCIÓN 4: ELIMINAR PRODUCTO ---
    private static void eliminarProducto() {
        System.out.println("\n--- ELIMINAR PRODUCTO ---");
        System.out.print("Ingrese ID del producto a eliminar: ");
        try {
            int id = Integer.parseInt(sc.nextLine());
            Producto p = gestor.buscarProducto(id);
            
            if (p != null) {
                System.out.println("Encontrado: " + p.getNombre() + " (Stock: " + p.getStock() + ")");
                System.out.print("¿Seguro que desea eliminarlo permanentemente? (S/N): ");
                if (sc.nextLine().equalsIgnoreCase("S")) {
                    gestor.eliminarProducto(id);
                    System.out.println("✅ Producto eliminado y archivo actualizado.");
                } else {
                    System.out.println("Operación cancelada.");
                }
            } else {
                System.out.println("❌ ID no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Ingrese un ID numérico.");
        }
    }

    // --- OPCIÓN 1: PUNTO DE VENTA (CON RESUMEN DE AHORRO) ---
    private static void realizarVenta() {
        System.out.println("\n--- NUEVA VENTA (CARRITO) ---");
        
        ArrayList<ItemCarrito> carrito = new ArrayList<>();
        double totalBrutoAcumulado = 0;
        String continuar = "S";

        // 1. LLENADO DEL CARRITO
        do {
            System.out.print("Ingrese ID del producto: ");
            try {
                int id = Integer.parseInt(sc.nextLine());
                System.out.print("Ingrese Cantidad: ");
                int cantidad = Integer.parseInt(sc.nextLine());

                if (gestor.validarStock(id, cantidad)) {
                    Producto p = gestor.buscarProducto(id);
                    carrito.add(new ItemCarrito(p, cantidad));
                    totalBrutoAcumulado += (p.getPrecioBase() * cantidad);
                    System.out.println("-> Agregado: " + p.getNombre());
                } else {
                    System.out.println("❌ Stock insuficiente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Ingrese solo números.");
            }

            System.out.print("¿Agregar otro? (S/N): ");
            continuar = sc.nextLine();
        } while (continuar.equalsIgnoreCase("S"));

        if (carrito.isEmpty()) return;

        // 2. DATOS CLIENTE
        System.out.println("\n--- PROCESANDO ---");
        System.out.print("DNI Cliente: "); String dni = sc.nextLine();
        System.out.print("Nombre Cliente: "); String nombre = sc.nextLine();

        // 3. CÁLCULOS
        double porcDescuento = 0.0;
        String etiquetaDesc = "NO APLICA";

        if (totalBrutoAcumulado >= 5000) {
            porcDescuento = 0.05; 
            etiquetaDesc = "NAVIDAD (5%)";
        } else if (totalBrutoAcumulado >= 2000) {
            porcDescuento = 0.02; 
            etiquetaDesc = "NAVIDAD (2%)";
        }

        double montoDescuento = totalBrutoAcumulado * porcDescuento;
        double totalPagar = totalBrutoAcumulado - montoDescuento;
        double subtotalBase = totalPagar / 1.18;
        double igv = totalPagar - subtotalBase;

        // 4. IMPRESIÓN BOLETA (MEJORADA)
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("\n" + "=".repeat(75));
        System.out.println("                           SPARKY STORE                           ");
        System.out.println("                         RUC: 2045568952                          ");
        System.out.println("-".repeat(75));
        System.out.println("Fecha: " + ahora.format(fmt));
        System.out.printf("Cliente: %-40s%n", nombre);
        System.out.printf("DNI    : %-15s%n", dni);
        System.out.println("-".repeat(75));
        
        System.out.printf("%-5s | %-25s | %-4s | %-10s | %-10s%n", "COD", "PRODUCTO", "CANT", "P.UNIT", "TOTAL");
        System.out.println("-".repeat(75));

        for (ItemCarrito item : carrito) {
            System.out.printf("%-5d | %-25s | %-4d | S/%8.2f | S/%8.2f%n", 
                    item.producto.getId(), 
                    (item.producto.getNombre().length() > 25 ? item.producto.getNombre().substring(0, 22) + "..." : item.producto.getNombre()),
                    item.cantidad, 
                    item.producto.getPrecioBase(), 
                    item.getSubtotal());
        }
        
        System.out.println("-".repeat(75));
        // AQUI ESTA EL CAMBIO VISUAL QUE PEDISTE:
        System.out.printf("IMPORTE TOTAL (Sin Dcto):                                    S/%10.2f%n", totalBrutoAcumulado);
        System.out.printf("DESCT. %-11s      :                                  - S/%10.2f%n", etiquetaDesc, montoDescuento);
        System.out.println("-".repeat(75));
        System.out.printf("SUBTOTAL (Op. Gravada)  :                                    S/%10.2f%n", subtotalBase);
        System.out.printf("IGV (18%%)               :                                    S/%10.2f%n", igv);
        System.out.printf("TOTAL A PAGAR           :                                    S/%10.2f%n", totalPagar);
        System.out.println("=".repeat(75));

        // 5. CONFIRMACIÓN
        System.out.print("\n¿Confirmar venta? (S/N): ");
        if (sc.nextLine().equalsIgnoreCase("S")) {
            for (ItemCarrito item : carrito) {
                item.producto.restarStock(item.cantidad);
            }
            gestor.guardarInventario();
            System.out.println("✅ Venta finalizada.");
        } else {
            System.out.println("🚫 Cancelada.");
        }
    }

    // --- OPCIÓN 2: STOCK POR CATEGORÍA ---
    private static void mostrarStockPorCategoria() {
        System.out.println("\n--- STOCK ---");
        System.out.println("1. Computo | 2. Perifericos | 3. Suministros | 4. Software");
        System.out.print("Opción: ");
        try {
            int op = Integer.parseInt(sc.nextLine());
            String cat = switch (op) {
                case 1 -> "Computo";
                case 2 -> "Perifericos";
                case 3 -> "Suministros";
                case 4 -> "Software";
                default -> "";
            };

            if (!cat.isEmpty()) {
                List<Producto> lista = gestor.listarPorCategoria(cat);
                System.out.println("\n--- " + cat.toUpperCase() + " ---");
                if (lista.isEmpty()) System.out.println("(Vacío)");
                for (Producto p : lista) {
                    System.out.println("[" + p.getId() + "] " + p.mostrarDetalle() + " | Stock: " + p.getStock());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Opción inválida.");
        }
    }

    static class ItemCarrito {
        Producto producto;
        int cantidad;
        public ItemCarrito(Producto p, int c) { this.producto = p; this.cantidad = c; }
        public double getSubtotal() { return producto.getPrecioBase() * cantidad; }
    }
}