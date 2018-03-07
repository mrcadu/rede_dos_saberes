package com.example.cadu.rededossaberes.adapter;

import android.content.Context;
import android.util.Log;
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
import java.util.List;

public class ParentShowerAdapter extends BaseAdapter {

    private List<ParseObject> listaPosts;
    Context context;

    public ParentShowerAdapter(Context context, List<ParseObject> listaPosts) {
        this.listaPosts = listaPosts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaPosts.size();
    }

    @Override
    public ParseObject getItem(int position) {
        return listaPosts.get(position);
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
            convertView = inflater.inflate(R.layout.activity_post,null);
        }
        ParseObject currentListData = getItem(position);
        TextView descricao = convertView.findViewById(R.id.descricao);
        descricao.setText(currentListData.getString("name"));
        final ImageView imagemDescricao = convertView.findViewById(R.id.imagemDescricao);
        List<ParseObject> listaParentsPost = currentListData.getList("parents");
        boolean firstPicture = false;
        for(ParseObject parentPost : listaParentsPost)
        {
            try {
                parentPost.fetchIfNeeded();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(parentPost.getParseFile("imagem") != null && firstPicture == false)
            {
                ParseFile imagemDescricaoParse = parentPost.getParseFile("imagem");
                ViewGroup.LayoutParams params = imagemDescricao.getLayoutParams();
                Picasso.with(this.context).load(imagemDescricaoParse.getUrl()).fit().into(imagemDescricao);
                imagemDescricao.refreshDrawableState();
                Log.d("teste", "funcionou");
                firstPicture = true;
            }
        }
        return convertView;
    }
    public List<ParseObject> getListaPosts() {
        return listaPosts;
    }

    public void setListaPosts(List<ParseObject> listaPosts) {
        this.listaPosts = listaPosts;
    }
}