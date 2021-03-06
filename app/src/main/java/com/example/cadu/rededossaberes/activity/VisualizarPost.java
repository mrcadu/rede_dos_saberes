package com.example.cadu.rededossaberes.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.example.cadu.rededossaberes.R;
import com.example.cadu.rededossaberes.adapter.VisualizarPostsAdapter;
import com.parse.ParseObject;
import java.util.List;

public class VisualizarPost extends ToolbarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_post);
        TextView nomePost = findViewById(R.id.nomePost);
        nomePost.setText(VerProjetos.currentPost.getString("name"));
        final ListView listViewParents = findViewById(R.id.listaParents);
        List<ParseObject> parents = VerProjetos.currentPost.getList("parents");
        listViewParents.setAdapter(new VisualizarPostsAdapter(VisualizarPost.this,parents));
    }
}
