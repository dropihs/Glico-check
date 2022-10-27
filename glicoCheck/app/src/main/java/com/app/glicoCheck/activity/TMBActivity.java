package com.app.glicoCheck.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.glicoCheck.R;

import java.util.ArrayList;

public class TMBActivity extends AppCompatActivity {
    private EditText altura;
    private EditText peso;
    private EditText idade;
    private RadioButton radioButton;
    private RadioGroup radioGroupTMB;
    private TextView TMBresultado;
    private TextView TMBatividaderesultado;
    String nivelAtividade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmbactivity);
        inicializador();
    }



    public void CalcularTMB(View view){
        String alturaStr = altura.getText().toString();
        String pesoStr = peso.getText().toString();
        String idadeStr = idade.getText().toString();
        System.out.println( "Altura: " + alturaStr + "peso: " + pesoStr + "idade: "+ idadeStr);
        int radioId = radioGroupTMB.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        String sexo = radioButton.getText().toString();
        System.out.println(sexo);
        System.out.println(nivelAtividade);
        float nivelAtividade = checarNivelAtividade();
        System.out.println(nivelAtividade);


        float alturaValor = Float.parseFloat(alturaStr);
        float pesoValor = Float.parseFloat(pesoStr);
        float idadeValor = Float.parseFloat(idadeStr);
        System.out.println( "Altura: " + alturaValor + "peso: " + pesoValor + "idade: "+ idadeValor);


        if (sexo.equals("Masculino")){
            float TMB = (float) ((66) + (13.8 * pesoValor) + (5*alturaValor) - (6.8 * idadeValor));
            float TMBatividade = (float) (nivelAtividade * ((66) + (13.8 * pesoValor) + (5*alturaValor) - (6.8 * idadeValor)));
            System.out.println("Masculino TMB: " + TMB);
            System.out.println("Masculino TMB com atividade física: " + TMBatividade);
            TMBresultado.setText("TMB: " + (int)TMB);
            TMBatividaderesultado.setText("TMB com atividade física: " + TMBatividade);
            TMBresultado.setVisibility(View.VISIBLE);
            TMBatividaderesultado.setVisibility(View.VISIBLE);

        }
        else{
            float TMB = (float) (665 + (9.6 * pesoValor) + (1.8 * alturaValor) - (4.7 * idadeValor));
            float TMBatividade = (float) (nivelAtividade * (665 + (9.6 * pesoValor) + (1.8 * alturaValor) - (4.7 * idadeValor)));
            System.out.println("Feminino TMB: " + TMB);
            System.out.println("Feminino TMB com atividade física: " + TMBatividade);
            TMBresultado.setText("TMB: " + (int)TMB);
            TMBatividaderesultado.setText("TMB com atividade física: " + TMBatividade);
            TMBresultado.setVisibility(View.VISIBLE);
            TMBatividaderesultado.setVisibility(View.VISIBLE);
        }
    }

    private float checarNivelAtividade() {
        float multiplicador = 1.0F;
        if(nivelAtividade.equals("Nenhum")){
            return multiplicador = 1.0f;
        }
        else if(nivelAtividade.equals("Pouco(1 à 3 dias/semana)")){
            return multiplicador = 1.375f;
        }
        else if(nivelAtividade.equals("Moderado(3 à 5 dias/semana)")){
            return multiplicador = 1.55f;
        }
        else if(nivelAtividade.equals("Intenso(6 à 7 dias/semana)")){
            return multiplicador = 1.725f;
        }
        else if(nivelAtividade.equals("Muito Intenso(2x/dia ou muito pesado)")){
            return multiplicador = 2f;
        }
        return multiplicador;
    }


    public void inicializador(){
        altura = (EditText) findViewById(R.id.edtTextAlturaTMB);
        peso = (EditText) findViewById(R.id.edtTextPesoBfTMB);
        idade = (EditText) findViewById(R.id.edtTextIdadeTMB);
        TMBresultado = (TextView) findViewById(R.id.txtViewResultadoTMB);
        TMBatividaderesultado = (TextView) findViewById(R.id.txtViewResultadoTMBAtividade);
        radioGroupTMB = findViewById(R.id.RadiogroupTMB);
        Spinner dropdown = findViewById(R.id.spinner1);
        ArrayList<String> list = new ArrayList<String>();   //make this as field atribute
        list.add("Nenhum");
        list.add("Pouco(1 à 3 dias/semana)");
        list.add("Moderado(3 à 5 dias/semana)");
        list.add("Intenso(6 à 7 dias/semana)");
        list.add("Muito Intenso(2x/dia ou muito pesado)");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nivelAtividade =  list.get(i);
                //Toast.makeText(TMBActivity.this, "Selecionou: " + nivelAtividade, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public String checkRadioGroupSexo(View v){
        int radioId = radioGroupTMB.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        return radioButton.getText().toString();
    }

    public void voltarHome(View v){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}