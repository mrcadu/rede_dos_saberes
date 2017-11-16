package com.example.cadu.rededossaberes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.View;
import android.widget.PopupWindow;

import com.example.cadu.login.LoginActionBean;

public class TelaInicial extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_buttons, menu);
        return true;
    }
    @Override
    public void onStart()
    {

        super.onStart();
    }
    public void verProjetos(View view)
    {

       LoginActionBean loginActionBean = new LoginActionBean();
        if(!loginActionBean.isUsuarioLogado())
        {

            Intent mudarActivityLogin = new Intent(this,Login.class);
            startActivity(mudarActivityLogin);
            PopupWindow popUpView = new PopupWindow(100,100);
        }
        else
        {
            Intent mudarActivityVerProjetos = new Intent(this, VerProjetos.class);
            startActivity(mudarActivityVerProjetos);
        }
    }
    public void criarProjetos(View view)
    {
        Intent mudarActivityCriarProjetos = new Intent (this, CriarProjetos.class);
        startActivity(mudarActivityCriarProjetos);
    }
}
