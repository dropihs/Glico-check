package com.app.glicoCheck.activity;

import static java.lang.Math.log;
import static java.lang.Math.log10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.app.glicoCheck.R;

public class BfActivity extends AppCompatActivity {
    private EditText altura;
    private EditText peso;
    private EditText cintura;
    private EditText quadril;
    private EditText pescoco;
    private EditText idade;
    private RadioButton radioButton;
    private RadioGroup radioGroupBf;




    private TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bf);
        altura = (EditText) findViewById(R.id.edtTextAlturaBf);
        peso = (EditText) findViewById(R.id.edtTextPesoBf);
        cintura = (EditText) findViewById(R.id.edtTextCinturaBf);
        quadril = (EditText) findViewById(R.id.edtTextPesoBf);
        pescoco = (EditText) findViewById(R.id.edtTextPescocoBf);
        idade = (EditText) findViewById(R.id.edtTextIdadeBf);
        radioGroupBf = findViewById(R.id.RadiogroupBf);
        int radioId = radioGroupBf.getCheckedRadioButtonId();
    }



    public void calcularBf(View v) {
        //Get Strings from inputs
        String alturaStr = altura.getText().toString();
        String pesoStr = peso.getText().toString();
        String cinturaStr = cintura.getText().toString();
        String quadrilStr = quadril.getText().toString();
        String pescocoStr = pescoco.getText().toString();
        String idadeStr = idade.getText().toString();

        //checar sexo
        int radioId = radioGroupBf.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        String sexo = radioButton.getText().toString();
        System.out.println(sexo);

        if (alturaStr != null && !"".equals(alturaStr)
                && (pesoStr != null && !"".equals(pesoStr))
                && (cinturaStr != null && !"".equals(cinturaStr))
                && (cinturaStr != null && !"".equals(cinturaStr))
                && (quadrilStr != null && !"".equals(quadrilStr))
                && (pescocoStr != null && !"".equals(pescocoStr))
                && (idadeStr != null && !"".equals(idadeStr))) {

            //Float values from Inputs
            float alturaValor = Float.parseFloat(alturaStr);
            float pesoValor = Float.parseFloat(pesoStr);
            float cinturaValor = Float.parseFloat(cinturaStr);
            float quadrilValor = Float.parseFloat(quadrilStr);
            float pescocoValor = Float.parseFloat(pescocoStr);
            float idadeValor = Float.parseFloat(idadeStr);

            System.out.println("Altura: " + alturaValor + "\nPeso: " + pesoValor +
                    "\nCintura: " + cinturaValor + "\nQuadril : " + quadrilValor + "\nPescoco: " +
                    pescocoValor + "\nidade: " + idadeValor);

            float bf = (float) (494 / (1.0324 - 0.19077 * log10(cinturaValor - pescocoValor) + 0.15456 * log10(alturaValor)) - 450);
            System.out.println("Resultado bf Homem: " + bf);


        }
    }

    public String checkRadioGroupSexo(View v){
        int radioId = radioGroupBf.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        return radioButton.getText().toString();
    }


    public void voltarHome(View v){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}





