package com.example.cadu.rededossaberes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cadu.rededossaberes.expandableListViewElements.ParentExpandableView;
import com.parse.ParseObject;

import java.util.ArrayList;

public class Post extends AppCompatActivity {
    private ArrayList<ParentExpandableView> listaParents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listaParents = new ArrayList<>();
        setContentView(R.layout.activity_post);
    }

    public ArrayList<ParentExpandableView> getListaParents() {
        return listaParents;
    }

    public void setListaParents(ArrayList<ParentExpandableView> listaParents) {
        this.listaParents = listaParents;
    }
}
