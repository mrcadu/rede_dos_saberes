package com.example.cadu.rededossaberes.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.cadu.rededossaberes.R;
import com.parse.ParseUser;

public class ToolbarActivity extends AppCompatActivity
{
    @Override
    public void onContentChanged() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        super.onContentChanged();
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
                ParseUser.logOutInBackground();
                Intent mudarActivityLogin = new Intent(this,Login.class);
                startActivity(mudarActivityLogin);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
