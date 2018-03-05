package com.example.cadu.rededossaberes.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cadu.rededossaberes.R;
import com.example.cadu.rededossaberes.adapter.ParentShowerAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class VerProjetos extends ToolbarActivity {

    ListView listView;
    Context context = VerProjetos.this;
    ParentShowerAdapter adapter;
    ArrayList<ParseObject> listaPostagens = new ArrayList();
    static ParseObject currentPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_projetos);
        findViewById(R.id.posts).setVisibility(View.GONE);
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
                                           adapter = new ParentShowerAdapter(context, listaPostagens);
                                           listView.setAdapter(adapter);
                                       }
                                       else
                                           {
                                               Log.d("post",e.getMessage());
                                           }
                                   }
                               });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                currentPost = listaPostagens.get(position);
                Intent intent = new Intent(context,VisualizarPost.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.waiting).setVisibility(View.GONE);
        findViewById(R.id.posts).setVisibility(View.VISIBLE);
    }
}