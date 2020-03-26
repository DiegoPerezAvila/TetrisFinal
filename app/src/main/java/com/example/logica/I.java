package com.example.logica;

public class I extends Pieza {

    public I() {
        this.coord[0][0] = 0;
        this.coord[0][1] = 0;
        this.coord[1][0] = 0;
        this.coord[1][1] = 1;
        this.coord[2][0] = 0;
        this.coord[2][1] = 2;
        this.coord[3][0] = 0;
        this.coord[3][1] = 3;
        this.ancho = 4;
        this.alto = 1;
        this.anchosDer = new int[] {4};
        this.anchosIzq = new int[] {0};
        this.altos = new int[] {1,1,1,1};
        this.orientacion = 0;
        this.color = 1;
    }

    @Override
    public void rotar() {
        if(this.orientacion == 0){
            this.coord[0][0] = 0;
            this.coord[0][1] = 0;
            this.coord[1][0] = 1;
            this.coord[1][1] = 0;
            this.coord[2][0] = 2;
            this.coord[2][1] = 0;
            this.coord[3][0] = 3;
            this.coord[3][1] = 0;
            this.ancho = 1;
            this.alto = 4;
            this.anchosDer = new int[] {1,1,1,1};
            this.anchosIzq = new int[] {0,0,0,0};
            this.altos = new int[] {4};
            this.orientacion = 1;
        } else {
            this.coord[0][0] = 0;
            this.coord[0][1] = 0;
            this.coord[1][0] = 0;
            this.coord[1][1] = 1;
            this.coord[2][0] = 0;
            this.coord[2][1] = 2;
            this.coord[3][0] = 0;
            this.coord[3][1] = 3;
            this.ancho = 4;
            this.alto = 1;
            this.anchosDer = new int[] {4};
            this.anchosIzq = new int[] {0};
            this.altos = new int[] {1,1,1,1};
            this.orientacion = 0;
        }
    }
}
