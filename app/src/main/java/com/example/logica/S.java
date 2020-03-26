package com.example.logica;

public class S extends Pieza {


    public S() {
        this.coord[0][0] = 0;
        this.coord[0][1] = 1;
        this.coord[1][0] = 0;
        this.coord[1][1] = 2;
        this.coord[2][0] = 1;
        this.coord[2][1] = 0;
        this.coord[3][0] = 1;
        this.coord[3][1] = 1;
        this.ancho = 3;
        this.alto = 2;
        this.anchosDer = new int[] {3,2};
        this.anchosIzq = new int[] {1,0};
        this.altos = new int[] {2,2,1};
        this.orientacion = 0;
        this.color = 5;
    }

    @Override
    public void rotar() {
        if(this.orientacion == 0){
            this.coord[0][0] = 0;
            this.coord[0][1] = 0;
            this.coord[1][0] = 1;
            this.coord[1][1] = 0;
            this.coord[2][0] = 1;
            this.coord[2][1] = 1;
            this.coord[3][0] = 2;
            this.coord[3][1] = 1;
            this.ancho = 2;
            this.alto = 3;
            this.anchosDer = new int[] {1,2,2};
            this.anchosIzq = new int[] {0,0,1};
            this.altos = new int[] {2,3};
            this.orientacion = 1;
        } else {
            this.coord[0][0] = 0;
            this.coord[0][1] = 1;
            this.coord[1][0] = 0;
            this.coord[1][1] = 2;
            this.coord[2][0] = 1;
            this.coord[2][1] = 0;
            this.coord[3][0] = 1;
            this.coord[3][1] = 1;
            this.ancho = 3;
            this.alto = 2;
            this.anchosDer = new int[] {3,2};
            this.anchosIzq = new int[] {1,0};
            this.altos = new int[] {2,2,1};
            this.orientacion = 0;
        }
    }
}
