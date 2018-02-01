package com.example.cadu.rededossaberes.expandableListViewElements;

import java.util.ArrayList;
import java.util.List;

public class ParentExpandableView
{
    private ArrayList<byte[]> listaImagens;
    private String description;
    private List<ChildExpandableView> childObjects;

    public ParentExpandableView(String description, List<ChildExpandableView> childObjects,ArrayList<byte[]> listaImagens)
    {
        this.childObjects = childObjects;
        this.description = description;
        this.listaImagens = listaImagens;
    }
    public ParentExpandableView(String description, List<ChildExpandableView> childObjects)
    {
        this.childObjects = childObjects;
        this.description = description;
        this.listaImagens = new ArrayList<>();
    }
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

    public void setChildObjects(List<ChildExpandableView> childObjects)
    {
        this.childObjects = childObjects;
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

    public void setListaImagens(ArrayList<byte[]> listaImagens) {
        this.listaImagens = listaImagens;
    }
}
