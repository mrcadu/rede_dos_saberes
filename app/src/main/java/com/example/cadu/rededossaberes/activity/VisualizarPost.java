package com.example.cadu.rededossaberes.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.cadu.rededossaberes.ActionBarSingleton;
import com.example.cadu.rededossaberes.R;
import com.example.cadu.rededossaberes.adapter.VisualizarPostsAdapter;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class VisualizarPost extends AppCompatActivity {
    ArrayList<ParseObject> arrayChilds;
    ArrayList<ParseObject> arrayParents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_post);
        Toolbar toolbar = findViewById(R.id.toolbar);
        ActionBarSingleton.getInstance().setarActionBar(toolbar,VisualizarPost.this);
        final ListView listViewParents = findViewById(R.id.listaParents);
        List<ParseObject> parents = VerProjetos.currentPost.getList("parents");
        listViewParents.setAdapter(new VisualizarPostsAdapter(VisualizarPost.this,parents));
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
                Intent mudarActivityLogin = new Intent(VisualizarPost.this,Login.class);
                startActivity(mudarActivityLogin);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
