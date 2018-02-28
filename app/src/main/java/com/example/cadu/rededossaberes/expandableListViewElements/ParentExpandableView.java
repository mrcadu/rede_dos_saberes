package com.example.cadu.rededossaberes.expandableListViewElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ParentExpandableView
{
    private ArrayList<byte[]> listaImagens;
    private String description;
    private List<ChildExpandableView> childObjects;

    public ParentExpandableView(String description, String descriptionChild)
    {
        ChildExpandableView child = new ChildExpandableView(descriptionChild);
        List<ChildExpandableView> childExpandableViews = new ArrayList<>();
        childExpandableViews.add(child);
        this.description = description;
        this.childObjects = childExpandableViews;
        this.listaImagens = new ArrayList<>();
    }
    public ParentExpandableView(String description)
    {
        List<ChildExpandableView> childExpandableViews = new ArrayList<>();
        this.description = description;
        this.childObjects = childExpandableViews;
        this.listaImagens = new ArrayList<>();
    }
    public List<ChildExpandableView> getChildObjects()
    {
        return childObjects;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    public ArrayList<byte[]> getListaImagens() {
        return listaImagens;
    }
}
