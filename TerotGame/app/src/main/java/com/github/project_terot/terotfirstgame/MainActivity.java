package com.github.project_terot.terotfirstgame;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements MainPlayFragment.OnMainPlayFragmentInteractionListener, MainAboutFragment.OnMainAboutFragmentInteractionListener, MainMenuFragment.OnMainMenuFragmentInteractionListener {
    @Override
    public void onPickButtonClick(int pickCode) {
        // MainPlayFragment interface

        //TODO: dal skidati sve sa steka prilikom prelaza u drugu aktivnost, provjeriti!

        Intent myIntent = new Intent(this, GameActivity.class);
        myIntent.putExtra("PICKED_CODE", pickCode);
        startActivity(myIntent);
        finish(); // ucini da iz druge aktivnosti kad se klikne back izadje iz aplikacije(tjst, ova aktivnost prestaje sa radom)
    }

    @Override
    public void onPlayButtonClick() {
        // MainMenuFragment interface
        FragmentManager fragmentManager = getFragmentManager();
        MainPlayFragment playFrag = MainPlayFragment.newInstance();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, playFrag, "Play").addToBackStack(null);
        transaction.commit();
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
        // MainAboutFragment interface, MainPlayFragment interface
        getFragmentManager().popBackStack();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // only portrait view in this activity

        // hides the status bar
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
