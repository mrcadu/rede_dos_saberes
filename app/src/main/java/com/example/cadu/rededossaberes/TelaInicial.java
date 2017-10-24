package com.example.cadu.rededossaberes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.View;

public class TelaInicial extends AppCompatActivity {

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
    }
    public void ver_projetos(View view)
    {
        Intent mudarActivityVerProjetos = new Intent(this,VerProjetos.class);
        startActivity(mudarActivityVerProjetos);
    }
}
