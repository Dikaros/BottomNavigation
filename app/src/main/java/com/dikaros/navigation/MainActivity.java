package com.dikaros.navigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dikaros.navigation.widget.BottomNavigationBar;
import com.dikaros.navigation.widget.NavigationItem;

public class MainActivity extends AppCompatActivity {

    BottomNavigationBar bottomNavigationBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bnBar);
        bottomNavigationBar.addItemView("Recents",R.drawable.ic_restore_green_24dp,R.drawable.ic_restore_black_24dp);
        bottomNavigationBar.addItemView("Favorites", R.drawable.ic_insert_emoticon_check_24dp,R.drawable.ic_insert_emoticon_black_24dp);
        bottomNavigationBar.addItemView("Nearby",R.drawable.ic_place_green_24dp,R.drawable.ic_place_black_24dp);

    }
}

