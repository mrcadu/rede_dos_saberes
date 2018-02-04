package com.example.cadu.rededossaberes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.example.cadu.rededossaberes.ActionBarSingleton;
import com.example.cadu.rededossaberes.R;
import com.example.cadu.rededossaberes.adapter.ChildsAttachedToParentAdapter;
import com.example.cadu.rededossaberes.adapter.ParentShowerAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

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
        ActionBarSingleton actionBarSingleton = new ActionBarSingleton();
        actionBarSingleton.setarActionBar(toolbar,this);
        ParseQuery query = new ParseQuery<ParseObject>("parents");
        ListView listViewChilds = findViewById(R.id.listaChilds);
        ListView listViewParents = findViewById(R.id.listaParents);
        query.findInBackground(new FindCallback<ParseObject>()
        {
            @Override
            public void done(List<ParseObject> objects, ParseException e)
            {
                if(e == null)
                {

                }
                else
                {
                    Log.d("queryParents",e.getMessage());
                }

            }
        });
        ChildsAttachedToParentAdapter adapterChilds;
        ParentShowerAdapter adapterParents;
    }
}
