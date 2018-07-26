package com.github.project_terot.terotfirstgame;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements MainAboutFragment.OnMainAboutFragmentInteractionListener, MainMenuFragment.OnMainMenuFragmentInteractionListener {
    @Override
    public void onPlayButtonClick() {
        // MainMenuFragment interface
    }

    @Override
    public void onAboutButtonClick() {
        // MainMenuFragment interface
        FragmentManager fragmentManager = getFragmentManager();
        MainAboutFragment aboutFrag = MainAboutFragment.newInstance();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, aboutFrag, "About").addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void onBackButtonClick() {
        // MainAboutFragment interface
        getFragmentManager().popBackStack();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // only portrait view in this activity

        // hide the status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_main);


        FragmentManager fragmentManager = getFragmentManager();
        MainMenuFragment menuFrag = MainMenuFragment.newInstance();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frameLayout, menuFrag, "Menu");
        transaction.commit();

    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if(count == 0){
            super.onBackPressed();
        }
        else{
            getFragmentManager().popBackStack();
        }
    }


}
