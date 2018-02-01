package com.example.cadu.rededossaberes.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.cadu.rededossaberes.expandableListViewElements.ChildExpandableView;
import com.example.cadu.rededossaberes.fragment.NomearPostFragment;
import com.example.cadu.rededossaberes.expandableListViewElements.ParentExpandableView;
import com.example.cadu.rededossaberes.R;
import com.example.cadu.rededossaberes.adapter.ExpandableListAdapter;
import com.example.cadu.rededossaberes.fragment.InputTextManagerFragment;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CriarProjetos extends AppCompatActivity {
    ExpandableListView expandableListView;
    static List<ParentExpandableView> parentObjects = new ArrayList<>();
    int currentParent;
    int currentChild;
    String perspective;
    final ExpandableListAdapter adapter = new ExpandableListAdapter(this, getData());
    FragmentManager manager = getSupportFragmentManager();
    ArrayList<Integer> listaChild = new ArrayList<>();
    int head;
    public static String postName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_projetos);
        LayoutInflater inflater = getLayoutInflater();
        expandableListView = (ExpandableListView) findViewById(R.id.lvExp);
        expandableListView.setOnGroupExpandListener(onGroupExpandListenser);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                currentChild = i1 - 1;
                if (perspective != null) {
                    if (perspective.equals("remove")) {
                        List<ChildExpandableView> childs = parentObjects.get(i).getChildObjects();
                        childs.remove(currentChild);
                        adapter.notifyDataSetChanged();
                        perspective = "";
                    }
                }
                return false;
            }
        });
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                parentObjects.get(i);
            }
        });
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                currentParent = i;
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_buttons, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sair:
                ParseUser.logOut();
                Intent mudarActivityLogin = new Intent(CriarProjetos.this, Login.class);
                startActivity(mudarActivityLogin);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    ExpandableListView.OnGroupExpandListener onGroupExpandListenser = new ExpandableListView.OnGroupExpandListener() {
        int previousGroup = -1;

        @Override
        public void onGroupExpand(int groupPosition) {
            if (groupPosition != previousGroup)
                expandableListView.collapseGroup(previousGroup);
            previousGroup = groupPosition;
        }
    };

    //Sample data for expandable list view.
    public List<ParentExpandableView> getData() {
        if (parentObjects.size() == 0) {
            ChildExpandableView childExpandableView1;

            addParent("Ingredientes, materiais e instrumentos", "Feijão");
            addParent("Primeiras instruções", "Cozinhe o feijão");
            addParent("Acabamentos e exibição", "Comer o feijão");

        /*Primeiro parent*/
            addChild(parentObjects.get(0), "Arroz");

        /*Segundo parent*/
            addChild(parentObjects.get(1), "Cozinhe o arroz");

        /*Terceiro parent*/
            addChild(parentObjects.get(2), "Comer o arroz");
        }
        return parentObjects;
    }

    private List<ChildExpandableView> getChildren(ParentExpandableView parentExpandableView)

    {
        for (ParentExpandableView parent : parentObjects) {
            if (parent.getDescription().equals(parentExpandableView)) {
                return parent.getChildObjects();
            }
        }
        return null;
    }

    private void addParent(String description, List<ChildExpandableView> childExpandableViews) {
        ParentExpandableView parentExpandableView = new ParentExpandableView(description, childExpandableViews);
        parentObjects.add(parentExpandableView);
    }

    private void addParent(String description, String descriptionChild) {
        ParentExpandableView parentExpandableView = new ParentExpandableView(description, descriptionChild);
        parentObjects.add(parentExpandableView);
    }

    private void addChild(ParentExpandableView parentExpandableView, ChildExpandableView childExpandableView) {
        for (ParentExpandableView parent : parentObjects) {
            if (parentExpandableView.getDescription() == parent.getDescription()) {
                parent.getChildObjects().add(childExpandableView);
            }
        }
    }

    private void addChild(ParentExpandableView parentExpandableView, String descriptionChild) {
        ChildExpandableView childExpandableView = new ChildExpandableView(descriptionChild);
        for (ParentExpandableView parent : parentObjects) {
            if (parentExpandableView.getDescription() == parent.getDescription()) {
                parent.getChildObjects().add(childExpandableView);
            }
        }
    }

    private void addChilds(ParentExpandableView parentExpandableView, List<ChildExpandableView> childExpandableViews) {
        for (ParentExpandableView parent : parentObjects) {
            if (parentExpandableView.getDescription() == parent.getDescription()) {
                for (ChildExpandableView child : childExpandableViews) {
                    parent.getChildObjects().add(child);
                }
            }
        }
    }

    public void removerParent(View view) {
        parentObjects.remove(currentParent);
        adapter.notifyDataSetChanged();
    }

    public void adicionarChild(View view) {
        ChildExpandableView newChild = new ChildExpandableView("");
        parentObjects.get(currentParent).getChildObjects().add(newChild);
        adapter.notifyDataSetChanged();
        Toast.makeText(CriarProjetos.this, "Passo adicionado com sucesso", Toast.LENGTH_SHORT).show();
    }

    public void editarChild(View view) {
        for (int i = 0; i < expandableListView.getChildCount(); i++) {
            View childView = expandableListView.getChildAt(i);
            if (childView.findViewById(R.id.lblListItemEditable) != null) {
                View editable = childView.findViewById(R.id.lblListItemEditable);
                editable.setVisibility(View.VISIBLE);
                View notEditable = childView.findViewById(R.id.lblListItem);
                notEditable.setVisibility(View.GONE);
                listaChild.add(i);
            }
            if (childView.findViewById(R.id.botaoSend) != null) {
                childView.findViewById(R.id.botaoSend).setVisibility(View.VISIBLE);
                head = i;
            }
        }
        Toast.makeText(CriarProjetos.this, "Modifique os passos e aperte no botão de confirmar para salvar as mudanças", Toast.LENGTH_SHORT).show();
    }

    public void excluirChild(View view) {
        perspective = "remove";
        Toast.makeText(CriarProjetos.this, "Clique num passo para exclui-lo", Toast.LENGTH_SHORT).show();
    }

    public void adicionarParent(View view) {
        InputTextManagerFragment dFragment = new InputTextManagerFragment();
        ParentExpandableView newParent = new ParentExpandableView("");
        parentObjects.add(newParent);
        dFragment.show(manager, "alert dialog fragment");
        Toast.makeText(CriarProjetos.this, "Instrução salva com sucesso", Toast.LENGTH_SHORT).show();
    }

    public static void setLastParentText(EditText editableText) {
        String textParent = editableText.getText().toString();
        parentObjects.get(parentObjects.size() - 1).setDescription(textParent);
    }

    public static void removeLastElement() {
        parentObjects.remove(parentObjects.size() - 1);
    }

    public void atualizar(View view) {
        adapter.notifyDataSetChanged();
    }

    public void adicionarFoto(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String descricao = "";
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri localImagemSelecionada = data.getData();
            descricao = localImagemSelecionada.toString();
            try {
                byte[] byteArray;
                Bitmap imagem = MediaStore.Images.Media.getBitmap(getContentResolver(), localImagemSelecionada);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imagem.compress(Bitmap.CompressFormat.PNG, 75, stream);
                byteArray = stream.toByteArray();
                if(byteArray!= null)
                {
                    parentObjects.get(currentParent).getListaImagens().add(byteArray);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(CriarProjetos.this, "Foto adicionada com sucesso", Toast.LENGTH_SHORT).show();
        }
        TextView listaTextoImagens = findViewById(R.id.listaImagens);
        String[] descricaoSplitado = descricao.split("/");
        listaTextoImagens.append(descricaoSplitado[descricaoSplitado.length - 1] + "\n");
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void enviarObjetos(View view) {
        NomearPostFragment dFragment = new NomearPostFragment();
        dFragment.show(manager, "alert dialog fragment");
    }

    public void realmenteEnviarObjetos(View view) {
        ArrayList<ParseObject> listaParentParse = new ArrayList<>();
        for (ParentExpandableView parent : parentObjects) {
            ArrayList<ParseObject> listaChildParse = new ArrayList<>();
            for (ChildExpandableView child : parent.getChildObjects()) {
                ParseObject childParse = new ParseObject("child");
                childParse.put("description", child.getDescription());
                listaChildParse.add(childParse);
                childParse.saveInBackground();
            }
            ParseObject parentParse = new ParseObject("parent");
            parentParse.put("description", parent.getDescription());
            parentParse.addAllUnique("childs", listaChildParse);
            for (byte[] imagem : parent.getListaImagens()) {
                ParseFile parseImage = new ParseFile("imagem.png", imagem);
                parentParse.put("imagem", parseImage);
            }
            listaParentParse.add(parentParse);
            parentParse.saveInBackground();
        }
        ParseObject post = new ParseObject("post" + "");
        if (postName == null) {
            postName = "";
        }
        post.put("name", postName);
        post.addAllUnique("parents", listaParentParse);
        post.saveInBackground();
        Toast.makeText(CriarProjetos.this, "Post feito com sucesso", Toast.LENGTH_SHORT).show();
        Intent mudarActivityMain = new Intent(this, TelaInicial.class);
        startActivity(mudarActivityMain);
    }
    public void aplicarMudancas(View view)
    {
        for(int i = 0;i < parentObjects.get(currentParent).getChildObjects().size();i++)
        {
            EditText descriptionView = expandableListView.getChildAt(listaChild.get(i)).findViewById(R.id.lblListItemEditable);
            String description = descriptionView.getText().toString();
            parentObjects.get(currentParent).getChildObjects().get(i).setDescription(description);
            adapter.notifyDataSetChanged();
            expandableListView.getChildAt(listaChild.get(i)).findViewById(R.id.lblListItem).setVisibility(View.VISIBLE);
            descriptionView.setVisibility(View.GONE);
        }
        expandableListView.getChildAt(head).findViewById(R.id.botaoSend).setVisibility(View.GONE);
        Toast.makeText(CriarProjetos.this,"As edições foram salvas com sucesso",Toast.LENGTH_SHORT).show();
    }
}