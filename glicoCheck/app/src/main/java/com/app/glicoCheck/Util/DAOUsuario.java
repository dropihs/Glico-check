package com.app.glicoCheck.Util;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;

import com.app.glicoCheck.model.GlicoseUser;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DAOUsuario {
    private DatabaseReference mReferenceGlicose;
    private FirebaseDatabase mDatabase;


    private final List<GlicoseUser> glicoseUserList = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<GlicoseUser> glicoseUserList, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }


    public DAOUsuario(){
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        mReferenceGlicose = mDatabase.getReference("glicose");
    }

    public Task<Void> add(GlicoseUser user){
        //if (user == null) //throw exception
        return mReferenceGlicose.push().setValue(user);
    }



    public void readGlicose(final DataStatus dataStatus){
        mReferenceGlicose.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                glicoseUserList.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode: snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    GlicoseUser glicoseUser = keyNode.getValue(GlicoseUser.class);
                    glicoseUserList.add(glicoseUser);
                }
                dataStatus.DataIsLoaded(glicoseUserList,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



}