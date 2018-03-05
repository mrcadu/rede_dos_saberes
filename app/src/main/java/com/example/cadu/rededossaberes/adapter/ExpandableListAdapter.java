package com.example.cadu.rededossaberes.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cadu.rededossaberes.expandableListViewElements.ChildExpandableView;
import com.example.cadu.rededossaberes.expandableListViewElements.ParentExpandableView;
import com.example.cadu.rededossaberes.R;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    Context context;
    List<ParentExpandableView> parentExpandableViews;

    private Map<ParentExpandableView,Boolean> FotoPresente;

    public ExpandableListAdapter(Context context, List<ParentExpandableView> parentExpandableViews)
    {
        this.context = context;
        this.parentExpandableViews = parentExpandableViews;
    }
    @Override
    public int getGroupCount() {
        return parentExpandableViews.size();
    }
    @Override
    public int getChildrenCount(int i) {
        return parentExpandableViews.get(i).getChildObjects().size() + 2;
    }

    @Override
    public ParentExpandableView getGroup(int i)
    {
        return parentExpandableViews.get(i);
    }

    @Override
    public ChildExpandableView getChild(int i, int i2)
    {
        return parentExpandableViews.get(i).getChildObjects().get(i2);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i2) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup)
    {
        ParentExpandableView currentParent = parentExpandableViews.get(i);
        if(view ==null)
        {
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_group_step,null);
        }
        TextView descriptionParent = view.findViewById(R.id.lblListHeader);
        EditText descriptionParentEditable = view.findViewById(R.id.lblListHeaderEditable);
        descriptionParent.setText(currentParent.getDescription());
        descriptionParentEditable.setText(currentParent.getDescription());
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup)
    {
        ParentExpandableView currentParent = getGroup(groupPosition);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //the first row is used as header
        if(childPosition ==0)
        {
            view = inflater.inflate(R.layout.activity_header, null);
            if(isFotoPresente().get(currentParent) != null)
            {
                view.findViewById(R.id.botaoRemoverImagem).setVisibility(View.VISIBLE);
            }
            else
            {
                view.findViewById(R.id.botaoRemoverImagem).setVisibility(View.GONE);
            }
        }

        //Here is the ListView of the ChildView
        if(childPosition>0 && childPosition<getChildrenCount(groupPosition)-1)
        {
            ChildExpandableView currentChild = getChild(groupPosition,childPosition-1);
            view = inflater.inflate(R.layout.activity_step,null);
            TextView description = (TextView) view.findViewById(R.id.lblListItem);
            EditText descriptionEditable = view.findViewById(R.id.lblListItemEditable);
            description.setText(currentChild.getDescription());
            descriptionEditable.setText(currentChild.getDescription());
        }
        //the last row is used as footer
        if(childPosition == getChildrenCount(groupPosition)-1)
        {

            view = inflater.inflate(R.layout.activity_last_child,null);
            TextView nomeImagens = view.findViewById(R.id.nomeImagens);
            nomeImagens.setText("");
            if(currentParent.getImagemDescription() == null)
            {
                nomeImagens.setVisibility(View.GONE);
            }
            else
            {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("1");
                nomeImagens.append("imagem".concat(stringBuilder.toString()));
                nomeImagens.append("\n");
            }
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i2)
    {
        return true;
    }
    public Map<ParentExpandableView,Boolean> isFotoPresente() {
        return FotoPresente;
    }

    public void setFotoPresente(Map<ParentExpandableView,Boolean> fotoPresente) {
        FotoPresente = fotoPresente;
    }
}