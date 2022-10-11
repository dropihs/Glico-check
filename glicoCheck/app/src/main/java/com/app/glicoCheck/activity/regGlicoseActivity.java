package com.app.glicoCheck.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.glicoCheck.R;
import com.app.glicoCheck.Util.DAOUsuario;
import com.app.glicoCheck.model.GlicoseUser;
import com.app.glicoCheck.model.Usuario;

public class regGlicoseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_glicose);
       final EditText glicose = findViewById(R.id.editTextGlicose);
       final EditText day = findViewById(R.id.editTextDay);
        Button btnRegistrar = findViewById(R.id.botaoRegistrar);
        DAOUsuario dao = new DAOUsuario();
        btnRegistrar.setOnClickListener(v->{
            GlicoseUser user = new GlicoseUser(glicose.getText().toString(),day.getText().toString());
            dao.add(user).addOnSuccessListener(suc ->{
                Toast.makeText(this, "Registrado com Sucesso", Toast.LENGTH_SHORT).show();
            });
        });
    }
    public void voltarHome(View v){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}