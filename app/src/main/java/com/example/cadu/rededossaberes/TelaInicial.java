package com.example.cadu.rededossaberes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.View;
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
/*        LoginActionBean loginActionBean = new LoginActionBean();
        if(!loginActionBean.isUsuarioLogado())
        {
            Intent mudarActivityLogin = new Intent(this,Login.class);
            startActivity(mudarActivityLogin);
        }*/
        Intent mudarActivityVerProjetos = new Intent(this,VerProjetos.class);
        startActivity(mudarActivityVerProjetos);
    }
    public void criarProjetos(View view)
    {
        Intent mudarActivityCriarProjetos = new Intent (this, CriarProjetos.class);
        startActivity(mudarActivityCriarProjetos);
    }
}
