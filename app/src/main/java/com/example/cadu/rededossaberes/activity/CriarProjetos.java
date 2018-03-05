package com.example.cadu.rededossaberes.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.cadu.rededossaberes.utils.RightDrawableOnTouchListener;
import com.example.cadu.rededossaberes.expandableListViewElements.ChildExpandableView;
import com.example.cadu.rededossaberes.fragment.ImageShowerFragment;
import com.example.cadu.rededossaberes.fragment.NomearPostFragment;
import com.example.cadu.rededossaberes.expandableListViewElements.ParentExpandableView;
import com.example.cadu.rededossaberes.R;
import com.example.cadu.rededossaberes.adapter.ExpandableListAdapter;
import com.example.cadu.rededossaberes.fragment.InputTextManagerFragment;
import com.example.cadu.rededossaberes.fragment.TextViewEditFragment;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CriarProjetos extends ToolbarActivity {
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
    ArrayList<ParseObject> listaParentParse = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_projetos);
        expandableListView = findViewById(R.id.lvExp);
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
                if (i != currentParent) {
                    expandableListView.collapseGroup(currentParent);
                }
                currentParent = i;
                parentObjects.get(i);
            }
        });
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, final int groupPosition, long id) {
                final TextView parentEdit = v.findViewById(R.id.lblListHeader);
                parentEdit.setOnTouchListener(new RightDrawableOnTouchListener(parentEdit) {

                    @Override
                    public boolean onDrawableTouch(final MotionEvent event) {
                        Bundle bundle = new Bundle();
                        bundle.putString("currentName", parentEdit.getText().toString());
                        bundle.putInt("currentParentIndex", groupPosition);
                        TextViewEditFragment dFragment = new TextViewEditFragment();
                        dFragment.setArguments(bundle);
                        dFragment.show(manager, "alert dialog fragment");
                        return true;
                    }

                    @Override
                    public boolean onTextViewTouch(MotionEvent event) {
                        if (expandableListView.isGroupExpanded(groupPosition)) {
                            expandableListView.collapseGroup(currentParent);
                        } else {
                            expandableListView.expandGroup(groupPosition);
                        }
                        return false;
                    }
                });
                return true;
            }
        });
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
    private void addParent(String description, String descriptionChild) {
        ParentExpandableView parentExpandableView = new ParentExpandableView(description, descriptionChild);
        parentObjects.add(parentExpandableView);
    }
    private void addChild(ParentExpandableView parentExpandableView, String descriptionChild) {
        ChildExpandableView childExpandableView = new ChildExpandableView(descriptionChild);
        for (ParentExpandableView parent : parentObjects) {
            if (parentExpandableView.getDescription().equals(parent.getDescription())) {
                parent.getChildObjects().add(childExpandableView);
            }
        }
    }
    public void removerParent(View view)
    {
        parentObjects.remove(currentParent);
        expandableListView.collapseGroup(currentParent);
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
    public static void setParentText(String descricao,int parentIndex) {
        parentObjects.get(parentIndex).setDescription(descricao);
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
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri localImagemSelecionada = data.getData();
            try {
                if(parentObjects.get(currentParent).getImagemDescription() != null)
                {
                    Toast.makeText(CriarProjetos.this, "A última imagem foi substituída pela atual", Toast.LENGTH_SHORT).show();
                }
                byte[] byteArray;
                Bitmap imagem = MediaStore.Images.Media.getBitmap(getContentResolver(), localImagemSelecionada);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imagem.compress(Bitmap.CompressFormat.PNG, 75, stream);
                byteArray = stream.toByteArray();
                if(byteArray != null)
                {
                    parentObjects.get(currentParent).setImagemDescription(byteArray);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(CriarProjetos.this, "Foto adicionada com sucesso", Toast.LENGTH_SHORT).show();
        }
            adapter.isFotoPresente().put(parentObjects.get(currentParent),Boolean.TRUE);
            adapter.notifyDataSetChanged();
            super.onActivityResult(requestCode, resultCode, data);

    }

    public void enviarObjetos(View view) {
        NomearPostFragment dFragment = new NomearPostFragment();
        dFragment.show(manager, "alert dialog fragment");
    }

    public void realmenteEnviarObjetos(View view) {
        if(!verificarPassos())
        {
            Toast.makeText(CriarProjetos.this, "Não é possível enviar um post que possua passos sem descrição", Toast.LENGTH_LONG).show();
            return;
        }
        for (ParentExpandableView parent : parentObjects) {
            ArrayList<ParseObject> listaChildParse = new ArrayList<>();
            for (ChildExpandableView child : parent.getChildObjects()) {
                ParseObject childParse = new ParseObject("child");
                childParse.put("description", child.getDescription());
                listaChildParse.add(childParse);
            }
            ParseObject parentParse = new ParseObject("parent");
            parentParse.put("description", parent.getDescription());
            parentParse.addAllUnique("childs", listaChildParse);
            ParseFile imagem = new ParseFile("imagem",parent.getImagemDescription());
            parentParse.add("imagem",imagem);
            listaParentParse.add(parentParse);
        }
        ParseObject post = new ParseObject("post" + "");
        if (postName == null) {
            postName = "";
        }
        post.put("name", postName);
        post.addAllUnique("parents", listaParentParse);
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.d("salvarPostParse", e.getMessage());
                }
            }
        });
        Toast.makeText(CriarProjetos.this, "Post feito com sucesso", Toast.LENGTH_SHORT).show();
        Intent mudarActivityMain = new Intent(CriarProjetos.this, TelaInicial.class);
        startActivity(mudarActivityMain);
        finishActivity(0);
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
    public boolean verificarPassos()
    {
        for(ParentExpandableView parent : parentObjects)
        {
            if(parent.getDescription().equals(""))
            {
                return false;
            }
            for(ChildExpandableView child : parent.getChildObjects())
            {
                if(child.getDescription().equals(""))
                {
                    return false;
                }
            }
        }
        return true;
    }
    public void visualizarImagem(View view)
    {
        ImageShowerFragment imageShower = new ImageShowerFragment();
        Bundle bundle = new Bundle();
        bundle.putByteArray("imagem", parentObjects.get(currentParent).getImagemDescription());
        imageShower.setArguments(bundle);
        imageShower.show(manager,"alert dialog fragment");
    }
    public void removerImagem(View view)
    {
        parentObjects.get(currentParent).setImagemDescription(null);
        adapter.isFotoPresente().put(parentObjects.get(currentParent),Boolean.FALSE);
        adapter.notifyDataSetChanged();
    }
}