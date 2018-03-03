package com.example.cadu.rededossaberes.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cadu.rededossaberes.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void logar(View view)
    {
        TextView username = findViewById(R.id.username);
        TextView senha = findViewById(R.id.Senha);
        ParseUser.logInInBackground(username.getText().toString(), senha.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e)
            {
                if(e == null)
                {
                    Toast.makeText(Login.this,"Bem vindo, " + user.getUsername(), Toast.LENGTH_SHORT).show();
                    Log.i("Login", "login realizado com sucesso");
                    if(TelaInicial.telaOrigem == "verProjetos")
                    {
                        Intent mudarActivityVerProjetos = new Intent(Login.this,VerProjetos.class);
                        startActivity(mudarActivityVerProjetos);
                    }
                    else
                    {
                        Intent mudarActivityCriarProjetos = new Intent(Login.this,CriarProjetos.class);
                        startActivity(mudarActivityCriarProjetos);
                    }
                }
                else
                {
                    Toast.makeText(Login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    Log.i("Login","Erro de login - " + e.getMessage());
                }
            }
        });
    }

}
