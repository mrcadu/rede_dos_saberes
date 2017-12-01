package com.example.cadu.rededossaberes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Cadastro extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        TextView loginSemConta = findViewById(R.id.loginSemConta);
        if(TelaInicial.telaOrigem == "verProjetos")
        {
            loginSemConta.setVisibility(View.VISIBLE);
            SpannableString content = new SpannableString("Continuar sem Cadastro");
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            loginSemConta.setText(content);
        }
        else
        {
            loginSemConta.setVisibility(View.INVISIBLE);
        }
    }
    public void criarConta(View view)
    {
        EditText email =  findViewById(R.id.Email);
        EditText senha =  findViewById(R.id.Senha);
        ParseUser usuario = new ParseUser();
        usuario.setUsername("cadu");
        usuario.setEmail(email.getText().toString());
        usuario.setPassword(senha.getText().toString());
        usuario.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null)
                {
                    Log.i("autenticação","Sucesso ao registrar usuário");
                    if(TelaInicial.telaOrigem == "verProjetos")
                    {
                        Intent mudarActivityVerProjetos = new Intent(Cadastro.this, VerProjetos.class);
                        startActivity(mudarActivityVerProjetos);
                    }
                    else
                    {
                        Intent mudarActivityCriarProjetos = new Intent(Cadastro.this,CriarProjetos.class);
                        startActivity(mudarActivityCriarProjetos);
                    }
                }
                else
                {
                    Log.i("autenticação","Falha ao registrar usuário - " + e.getMessage());
                }
            }
        });
    }
    public void continuarSemConta(View view)
    {
        Intent mudarActivityVerProjetos = new Intent(this,VerProjetos.class);
        startActivity(mudarActivityVerProjetos);
    }
    public void fazerLogin(View view)
    {
        Intent mudarActivityLogin = new Intent(this,Login.class);
        startActivity(mudarActivityLogin);
    }
}
