package com.app.glicoCheck.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.app.glicoCheck.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

public class PerfilActivity extends AppCompatActivity {
    TextView nome,peso,altura,email,sexo;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        inicializar();
    }


    public void voltarHome(View v){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    protected void onStart(){
        super.onStart();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("Usuarios").document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot != null){
                    nome.setText(documentSnapshot.getString("nome"));
                    peso.setText(documentSnapshot.getString("peso"));
                    altura.setText(documentSnapshot.getString("altura"));
                    email.setText(documentSnapshot.getString("email"));
                    sexo.setText(documentSnapshot.getString("sexo"));//Chave
                }
            }
        });
    }

    private void inicializar(){
        nome =  findViewById(R.id.txtNomeUser);
        peso = findViewById(R.id.txtPesoUser);
        altura = findViewById(R.id.txtAlturaUser);
        email = findViewById(R.id.txtEmailUser);
        sexo = findViewById(R.id.txtSexoUser);
    }

}