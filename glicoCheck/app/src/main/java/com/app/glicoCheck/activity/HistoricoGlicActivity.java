package com.app.glicoCheck.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.glicoCheck.R;
import com.app.glicoCheck.Util.DAOUsuario;
import com.app.glicoCheck.model.GlicoseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class HistoricoGlicActivity extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_glic);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_Glicose);

        new DAOUsuario().readGlicose(new DAOUsuario.DataStatus() {
            @Override
            public void DataIsLoaded(List<GlicoseUser> glicoseUserList, List<String> keys) {
                new RecyclerView_config().setConfig(mRecyclerView, HistoricoGlicActivity.this,glicoseUserList,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });


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