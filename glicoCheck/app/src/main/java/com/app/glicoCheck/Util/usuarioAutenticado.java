package com.app.glicoCheck.Util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class usuarioAutenticado {

    public static FirebaseUser usuarioLogado(){
        FirebaseAuth usuario = ConfiguracaoBd.Firebaseautenticacao();
        return usuario.getCurrentUser();
    }
}
