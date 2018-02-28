package com.example.cadu.rededossaberes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cadu.rededossaberes.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Cadastro extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toolbar toolbarCadastro = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarCadastro);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_buttons, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() )
        {
            case R.id.action_sair:
                ParseUser.logOut();
                Intent mudarActivityLogin = new Intent(Cadastro.this,Login.class);
                startActivity(mudarActivityLogin);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void criarConta(View view)
    {
        EditText email =  findViewById(R.id.Email);
        EditText senha =  findViewById(R.id.Senha);
        EditText username = findViewById(R.id.username);
        ParseUser usuario = new ParseUser();
        usuario.setUsername(username.getText().toString());
        usuario.setEmail(email.getText().toString());
        usuario.setPassword(senha.getText().toString());
        usuario.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null)
                {
                    Toast.makeText(Cadastro.this,"Sucesso ao registrar usuário",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Cadastro.this,e.getMessage(),Toast.LENGTH_SHORT).show();
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
