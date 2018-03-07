package com.example.cadu.rededossaberes.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.cadu.rededossaberes.R;
import com.parse.Parse;
import com.parse.ParseUser;

public class TelaInicial extends AppCompatActivity {
    public static String telaOrigem;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        Parse.initialize(this);
    }
    @Override
    public void onStart()
    {

        super.onStart();
    }
    public void verProjetos(View view)
    {
        if(ParseUser.getCurrentUser() == null)
        {
            Intent mudarActivityCadastro = new Intent(this,Cadastro.class);
            telaOrigem = "verProjetos";
            startActivity(mudarActivityCadastro);
        }
        else
        {
            Intent mudarActivityVerProjetos = new Intent(this, VerProjetos.class);
            startActivity(mudarActivityVerProjetos);
        }
    }
    public void criarProjetos(View view)
    {
        if(ParseUser.getCurrentUser() == null)
        {
            Intent mudarActivityCadastro = new Intent(this,Cadastro.class);
            telaOrigem = "criarProjetos";
            startActivity(mudarActivityCadastro);
        }
        else {
            Intent mudarActivityCriarProjetos = new Intent(this, CriarProjetos.class);
            startActivity(mudarActivityCriarProjetos);
        }
    }
    public void sair(View view)
    {
        ParseUser.logOutInBackground();
    }
}
