package com.github.project_terot.terotfirstgame;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity implements MainMenuFragment.OnMainMenuFragmentInteractionListener {
    @Override
    public void onPlayButtonClick() {
        // MainMenuFragment interface
    }

    @Override
    public void onAboutButtonClick() {
        // MainMenuFragment interface

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //only portrait view in this activity
        setContentView(R.layout.activity_main);


        FragmentManager fragmentManager = getFragmentManager();
        MainMenuFragment menuFrag = MainMenuFragment.newInstance();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frameLayout, menuFrag, "Menu");
        transaction.commit();

    }
}
