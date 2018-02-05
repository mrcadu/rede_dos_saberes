package com.example.cadu.rededossaberes;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class ActionBarSingleton {
    private static ActionBarSingleton actionBarSingleton;
    private ActionBarSingleton()
    {
    }
    public static ActionBarSingleton getInstance()
    {
        actionBarSingleton = new ActionBarSingleton();
        return actionBarSingleton;
    }
    public void setarActionBar(Toolbar toolbar, AppCompatActivity activity)
    {
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

}
