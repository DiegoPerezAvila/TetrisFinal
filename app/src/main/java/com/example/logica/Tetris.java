package com.example.logica;

import android.util.Log;

public class Tetris {
    private int[][]matriz = new int[20][10];
    private int[][]nextmatriz = new int[4][4];
    private int numP = 9;
    private int numnext;
    private long puntaje=0;

    private Pieza piezaActual;
    private Pieza piezaSiguiente;
    private int filaActual;
    private int colActual;

    public int[][] getMatriz(){
        return matriz;
    }

    public long getPuntaje(){
        return puntaje;
    }

    public void setPuntaje(long puntaje) {
        this.puntaje = puntaje;
    }

    public int[][] getNextmatriz(){
        return nextmatriz;
    }

    public Tetris(){
        this.nuevaPiezaActual();
    }



    private void nuevaPiezaActual() {
        if(numP == 9) numP = (int)(Math.random()*7);
        else numP = numnext;
        numnext = (int)(Math.random()*7);
        if(numP == 0) {
            this.piezaActual = new I();
        } else if(numP == 1) {
            this.piezaActual = new J();
        } else if(numP == 2) {
            this.piezaActual = new L();
        } else if(numP == 3) {
            this.piezaActual = new O();
        } else if(numP == 4) {
            this.piezaActual = new S();
        } else if(numP == 5) {
            this.piezaActual = new T();
        } else if(numP == 6) {
            this.piezaActual = new Z();
        }
        if(numnext == 0) {
            this.piezaSiguiente = new I();
        } else if(numnext == 1) {
            this.piezaSiguiente = new J();
        } else if(numnext == 2) {
            this.piezaSiguiente = new L();
        } else if(numnext == 3) {
            this.piezaSiguiente = new O();
        } else if(numnext == 4) {
            this.piezaSiguiente = new S();
        } else if(numnext == 5) {
            this.piezaSiguiente = new T();
        } else if(numnext == 6) {
            this.piezaSiguiente = new Z();
        }
        this.filaActual = 0;
        this.colActual = 4;
        int valor = this.actualizarEstado();
        if(valor > 3){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int k=0; k<20; k++){
                for(int j=0; j<10; j++){
                    this.matriz[k][j] = 0;
                }
            }
        }
    }







    public boolean abajo(){
        if(this.filaActual < (20 - this.piezaActual.getAlto())){
            boolean valor = this.revisarSiguienteFila();
            if(valor == true){
                this.limpiar();
                this.filaActual++;
                actualizarEstado();
                return true;
            } else {
                this.verificarFilasVacias();
                this.bajarTodasLasFilas();
                this.nuevaPiezaActual();
                return false;
            }
        } else {
            this.verificarFilasVacias();
            this.bajarTodasLasFilas();
            this.nuevaPiezaActual();
            return false;
        }
    }

    public boolean revisarSiguienteFila(){
        boolean valor = true;
        for(int i=0; i<piezaActual.getAncho(); i++) {
            if (this.matriz[this.filaActual + this.piezaActual.getAltos()[i]][this.colActual + i] != 0) {
                return false;
            } else {
                valor = true;
            }
        }
        return valor;
    }

    private void verificarFilasVacias(){
        int cantidad;
        for (int i = 19; i >= 0; i--) {
            cantidad = 0;
            for (int j = 9; j >= 0; j--) {
                if (matriz[i][j] != 0) {
                    cantidad++;
                }
            }
            if (cantidad == 10) {
                for (int j = 9; j >= 0; j--) {
                    matriz[i][j] = 8;
                }
            }
        }
    }

    private void bajarTodasLasFilas(){
        int cantidad;

        boolean cambio = false;
        for(int o=19; o>=0; o--) {
            for (int i = 19; i >= 0; i--) {
                cantidad = 0;
                for (int j = 9; j >= 0; j--) {
                    if (matriz[i][j] == 8) {
                        cantidad++;
                    }
                }
                if (cantidad == 10) {
                    for (int k = i; k >= 0; k--) {
                        for (int j = 9; j >= 0; j--) {
                            if (k - 1 >= 0) {
                                matriz[k][j] = matriz[k - 1][j];
                            } else {
                                matriz[k][j] = 0;
                            }
                        }
                    }
                    cambio = true;
                }
            }
        }
        if(cambio) {
            puntaje+=100;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void derecha(){
        if (this.colActual < (10 - this.piezaActual.getAncho())) {
            boolean valor = this.revisarSiguienteColDer();
            if(valor) {
                this.limpiar();
                this.colActual++;
                this.actualizarEstado();
            }
        }
    }

    public boolean revisarSiguienteColDer(){
        boolean valor = true;
        for(int i=0; i<piezaActual.getAlto(); i++) {
            if (this.matriz[this.filaActual + i][this.colActual + this.piezaActual.getAnchosDer()[i]] != 0) {
                return false;
            } else {
                valor = true;
            }
        }
        return valor;
    }

    public String toString() {
        return "bien bien "+ this.colActual;
    }

    public void izquierda(){
        if (this.colActual > 0) {
            boolean valor = this.revisarSiguienteColIzq();
            if(valor) {
                this.limpiar();
                this.colActual--;
                this.actualizarEstado();
            }
        }
    }

    public boolean revisarSiguienteColIzq(){
        boolean valor = true;
        for(int i=0; i<piezaActual.getAlto(); i++) {
            if (this.matriz[this.filaActual + i][this.colActual - 1 + this.piezaActual.getAnchosIzq()[i]] != 0) {
                return false;
            } else {
                valor = true;
            }
        }
        return valor;
    }

    public void girar() {
        if(piezaActual.getAlto() + filaActual < 20) {
            boolean valorColIzq;
            boolean valorColDer;

            if (this.colActual > 0) valorColIzq = this.revisarSiguienteColIzq();
            else valorColIzq = true;

            if (this.colActual < (10 - this.piezaActual.getAncho())) valorColDer = this.revisarSiguienteColDer();
            else valorColDer = true;

            boolean valorFila = this.revisarSiguienteFila();

            if (valorFila && valorColDer && valorColIzq) {
                if ((piezaActual.getAlto() + filaActual) < 20) {
                    this.limpiar();
                    this.piezaActual.rotar();
                    while ((piezaActual.getAncho() + colActual) > 10) {
                        colActual--;
                    }
                    while ((piezaActual.getAlto() + filaActual) > 20){
                        filaActual--;
                    }

                }
            }
            this.actualizarEstado();
        }
    }


    private int actualizarEstado() {
        int sucio = 0;
        for(int i=0; i<4; i++) {
            if(this.matriz[this.filaActual+this.piezaActual.getCoord()[i][0]][this.colActual+this.piezaActual.getCoord()[i][1]] == 0) {
                this.matriz[this.filaActual + this.piezaActual.getCoord()[i][0]][this.colActual + this.piezaActual.getCoord()[i][1]] = this.piezaActual.getColor();
            } else {
                sucio++;
            }
        }
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                this.nextmatriz[i][j] =0;
            }
        }
        for(int i=0; i<4; i++) {
            if(this.nextmatriz[this.piezaSiguiente.getCoord()[i][0]][this.piezaSiguiente.getCoord()[i][1]] == 0) {
                this.nextmatriz[this.piezaSiguiente.getCoord()[i][0]][this.piezaSiguiente.getCoord()[i][1]] = this.piezaSiguiente.getColor();
            }
        }
        return sucio;
    }

    private void limpiar() {
        for(int i=0; i<4; i++) {
            this.matriz[this.filaActual+this.piezaActual.getCoord()[i][0]][this.colActual+this.piezaActual.getCoord()[i][1]] = 0;
        }
    }


}
