package com.example.logica;

public abstract class Pieza {

    protected int [][]coord = new int[4][2];
    protected int ancho;
    protected int alto;
    protected int anchosDer[];
    protected int anchosIzq[];
    protected int altos[];
    protected int orientacion = 0;
    protected int color;

    public int[][] getCoord() {
        return coord;
    }

    public int getColor() {
        return color;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() { return alto; }

    public int[] getAnchosIzq() { return anchosIzq; }

    public int[] getAnchosDer() { return anchosDer; }

    public int[] getAltos() { return altos; }

    public abstract void rotar();
}
