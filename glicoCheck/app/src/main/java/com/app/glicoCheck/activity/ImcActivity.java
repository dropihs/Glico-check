package com.app.glicoCheck.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.app.glicoCheck.R;

public class ImcActivity extends AppCompatActivity {
    EditText campoPeso;
    EditText campoAltura;
    TextView campoResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
    }


    public void calcularImc(View v){


        EditText campoPeso = (EditText) findViewById(R.id.editTextNumberPeso);
        EditText campoAltura = (EditText) findViewById(R.id.editTextNumberAltura);
        TextView campoResultado = (TextView) findViewById(R.id.textViewResultado);

        int peso = Integer.parseInt(campoPeso.getText().toString());
        float altura = Float.parseFloat(campoAltura.getText().toString());
        float imc = peso/(altura*altura);

        if(imc < 19){
            //abaixo
            campoResultado.setText("Abaixo do peso!");
        }
        else if(imc > 32){
            //obeso
            campoResultado.setText("Acima do peso!");
        }
        else{
            campoResultado.setText("Peso ok!");
        }

    }
    public void voltarHome(View v){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }



}