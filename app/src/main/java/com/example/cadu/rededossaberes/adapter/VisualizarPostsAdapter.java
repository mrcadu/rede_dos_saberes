package com.example.cadu.rededossaberes.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.cadu.rededossaberes.R;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import java.util.List;

public class VisualizarPostsAdapter extends BaseAdapter {

    byte[] imagemBytes;
    List<ParseObject> listaPosts;
    Context context;

    public VisualizarPostsAdapter(Context context, List<ParseObject> listaPosts) {
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
        for(ParseObject parentPost : listaParentsPost)
        {
            if(parentPost.get("imagem") == null)
            {
            }
            else
            {
                ParseFile parseFile = (ParseFile) parentPost.get("imagem");
                parseFile.getDataInBackground(new GetDataCallback(){
                    public void done(byte[] data, ParseException e) {
                        if (e == null && imagemBytes!= null) {
                            imagemBytes = data;
                        } else {
                            Log.d("backgroundPostImage",e.getMessage());
                        }
                    }
                });
                Bitmap bitmap = BitmapFactory.decodeByteArray(imagemBytes,100,100);
                imagemDescricao.setImageBitmap(bitmap);
                Log.d("teste","funcionou");
            }
        }
        return convertView;
    }
}