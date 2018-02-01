package com.example.cadu.rededossaberes.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import com.example.cadu.rededossaberes.R;
import com.example.cadu.rededossaberes.adapter.VisualizarPostsAdapter;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import java.util.ArrayList;
import java.util.List;

public class VerProjetos extends AppCompatActivity {

    ListView listView;
    Context context = VerProjetos.this;
    VisualizarPostsAdapter adapter;
    ArrayList<ParseObject> listaPostagens = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_projetos);
        Toolbar toolbarCadastro = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarCadastro);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        listView = findViewById(R.id.posts);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("post");
        query.orderByAscending("createdAt");
        query.include("parents");
        query.findInBackground(new FindCallback<ParseObject>() {
                                   @Override
                                   public void done(List<ParseObject> objects, ParseException e)
                                   {
                                       if(e == null) {
                                           for (ParseObject object : objects) {
                                               listaPostagens.add(object);
                                           }
                                           adapter = new VisualizarPostsAdapter(context, listaPostagens);
                                           listView.setAdapter(adapter);
                                       }
                                       else
                                           {
                                               Log.d("post",e.getMessage());
                                           }
                                   }
                               });
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
                Intent mudarActivityLogin = new Intent(VerProjetos.this,Login.class);
                startActivity(mudarActivityLogin);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void pegarElementosParse()
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e == null)
                {
                    listaPostagens.add(object);
                }
                else
                {
                    Log.d("post",e.getMessage());
                }
            }
        });
    }

}