package com.app.glicoCheck.Util;
import com.app.glicoCheck.model.GlicoseUser;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOUsuario {
    private DatabaseReference databaseReference;
    String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();


    public DAOUsuario(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        //Alterar para Glicose Dps
        databaseReference = db.getReference().child(userUid);
    }

    public Task<Void> add(GlicoseUser user){
        //if (user == null) //throw exception
        return databaseReference.push().setValue(user);
    }

}
