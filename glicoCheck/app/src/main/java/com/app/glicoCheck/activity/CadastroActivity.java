package com.app.glicoCheck.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.glicoCheck.R;
import com.app.glicoCheck.Util.ConfiguracaoBd;
import com.app.glicoCheck.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadastroActivity extends AppCompatActivity {

    Usuario usuario;
    FirebaseAuth autenticacao;
    EditText campoNome, campoEmail, campoSenha,campoPeso,campoAltura;
    Button botaoCadastrar;
    String usuarioId;
    RadioGroup radioGroup;
    RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inicializar();
    }

    public void validarCampos(View v){
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if(!email.isEmpty()){
            if(!nome.isEmpty()){
                if(!senha.isEmpty()){
                    usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setSenha(senha);
                    //cadastro User
                    cadastrarUsuario();
                }
                else{
                    Toast.makeText(CadastroActivity.this, "Preencha o campo Senha", Toast.LENGTH_SHORT).show();

                }

            }else{
                Toast.makeText(CadastroActivity.this, "Preencha o campo Nome", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(CadastroActivity.this, "Preencha o campo Email",Toast.LENGTH_SHORT).show();
        }
    }
    private void cadastrarUsuario() {
        autenticacao = ConfiguracaoBd.Firebaseautenticacao();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    SalvarDadosUsuario();

                    Toast.makeText(CadastroActivity.this, "Sucesso", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Toast.makeText(CadastroActivity.this, "opa, deu ruim", Toast.LENGTH_SHORT).show();
                    String excecao = "";
                    try{
                        throw  task.getException();
                    }catch(FirebaseAuthWeakPasswordException e){ //Senha fraca
                        excecao = "Digite uma senha mais forte";
                    }catch(FirebaseAuthInvalidCredentialsException e){ //Email Valido
                        excecao = "Digite um email valido";
                    }catch(FirebaseAuthUserCollisionException e){
                        excecao = "Essa conta ja existe";
                    }catch (Exception e){
                        excecao = "Erro ao cadastrar usuario" + e.getMessage();
                        e.printStackTrace(); //Pegar o log do error
                    }
                    Toast.makeText(CadastroActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void SalvarDadosUsuario(){
        String nome = campoNome.getText().toString();
        String peso = campoPeso.getText().toString();
        String altura = campoAltura.getText().toString();
        String email = campoEmail.getText().toString();
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        String sexo = radioButton.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String,Object> usuarios = new HashMap<>();

        usuarios.put("nome",nome);
        usuarios.put("peso",peso);
        usuarios.put("altura",altura);
        usuarios.put("email",email);
        usuarios.put("sexo",sexo);

        usuarioId = FirebaseAuth.getInstance().getCurrentUser().getUid(); //obtem o usuario atual e pega o Uid de cada user
        DocumentReference documentReference = db.collection("Usuarios").document(usuarioId); //Cada Usuario terá um documento especifico
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db,","Sucesso ao salvar os dados");
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("db_error", "Erro ao salvar os dados");
            }
        });


    }

    public void voltarLogin(View v){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }


    private void inicializar(){
        campoNome = findViewById(R.id.editTextNomeCadastro);
        campoPeso = findViewById(R.id.editTextPesoCadastro);
        campoAltura = findViewById(R.id.editTextAlturaCadastro);
        campoEmail = findViewById(R.id.editTextEmailLogin);
        campoSenha = findViewById(R.id.editTextSenhaLogin);
        radioGroup = findViewById(R.id.RadioGroupSexo);
        //masculino = findViewById(R.id.radioBtnMasculino);
        //feminino = findViewById(R.id.radioBtnFeminino);
        botaoCadastrar = findViewById(R.id.botaoCadastrar);

    }
    public String checkRadioGroupSexo(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        return radioButton.getText().toString();
    }
}