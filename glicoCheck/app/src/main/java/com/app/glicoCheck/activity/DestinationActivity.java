package com.app.glicoCheck.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.glicoCheck.R;

public class DestinationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
    }
    public void voltarLembreteInsulina(View v){
        Intent i = new Intent(this, LembreteInsulinaActivity.class);
        startActivity(i);
    }
}