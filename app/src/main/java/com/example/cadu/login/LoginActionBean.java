package com.example.cadu.login;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActionBean
{
    FirebaseAuth autenticador;
    public void criarContaLogando(String email, String senha)
    {
        autenticador.createUserWithEmailAndPassword(email,senha);
        autenticador.signInWithEmailAndPassword(email,senha);
    }
    public boolean isUsuarioLogado()
    {
        if(autenticador.getCurrentUser() != null)
        {
            return true;
        }
        return false;
    }

}
