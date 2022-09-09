package com.example.marcos;

import android.graphics.drawable.Icon;

public class Producto {
    //private byte noProducto;//numero consecutivo por pedido
    private float ancho, alto;
    //private Icon icono;
    private int modelo;
    //private String color;
    //private int modeloColor;
    private String vidrio;
    //private boolean marialuisa;
    //private float anchoMarialuisa;
    //private String colorMarialuisa;
    //private boolean mdf, bastidor;
    private boolean laminado, pegeng;
    private int precio;

    public Producto(float ancho, float alto, int modelo,
                    String vidrio, boolean laminado, boolean pegeng) {
        this.ancho = ancho;
        this.alto = alto;
        this.modelo = modelo;
        this.vidrio = vidrio;
        this.laminado = laminado;
        this.pegeng = pegeng;
        this.precio = calcularPrecio();
    }

    public int getPrecio() {
        return precio;
    }

    private int calcularPrecio() {
        return precioMarco() + precioVidrio() + precioOtros();
    }

    private int precioMarco() {
        //formula para el precio del marco
        return 0;
    }

    private int precioVidrio() {
        //formula para el precio del vidrio
        int p;

        switch (vidrio) {
            case "Anti":
                p = 1;
                break;
            case "Doble Anti":
                p = 2;
                break;
            case "Sin Anti":
                p = 3;
                break;
            case "Resina":
                 p = 4;
                break;
            case "Polioleo":
                p = 5;
                break;
            default:
                p = 0;
        }
        return p;
    }

    private int precioOtros() {
        //formula para el precio de otros
        int p = 0;
        if (laminado) {
            p = p + 1;
        }

        if (pegeng) {
            p = p + 1;
        }

        return p;
    }
}
