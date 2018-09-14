package com.github.project_terot.terotfirstgame;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameActivity extends Activity implements GameOpponentFragment.OnGameOpponentFragmentInteractionListener {

    @Override
    public void onFragmentInteraction(Uri uri) {
        // GameOpponentFragment interface
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //only landscape view in this activity

        // hides the status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_game);

        FragmentManager fragmentManager = getFragmentManager();
        GameOpponentFragment opponentFrag = GameOpponentFragment.newInstance(-1); //TODO: upisati pravi code primljen iz mainActivity
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frameLayout, opponentFrag, "Opponent");
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
