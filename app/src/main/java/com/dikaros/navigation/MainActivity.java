package com.dikaros.navigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.dikaros.navigation.widget.BottomNavigationBar;
import com.dikaros.navigation.widget.NavigationItem;

public class MainActivity extends AppCompatActivity{

    BottomNavigationBar bottomNavigationBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bnBar);
        /*
        add bottom navigation item
        param1 bottom text
        param2 bottom image checked
        param3 bottom image unchecked
         */
        bottomNavigationBar.addItemView("Recents",R.drawable.ic_restore_green_24dp,R.drawable.ic_restore_black_24dp);
        bottomNavigationBar.addItemView("Favorites", R.drawable.ic_insert_emoticon_check_24dp,R.drawable.ic_insert_emoticon_black_24dp);
        bottomNavigationBar.addItemView("Nearby",R.drawable.ic_place_green_24dp,R.drawable.ic_place_black_24dp);

        //set Callback
        bottomNavigationBar.setOnItemViewSelectedListener(new BottomNavigationBar.OnItemViewSelectedListener() {
            /**
             * call when item clicked
             * @param v clicked item
             * @param index item index
             */
            @Override
            public void onItemClcik(View v, int index) {

            }
        });


    }

    public void change1(View v){
        bottomNavigationBar.setType(BottomNavigationBar.NavShowType.NORMAL);
    }
    public void change2(View v){
        bottomNavigationBar.setType(BottomNavigationBar.NavShowType.NO_TEXT);

    }
    public void change3(View v){
        bottomNavigationBar.setType(BottomNavigationBar.NavShowType.NO_IMAGE);

    }
    public void change4(View v){
        bottomNavigationBar.setType(BottomNavigationBar.NavShowType.CHECKED_SHOW_TEXT);

    }
}

