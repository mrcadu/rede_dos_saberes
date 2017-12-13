package com.example.cadu.rededossaberes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.ExpandableListView;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class CriarProjetos extends AppCompatActivity {
    ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_projetos);
        LayoutInflater inflater = getLayoutInflater();
        expandableListView = (ExpandableListView)findViewById(R.id.lvExp);
        expandableListView.setOnGroupExpandListener(onGroupExpandListenser);
        ExpandableListAdapter adapter = new ExpandableListAdapter(this, getData());
        expandableListView.setAdapter(adapter);
    }

    ExpandableListView.OnGroupExpandListener onGroupExpandListenser = new ExpandableListView.OnGroupExpandListener() {
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
        List<ParentExpandableView> parentObjects = new ArrayList<ParentExpandableView>();
        for (int i = 0; i<20; i++)
        {
            parentObjects.add(new ParentExpandableView("Mother " +i, getChildren(i)));

        }
        return parentObjects;
    }

    private List<ChildExpandableView> getChildren(int childCount)
    {
        List<ChildExpandableView> childObjects = new ArrayList<ChildExpandableView>();
        for (int i =0; i<childCount; i++)
        {
            childObjects.add(new ChildExpandableView("Child " + (i+1)));
        }
        return childObjects;
    }

}