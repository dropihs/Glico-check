package com.app.glicoCheck.Util;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoBd {
    private static FirebaseAuth auth;

    public static FirebaseAuth Firebaseautenticacao(){
        if (auth == null){
            auth = FirebaseAuth.getInstance();

        }
        return auth;
    }

}
