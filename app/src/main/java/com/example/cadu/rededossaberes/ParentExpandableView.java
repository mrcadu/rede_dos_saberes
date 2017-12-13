package com.example.cadu.rededossaberes;

import java.util.ArrayList;
import java.util.List;

public class ParentExpandableView
{
    public ParentExpandableView(String description, List<ChildExpandableView> childObjects)
    {
        this.childObjects = childObjects;
        this.description = description;
    }
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
    private String description;
    private List<ChildExpandableView> childObjects;

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


}
