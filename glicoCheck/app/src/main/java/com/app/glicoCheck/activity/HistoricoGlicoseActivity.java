package com.app.glicoCheck.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.glicoCheck.R;
import com.app.glicoCheck.model.GlicoseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoricoGlicoseActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    //alterar para Glicose dps
    private DatabaseReference root = db.getReference().child(userUid);
    private MyAdapter adapter;
    private ArrayList<GlicoseUser> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_glicose);
        recyclerView = findViewById(R.id.recyclerGlicose);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new MyAdapter(this,list);
        recyclerView.setAdapter(adapter);
        //codigo delete
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //now delete
                list.remove(position);
                //then notify
                adapter.notifyItemRemoved(position);
            }
        });
        //fim codigo

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    GlicoseUser glicoseUser = dataSnapshot.getValue(GlicoseUser.class);
                    list.add(glicoseUser);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void voltarRegGlicose(View v){
        Intent i = new Intent(this, regGlicoseActivity.class);
        startActivity(i);
    }
}