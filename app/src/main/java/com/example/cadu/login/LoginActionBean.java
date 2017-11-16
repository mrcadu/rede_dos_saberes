package com.example.cadu.login;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActionBean
{
    FirebaseAuth autenticador;
    public void criarContaLogando(String email, String senha)
    {
        autenticador = FirebaseAuth.getInstance();
        autenticador.createUserWithEmailAndPassword(email,senha);
        autenticador.signInWithEmailAndPassword(email,senha);
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
