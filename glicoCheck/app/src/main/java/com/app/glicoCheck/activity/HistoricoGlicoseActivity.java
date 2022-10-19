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
    final ArrayList<String> statesArrayList= new ArrayList<>();
    final ArrayList<String> keysToDelete = new ArrayList<>();


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
                list.remove(position);
                adapter.notifyItemRemoved(position);
                String keyGlicose = statesArrayList.get(position);
                keysToDelete.add(keyGlicose);
                /*
                root.child(keyGlicose).removeValue();
                */

               /*
                String keyGlicose;
                keyGlicose = statesArrayList.get(position);
                System.out.println(position);
                //View child = recyclerView.getChildAt(position);
                //int itemPosition = recyclerView.getChildAdapterPosition(child);
                //System.out.println(itemPosition);
                //Deleta o item do recycler view e notifica
                //
                //Tira o item do firebase com a posicao que foi pega no recycler view

                //Remove o valor no Firebase
                root.child(keyGlicose).removeValue();
                 */
            }/*
            public void deleteRegisterFirebase(int position){
                String keyGlicose = statesArrayList.get(position);
                root.child(keyGlicose).removeValue();
            }*/
        });
        //fim codigo

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                statesArrayList.clear();
                System.out.println("lista limpa: "+statesArrayList);

                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    GlicoseUser glicoseUser = dataSnapshot.getValue(GlicoseUser.class);
                    list.add(glicoseUser);
                    //Pega a chave de registro de glicose
                    statesArrayList.add(dataSnapshot.getKey());
                }
                System.out.println("Lista preenchida: " + statesArrayList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void voltarRegGlicose(View v){
        System.out.println("Chaves para serem deletadas" + keysToDelete);
        for (int i = 0; i< keysToDelete.size(); i++){
            root.child(keysToDelete.get(i)).removeValue();
        }
        System.out.println("Chaves deletadas no firebase com sucesso" + keysToDelete);
        Intent i = new Intent(this, regGlicoseActivity.class);
        startActivity(i);


    }
}