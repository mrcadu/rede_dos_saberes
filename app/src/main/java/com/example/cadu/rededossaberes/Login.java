package com.example.cadu.rededossaberes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.cadu.login.LoginActionBean;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void criarConta(View view)
    {
        LoginActionBean loginController = new LoginActionBean();
        EditText email = (EditText) findViewById(R.id.Email);
        EditText senha = (EditText) findViewById(R.id.Senha);
        loginController.criarContaComEmailESenha(email.getText().toString(),senha.getText().toString());
        Intent mudarActivityVerProjetos = new Intent(this,VerProjetos.class);
        startActivity(mudarActivityVerProjetos);
    }
    public void continuarSemConta(View view)
    {
        Intent mudarActivityVerProjetos = new Intent(this,VerProjetos.class);
        startActivity(mudarActivityVerProjetos);
    }
}
