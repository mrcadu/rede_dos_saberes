package com.example.cadu.rededossaberes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.cadu.rededossaberes.R;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

public class ParentShowerAdapter extends BaseAdapter {

    Map<ParseObject,ParseFile> singleImageParentPreview;
    List<ParseObject> listaParentsPost;
    Context context;

    public ParentShowerAdapter(Context context, List<ParseObject> listaParentsPost) {
        for (ParseObject object:listaParentsPost)
        {
            boolean imagemExistente = false;
            if(object.getParseFile("imagem") != null && imagemExistente == false)
            {
                singleImageParentPreview.put(object,object.getParseFile("imagem"));
                imagemExistente = true;
            }
        }
        this.listaParentsPost = listaParentsPost;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaParentsPost.size();
    }

    @Override
    public ParseObject getItem(int position) {
        return listaParentsPost.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_lista_parents,null);
        }
        ParseObject currentListData = getItem(position);
        TextView  descricaoParents = convertView.findViewById(R.id.descricaoParents);
        ImageView descricaoParentsImage = convertView.findViewById(R.id.descricaoParentsImagem);
        descricaoParents.setText(currentListData.getString("description"));
        Picasso.with(context).load(singleImageParentPreview.get(currentListData).getUrl()).fit().into(descricaoParentsImage);
        return convertView;
    }
}