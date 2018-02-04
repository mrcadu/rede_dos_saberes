package com.example.cadu.rededossaberes.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.cadu.rededossaberes.R;
import com.parse.ParseObject;
import java.util.List;

public class ChildsAttachedToParentAdapter extends BaseAdapter{

    List<ParseObject> listaChildsPost;
    Context context;

    public ChildsAttachedToParentAdapter(Context context, List<ParseObject> listaChildsPost) {
        this.listaChildsPost = listaChildsPost;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaChildsPost.size();
    }

    @Override
    public ParseObject getItem(int position) {
        return listaChildsPost.get(position);
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
            convertView = inflater.inflate(R.layout.activity_lista_childs,null);
        }
        ParseObject currentListData = getItem(position);
        TextView descricaoChild = convertView.findViewById(R.id.textoChild);
        descricaoChild.setText(currentListData.getString("description"));
        return convertView;
    }
}
