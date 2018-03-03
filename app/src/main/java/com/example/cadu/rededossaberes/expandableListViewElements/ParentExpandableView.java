package com.example.cadu.rededossaberes.expandableListViewElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ParentExpandableView
{
    private byte[] imagemDescription;
    private String description;
    private List<ChildExpandableView> childObjects;

    public ParentExpandableView(String description, String descriptionChild)
    {
        ChildExpandableView child = new ChildExpandableView(descriptionChild);
        List<ChildExpandableView> childExpandableViews = new ArrayList<>();
        childExpandableViews.add(child);
        this.description = description;
        this.childObjects = childExpandableViews;
    }
    public ParentExpandableView(String description)
    {
        List<ChildExpandableView> childExpandableViews = new ArrayList<>();
        this.description = description;
        this.childObjects = childExpandableViews;
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
    public byte[] getImagemDescription()
    {
        return imagemDescription;
    }
    public void setImagemDescription(byte[] imagemDescription)
    {
        this.imagemDescription = imagemDescription;
    }
}
