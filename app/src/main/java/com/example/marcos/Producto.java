package com.example.marcos;

public class Producto {
    float ancho, alto;
    private int modelo;
    private String vidrio;
    private boolean laminado, pegeng;

    public Producto(float ancho, float alto, int modelo,
                    String vidrio, boolean laminado, boolean pegeng) {
        this.ancho = ancho;
        this.alto = alto;
        this.modelo = modelo;
        this.vidrio = vidrio;
        this.laminado = laminado;
        this.pegeng = pegeng;
    }

    public int getPrecio() {
        return calcularPrecio();
    }

    private int calcularPrecio() {
        return precioMarco() + precioVidrio() + precioMDF();
    }

    private int precioMarco() {
        return (int)((ancho+ancho+alto+alto)*modelo/240);
    }

    private int precioVidrio() {
        int p;
        switch (vidrio) {
            case "Anti":
                p = (int)(ancho*alto*1350*2/(160*220));
                break;
            case "Doble Anti":
                p = (int)(ancho*alto*1350*4/(160*220));
                break;
            case "Sin Anti":
                p = 10;
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

    private int precioMDF() {
        return (int)((ancho*alto*200*3)/(122*244));
    }

    private int precioOtros() {
        int p = 0;
        if (laminado)
            p = p + 1;

        if (pegeng)
            p = p + 1;

        return p;
    }
}
