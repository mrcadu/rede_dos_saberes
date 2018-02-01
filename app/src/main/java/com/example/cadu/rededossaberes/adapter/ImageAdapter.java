package com.example.cadu.rededossaberes.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.cadu.rededossaberes.R;
import com.parse.ParseObject;
import java.util.ArrayList;


public class ImageAdapter extends ArrayAdapter<ParseObject>
{
    private Context context;
    private ArrayList<ParseObject> objects;


    public ImageAdapter(Context context,ArrayList<ParseObject> objects) {
        super(context, 0, objects);
        this.context = context;
        this.objects = objects;
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listaImagens = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View criarProjetos = inflater.inflate(R.layout.activity_criar_projetos, parent, false);
        if(convertView == null)
        {
            listaImagens = criarProjetos.findViewById(R.id.listaImagens);
        }
        if(objects.size() > 0)
        {
            ImageView imagemPostagem = criarProjetos.findViewById(R.id.listaImagens);
        }
        return listaImagens;
    }
}
