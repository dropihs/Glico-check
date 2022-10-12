package com.app.glicoCheck.Util;
import com.app.glicoCheck.model.GlicoseUser;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DAOUsuario {
    private DatabaseReference databaseReference;
    FirebaseDatabase db = FirebaseDatabase.getInstance();

    private List<GlicoseUser> glicoseUserList = new ArrayList<>();


    public DAOUsuario(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(GlicoseUser.class.getSimpleName());


    }

    public Task<Void> add(GlicoseUser user){
        //if (user == null) //throw exception
        return databaseReference.push().setValue(user);
    }

}
