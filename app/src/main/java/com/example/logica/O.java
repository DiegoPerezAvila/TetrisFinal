package com.example.logica;

public class O extends Pieza{


    public O() {
        this.coord[0][0] = 0;
        this.coord[0][1] = 0;
        this.coord[1][0] = 0;
        this.coord[1][1] = 1;
        this.coord[2][0] = 1;
        this.coord[2][1] = 0;
        this.coord[3][0] = 1;
        this.coord[3][1] = 1;
        this.ancho = 2;
        this.alto = 2;
        this.anchosDer = new int[] {2,2};
        this.anchosIzq = new int[] {0,0};
        this.altos = new int[] {2,2};
        this.orientacion = 0;
        this.color = 4;
    }

    @Override
    public void rotar() {
    }
}
