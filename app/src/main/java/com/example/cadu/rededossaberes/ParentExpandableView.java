package com.example.cadu.rededossaberes;

import java.util.List;

public class ParentExpandableView
{
    public ParentExpandableView(String description, List<ChildExpandableView> childObjects)
    {
        this.childObjects = childObjects;
        this.description = description;
    }
    private String description;
    private List<ChildExpandableView> childObjects;

    public List<ChildExpandableView> getChildObjects() {
        return childObjects;
    }

    public void setChildObjects(List<ChildExpandableView> childObjects) {
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
