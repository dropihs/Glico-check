package com.app.glicoCheck.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.glicoCheck.R;

public class HistoricoGlicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_glic);
    }
    public void voltarHistGlic(View v){
        Intent i = new Intent(this, regGlicoseActivity.class);
        startActivity(i);
    }
    public void goHistGlicose(View v){
        Intent i = new Intent(this, HistoricoGlicActivity.class);
        startActivity(i);
    }
}