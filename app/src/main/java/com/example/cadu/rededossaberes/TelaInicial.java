package com.example.cadu.rededossaberes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.View;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;

public class TelaInicial extends AppCompatActivity {

    private FirebaseAuth autenticador;
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_buttons, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        autenticador = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart()
    {

        super.onStart();
        FirebaseUser currentUser = autenticador.getCurrentUser();
        updateUI(currentUser);
    }
    public void verProjetos(View view)
    {
        Intent mudarActivityVerProjetos = new Intent(this,VerProjetos.class);
        startActivity(mudarActivityVerProjetos);
    }
    public void criarProjetos(View view)
    {
        Intent mudarActivityCriarProjetos = new Intent (this, CriarProjetos.class);
        startActivity(mudarActivityCriarProjetos);
    }
}
