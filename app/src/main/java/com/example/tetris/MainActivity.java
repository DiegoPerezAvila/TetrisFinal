package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.icu.lang.UCharacter;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logica.Tetris;

import org.w3c.dom.Text;
// Hola, este es el nuevo proyecto
// listo
public class MainActivity extends AppCompatActivity {

    private GridLayout layoutMatriz;
    private GridLayout nextfigure;
    private Button buttonreset;
    private Button buttonstart;
    private boolean pause = true;
    private int puntajemax = 200;
    private int newmovTime;

    private TextView[][]celdas = new TextView[20][10];
    private TextView[][]nextceldas = new TextView[4][4];
    private TextView puntaje;
    private Tetris tetris;
    private long movTime = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //poner icono :v tengo sueño , alv
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        this.layoutMatriz = findViewById(R.id.layoutMatriz);
        this.nextfigure = findViewById(R.id.nextfigure);
        this.puntaje= (TextView) findViewById(R.id.puntaje);
        buttonreset= (Button) findViewById(R.id.buttonreset);
        buttonstart= (Button) findViewById(R.id.buttonstart);

        time time = new time();
        time.execute();
        final MediaPlayer mp=MediaPlayer.create(MainActivity.this , R.raw.musictetris);
        mp.start();

        View fs = getWindow().getDecorView();
        fs.setOnTouchListener(new View.OnTouchListener() {
            int xi;
            int yi;
            int xf;
            int yf;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    xi = (int)motionEvent.getX();
                    yi = (int)motionEvent.getY();
                } else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    xf = (int)motionEvent.getX();
                    yf = (int)motionEvent.getY();
                    movimiento(xi, xf, yi, yf);
                }
                return true;
            }
        });

        for(int i=0; i<20; i++){
            for(int j=0; j<10; j++){
                this.celdas[i][j] = new TextView(this);
                this.celdas[i][j].setWidth(35);
                this.celdas[i][j].setHeight(35);
                this.celdas[i][j].setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
                this.celdas[i][j].setTextSize(15);
                this.layoutMatriz.addView(this.celdas[i][j]);
            }
        }

        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                this.nextceldas[i][j] = new TextView(this);
                this.nextceldas[i][j].setWidth(35);
                this.nextceldas[i][j].setHeight(35);
                this.nextceldas[i][j].setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
                this.nextceldas[i][j].setTextSize(15);
                this.nextfigure.addView(this.nextceldas[i][j]);
            }
        }
        this.tetris = new Tetris();
        this.pintar();

        buttonreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tetris = new Tetris();
                pintar();
            }
        });

        buttonstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pause && mp.isPlaying()) {
                    pause = false;
                    mp.pause();
                    buttonstart.setBackgroundResource(R.drawable.play);
                }
                else {
                    pause = true;
                    time time = new time();
                    time.execute();
                    mp.start();
                    buttonstart.setBackgroundResource(R.drawable.pause);
                }
            }
        });

    }





    private void movimiento(int xi, int xf, int yi, int yf) {
        if(pause) {
            int dx = xi - xf;
            int dy = yi - yf;
            String mov;
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0) {
                    mov = "Izq";
                    this.tetris.izquierda();
                    this.pintar();
                } else {
                    mov = "Der";
                    this.tetris.derecha();
                    this.pintar();
                }
            } else {
                if (dy > 0) {
                    this.tetris.girar();
                    this.pintar();
                    mov = "Arr";
                } else {
                    this.movTime = 1;
                    this.pintar();
                    long temp = this.tetris.getPuntaje()+10;
                    this.tetris.setPuntaje(temp);
                    mov = "Aba";
                }
            }
            this.pintar();
        }
    }

    private void pintar() {
        this.puntaje.setText(String.valueOf(this.tetris.getPuntaje()));
        for(int i=0; i<20; i++){
            for(int j=0; j<10; j++){
                this.celdas[i][j].setText(String.valueOf(this.tetris.getMatriz()[i][j]));
                if(this.tetris.getMatriz()[i][j] == 0) {
                    this.celdas[i][j].setTextColor(Color.WHITE);
                    this.celdas[i][j].setBackgroundColor(Color.WHITE);
                } else if (this.tetris.getMatriz()[i][j] == 1){
                    this.celdas[i][j].setTextColor(Color.CYAN);
                    this.celdas[i][j].setBackgroundColor(Color.CYAN);
                } else if (this.tetris.getMatriz()[i][j] == 2){
                    this.celdas[i][j].setTextColor(Color.BLUE);
                    this.celdas[i][j].setBackgroundColor(Color.BLUE);
                } else if (this.tetris.getMatriz()[i][j] == 3){
                    this.celdas[i][j].setTextColor(Color.parseColor("#FF732E"));
                    this.celdas[i][j].setBackgroundColor(Color.parseColor("#FF732E"));
                } else if (this.tetris.getMatriz()[i][j] == 4){
                    this.celdas[i][j].setTextColor(Color.parseColor("#FFC92E"));
                    this.celdas[i][j].setBackgroundColor(Color.parseColor("#FFC92E"));
                } else if (this.tetris.getMatriz()[i][j] == 5){
                    this.celdas[i][j].setTextColor(Color.GREEN);
                    this.celdas[i][j].setBackgroundColor(Color.GREEN);
                } else if (this.tetris.getMatriz()[i][j] == 6){
                    this.celdas[i][j].setTextColor(Color.parseColor("#FF48DD"));
                    this.celdas[i][j].setBackgroundColor(Color.parseColor("#FF48DD"));
                } else if (this.tetris.getMatriz()[i][j] == 7){
                    this.celdas[i][j].setTextColor(Color.RED);
                    this.celdas[i][j].setBackgroundColor(Color.RED);
                } else if (this.tetris.getMatriz()[i][j] == 8){
                    this.celdas[i][j].setTextColor(Color.BLACK);
                    this.celdas[i][j].setBackgroundColor(Color.BLACK);
                }
            }
        }
        for(int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                this.nextceldas[i][j].setText(String.valueOf(this.tetris.getNextmatriz()[i][j]));
                if(this.tetris.getNextmatriz()[i][j] == 0) {
                    this.nextceldas[i][j].setTextColor(Color.BLACK);
                    this.nextceldas[i][j].setBackgroundColor(Color.BLACK);
                } else if (this.tetris.getNextmatriz()[i][j] == 1){
                    this.nextceldas[i][j].setTextColor(Color.CYAN);
                    this.nextceldas[i][j].setBackgroundColor(Color.CYAN);
                } else if (this.tetris.getNextmatriz()[i][j] == 2){
                    this.nextceldas[i][j].setTextColor(Color.BLUE);
                    this.nextceldas[i][j].setBackgroundColor(Color.BLUE);
                } else if (this.tetris.getNextmatriz()[i][j] == 3){
                    this.nextceldas[i][j].setTextColor(Color.parseColor("#FF732E"));
                    this.nextceldas[i][j].setBackgroundColor(Color.parseColor("#FF732E"));
                } else if (this.tetris.getNextmatriz()[i][j] == 4){
                    this.nextceldas[i][j].setTextColor(Color.parseColor("#FFC92E"));
                    this.nextceldas[i][j].setBackgroundColor(Color.parseColor("#FFC92E"));
                } else if (this.tetris.getNextmatriz()[i][j] == 5){
                    this.nextceldas[i][j].setTextColor(Color.GREEN);
                    this.nextceldas[i][j].setBackgroundColor(Color.GREEN);
                } else if (this.tetris.getNextmatriz()[i][j] == 6){
                    this.nextceldas[i][j].setTextColor(Color.parseColor("#FF48DD"));
                    this.nextceldas[i][j].setBackgroundColor(Color.parseColor("#FF48DD"));
                } else if (this.tetris.getNextmatriz()[i][j] == 7){
                    this.nextceldas[i][j].setTextColor(Color.RED);
                    this.nextceldas[i][j].setBackgroundColor(Color.RED);
                } else if (this.tetris.getNextmatriz()[i][j] == 8){
                    this.nextceldas[i][j].setTextColor(Color.BLACK);
                    this.nextceldas[i][j].setBackgroundColor(Color.BLACK);
                }
            }
        }
    }


    public void hilo(){
        if(movTime!=1) {
            try {
                Thread.sleep(this.movTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void  ejecutar() {
        time time = new time();
        time.execute();
    }

    public class time extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            for(int i=1; i<=1; i++){
                hilo();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(pause) {

                ejecutar();
                boolean variable = tetris.abajo();
                if (!variable) movTime = 500;
                pintar();
            }
        }
    }
}