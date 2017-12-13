package com.example.cadu.rededossaberes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.List;

public class CriarProjetos extends AppCompatActivity {
    ExpandableListView expandableListView;
    List<ParentExpandableView> parentObjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_projetos);
        LayoutInflater inflater = getLayoutInflater();
        expandableListView = (ExpandableListView)findViewById(R.id.lvExp);
        expandableListView.setOnGroupExpandListener(onGroupExpandListenser);
        ExpandableListAdapter adapter = new ExpandableListAdapter(this, getData());
        expandableListView.setAdapter(adapter);
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
        ChildExpandableView childExpandableView1;

        addParent("Ingredientes, materiais e instrumentos","feijão");
        addParent("Primeiras instruções","Cozinhe o feijão");
        addParent("Acabamentos e exibição","Comer o feijão");

        /*Primeiro parent*/
        addChild(parentObjects.get(0),"Arroz");

        /*Segundo parent*/
        addChild(parentObjects.get(1),"Cozinhe o arroz");

        /*Terceiro parent*/
        addChild(parentObjects.get(2),"Comer o arroz");

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
}