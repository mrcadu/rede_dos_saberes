package com.example.cadu.rededossaberes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.cadu.rededossaberes.R;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualizarPostsAdapter extends BaseAdapter {

    Map<ParseObject,ParseFile> singleImageParentPreview;
    Map<ParseObject,List<ParseObject>> listaChilds;
    List<ParseObject> listaParentsPost;
    Context context;

    public VisualizarPostsAdapter(Context context, List<ParseObject> listaParentsPost) {
        singleImageParentPreview = new HashMap<>();
        this.listaParentsPost = new ArrayList<>();
        listaChilds = new HashMap<>();
        for (ParseObject object:listaParentsPost)
        {
            if(object.getParseFile("imagem") != null)
            {
                singleImageParentPreview.put(object,object.getParseFile("imagem"));
            }
            if(object.getList("childs") != null)
            {
                List<ParseObject> parseObjectsChilds = object.getList("childs");
                listaChilds.put(object, parseObjectsChilds);
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
        TextView  descricaoParent = convertView.findViewById(R.id.descricaoParent);
        ImageView descricaoParentsImage = convertView.findViewById(R.id.descricaoParentsImagem);
        TextView descricaoChilds = convertView.findViewById(R.id.descricaoChilds);
        descricaoParent.setText(currentListData.getString("description"));
        descricaoParent.append("\n");
        descricaoChilds.setText("");
        for (ParseObject object: listaChilds.get(currentListData))
        {
            try {
                object.fetchIfNeeded();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            descricaoChilds.append(object.getString("description") + "\n");
        }
        if(singleImageParentPreview.get(currentListData) != null)
        {
            Picasso.with(this.context).load(singleImageParentPreview.get(currentListData).getUrl()).fit().into(descricaoParentsImage);

        }
        else
        {
            descricaoParentsImage.setVisibility(View.GONE);
        }
        return convertView;
    }
}