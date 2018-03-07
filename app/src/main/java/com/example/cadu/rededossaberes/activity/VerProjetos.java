package com.example.cadu.rededossaberes.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.cadu.rededossaberes.R;
import com.example.cadu.rededossaberes.adapter.ParentShowerAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class VerProjetos extends ToolbarActivity {

    GridView gridView;
    Context context = VerProjetos.this;
    ParentShowerAdapter adapter;
    ArrayList<ParseObject> listaPostagens = new ArrayList();
    static ParseObject currentPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_projetos);
        gridView = findViewById(R.id.posts);
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
                                           adapter = new ParentShowerAdapter(context, listaPostagens);
                                           gridView.setAdapter(adapter);
                                       }
                                       else
                                           {
                                               Log.d("post",e.getMessage());
                                           }
                                   }
                               });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                currentPost = listaPostagens.get(position);
                Intent intent = new Intent(context,VisualizarPost.class);
                startActivity(intent);
            }
        });
    }

    public void BuscarPosts(View view)
    {
        TextView textoBusca = findViewById(R.id.buscaTexto) ;
        ParseQuery<ParseObject> query = ParseQuery.getQuery("post");
        query.whereContains("name",textoBusca.getText().toString());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e)
            {
                if(e == null) {
                    listaPostagens = new ArrayList<>();
                    for (ParseObject object : objects) {
                        listaPostagens.add(object);
                    }
                    adapter.setListaPosts(listaPostagens);
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    Log.d("post",e.getMessage());
                }
            }
        });
    }
}