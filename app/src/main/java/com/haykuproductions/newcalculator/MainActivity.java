package com.haykuproductions.newcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tAbajo;
    TextView tArriba;
    Button b;
    String op="";
    String opAntigua="";
    Double ultN=0.0;
    Double nRes=0.0;
    boolean operacion=false;
    boolean ultPulSign=false;
    boolean recienRes=false;
    Double memoria;
    boolean mVacia=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tAbajo=(TextView)findViewById(R.id.tv);
        tArriba=(TextView)findViewById(R.id.tv0);
        tAbajo.setText("0");
    }
    public void pulsar(View v) {
        b = (Button)findViewById(v.getId());


        if(!ultPulSign && !recienRes) {
            if(tAbajo.getText().toString()=="0"){
                tAbajo.setText(b.getText());
            }
            else
                tAbajo.append(b.getText());
        }
        else {
            tAbajo.setText(b.getText());

            ultPulSign = false;
            recienRes = false;
        }


    }
    public void pBorrarUno (View view){
        tAbajo.getText();
        if(tAbajo.length()>1)
            tAbajo.setText(""+tAbajo.getText().subSequence(0, tAbajo.length()-1));
        else
            tAbajo.setText("0");
    }
    public void pulsarPunto(View v) {
        b = (Button)findViewById(v.getId());
        if(!(tAbajo.getText().toString().contains("."))) {
            tAbajo.append(b.getText());
        }
    }
    public void bfsen(View v) {
        ultN = Double.parseDouble(tAbajo.getText().toString());

        nRes = (Math.sin(Math.toRadians(ultN)));
        tAbajo.setText(String.valueOf(nRes));
    }
    public void bfcos(View v) {

        ultN = Double.parseDouble(tAbajo.getText().toString());

        nRes = (Math.cos(Math.toRadians(ultN)));
        tAbajo.setText(String.valueOf(nRes));
    }
    public void bftan(View v) {
        ultN = Double.parseDouble(tAbajo.getText().toString());

        nRes = (Math.tan(Math.toRadians(ultN)));
        tAbajo.setText(String.valueOf(nRes));
    }
    public void bfRaiz(View v) {

        ultN = Double.parseDouble(tAbajo.getText().toString());

        nRes = (Math.sqrt(ultN));
        tAbajo.setText(String.valueOf(nRes));
    }
    public void operaciones(View v) {
        b = (Button) findViewById(v.getId());

        if(tAbajo.getText()==("0")){
            Toast.makeText(this, "Introduzca un número primero", Toast.LENGTH_SHORT).show();
        }
        else {
            opAntigua = op;
            op = "" + b.getText();
            if (ultPulSign) {
                tArriba.setText("" + tArriba.getText().subSequence(0, tArriba.length() - 1) + op);
            } else {
                if (!operacion) {
                    ultN = Double.parseDouble(tAbajo.getText().toString());
                    tArriba.setText((tAbajo.getText().toString()) + op);
                    operacion = true;

                } else {
                    tArriba.append((tAbajo.getText().toString()) + op);
                    ultN = calcular(ultN, Double.parseDouble(tAbajo.getText().toString()), opAntigua);
                    tAbajo.setText("" + ultN);

                }
            }
            ultPulSign = true;
        }



    }

    public void igual(View v)
    {

        double res = calcular(ultN,Double.parseDouble(tAbajo.getText().toString()),op);

        tAbajo.setText(""+res);
        tArriba.setText("");
        operacion=false;
        recienRes=true;

        
    }
    public void limpiar(View v)
    {
        ultN=0.0;
        nRes=0.0;
        tAbajo.setText("0");
        tArriba.setText("");
        op="";
        opAntigua="";
        operacion=false;
        ultPulSign=false;
    }
    public void guardarMem(View v) {
        if (tAbajo.getText() != ("")) {
            memoria = Double.parseDouble((tAbajo.getText().toString()));
            mVacia = false;
            Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Introduzca un número", Toast.LENGTH_SHORT).show();
    }

    public void cargarMem(View v){
        if(mVacia)
            Toast.makeText(this, "Memoria vacia", Toast.LENGTH_SHORT).show();
        else
            tAbajo.setText(""+memoria);
    }

    public double calcular(double n1,double n2,String op){
        double calcular=0;

        switch(op){
            case "+" : calcular=n1+n2;
                break;
            case "-" : calcular=n1-n2;
                break;
            case "*" : calcular=n1*n2;
                break;
            case "/" : calcular=n1/n2;
                break;
        }
        return calcular;

    }
}
