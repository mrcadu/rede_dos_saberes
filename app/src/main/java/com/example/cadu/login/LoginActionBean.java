package com.example.cadu.login;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;

public class LoginActionBean
{
    FirebaseAuth autenticador;
    public void criarContaComEmailESenha(final String email,final String senha)
    {
        autenticador = FirebaseAuth.getInstance();
        autenticador.createUserWithEmailAndPassword(email, senha).addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Criação de usuário bem sucedida!");
                } else {
                    Log.w(TAG, "Não foi possível criar um usuário", task.getException());
                }
            }
        });
    }
    public void logarComEmaileSenha(String email,String senha)
    {
        autenticador = FirebaseAuth.getInstance();
        autenticador.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Login efetuado com sucesso");
                        } else {
                            Log.w(TAG, "Não foi possível fazer login", task.getException());
                        }
                    }
                });
    }
    public boolean isUsuarioLogado()
    {
        autenticador = FirebaseAuth.getInstance();
        if(autenticador.getCurrentUser() == null)
        {
            return false;
        }
        return true;
    }


}
