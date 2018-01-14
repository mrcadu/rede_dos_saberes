package com.example.cadu.rededossaberes;

import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ExpandableListView;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CriarProjetos extends AppCompatActivity{
    ExpandableListView expandableListView;
    static List<ParentExpandableView> parentObjects = new ArrayList<>();
    int currentParent;
    int currentChild;
    String perspective;
    final ExpandableListAdapter adapter = new ExpandableListAdapter(this, getData());
    FragmentManager manager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
                        if (perspective.equals("edit")) {
                            final EditText editableItem = view.findViewById(R.id.lblListItemEditable);
                            editableItem.setVisibility(View.VISIBLE);
                            view.findViewById(R.id.lblListItem).setVisibility(View.GONE);
                            editableItem.requestFocus();
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(editableItem, InputMethodManager.SHOW_IMPLICIT);
                            editableItem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                @Override
                                public void onFocusChange(View view, boolean b) {
                                    if (!b) {
                                        parentObjects.get(currentParent).getChildObjects().get(currentChild).setDescription(editableItem.getText().toString());
                                    }
                                }
                            });
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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_buttons, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() )
        {
            case R.id.action_sair:
                ParseUser.logOut();
                Intent mudarActivityLogin = new Intent(CriarProjetos.this,Login.class);
                startActivity(mudarActivityLogin);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    ExpandableListView.OnGroupExpandListener onGroupExpandListenser = new ExpandableListView.OnGroupExpandListener()
    {
        int previousGroup =-1;
        @Override
        public void onGroupExpand(int groupPosition) {
            if(groupPosition!= previousGroup)
                expandableListView.collapseGroup(previousGroup);
            previousGroup = groupPosition;
        }
    };

    //Sample data for expandable list view.
    public List<ParentExpandableView> getData()
    {
        if(parentObjects.size()==0)
        {
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
        for (ParentExpandableView parent:parentObjects)
        {
            if(parent.getDescription().equals(parentExpandableView))
            {
                return parent.getChildObjects();
            }
        }
        return null;
    }
    private void addParent(String description,List<ChildExpandableView> childExpandableViews)
    {
        ParentExpandableView parentExpandableView = new ParentExpandableView(description,childExpandableViews);
        parentObjects.add(parentExpandableView);
    }
    private void addParent(String description,String descriptionChild)
    {
        ParentExpandableView parentExpandableView = new ParentExpandableView(description,descriptionChild);
        parentObjects.add(parentExpandableView);
    }
    private void addChild(ParentExpandableView parentExpandableView,ChildExpandableView childExpandableView)
    {
        for (ParentExpandableView parent: parentObjects)
        {
            if(parentExpandableView.getDescription() == parent.getDescription())
            {
                parent.getChildObjects().add(childExpandableView);
            }
        }
    }
    private void addChild(ParentExpandableView parentExpandableView,String descriptionChild)
    {
        ChildExpandableView childExpandableView = new ChildExpandableView(descriptionChild);
        for (ParentExpandableView parent: parentObjects)
        {
            if(parentExpandableView.getDescription() == parent.getDescription())
            {
                parent.getChildObjects().add(childExpandableView);
            }
        }
    }
    private void addChilds(ParentExpandableView parentExpandableView,List <ChildExpandableView> childExpandableViews)
    {
        for (ParentExpandableView parent: parentObjects)
        {
            if(parentExpandableView.getDescription() == parent.getDescription())
            {
                for(ChildExpandableView child: childExpandableViews)
                {
                    parent.getChildObjects().add(child);
                }
            }
        }
    }
    public void removerParent(View view)
    {
        parentObjects.remove(currentParent);
        adapter.notifyDataSetChanged();
    }
    public void adicionarChild(View view)
    {
        ChildExpandableView newChild = new ChildExpandableView("");
        parentObjects.get(currentParent).getChildObjects().add(newChild);
        adapter.notifyDataSetChanged();
    }
    public void editarChild(View view)
    {
        int tamanhoChilds = parentObjects.get(currentParent).getChildObjects().size();
        for(int i = tamanhoChilds; tamanhoChilds != 0; tamanhoChilds--)
        {
            View childView = expandableListView.getChildAt(i);
            childView.findViewById(R.id.lblListHeader).setVisibility(View.GONE);
            childView.findViewById(R.id.lblListHeaderEditable).setVisibility(View.VISIBLE);
        }
        perspective = "edit";
    }
    public void excluirChild(View view)
    {
        perspective = "remove";
    }
    public void adicionarParent(View view) {
        InputTextManager dFragment = new InputTextManager();
        ParentExpandableView newParent = new ParentExpandableView("");
        parentObjects.add(newParent);
        dFragment.show(manager, "alert dialog fragment");
    }
    public static void setLastParentText(EditText editableText)
    {
        String textParent = editableText.getText().toString();
        parentObjects.get(parentObjects.size()-1).setDescription(textParent);
    }
    public static void removeLastElement()
    {
        parentObjects.remove(parentObjects.size()-1);
    }
    public void atualizar(View view)
    {
        adapter.notifyDataSetChanged();
    }

    public void adicionarFoto(View view)
    {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode==1 && resultCode==1)
        {
            Uri localImagemSelecionada = data.getData();
            try {
                Bitmap imagem = MediaStore.Images.Media.getBitmap(getContentResolver() , localImagemSelecionada);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imagem.compress(Bitmap.CompressFormat.PNG,75,stream);
                byte[] byteArray = stream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void enviarObjetos(View view)
    {
        for(ParentExpandableView parent : parentObjects)
        {
            for (ChildExpandableView child : parent.getChildObjects())
            {
                ParseObject childParse = new ParseObject("child");
                childParse.put("description",child.getDescription());
                childParse.saveInBackground();
            }
            ParseObject parentParse = new ParseObject("parent");
            parentParse.put("description",parent.getDescription());
            parentParse.addAllUnique("childs",Arrays.asList(parent.getChildObjects()));
            parentParse.saveInBackground();
        }
        ParseObject post = new ParseObject("post");
        post.put("name","primeiroPost");
        post.addAllUnique("parents",Arrays.asList(parentObjects));
        post.saveInBackground();
    }
}