package com.github.project_terot.terotfirstgame;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.project_terot.terotfirstgame.models.Jaguair;
import com.github.project_terot.terotfirstgame.models.Laion;
import com.github.project_terot.terotfirstgame.models.Leopaird;
import com.github.project_terot.terotfirstgame.models.Pantherai;
import com.github.project_terot.terotfirstgame.models.SnowLeopaird;
import com.github.project_terot.terotfirstgame.models.Taiger;

import java.util.ArrayList;

public class GameActivity extends Activity implements GameCombatFragment.OnGameCombatFragmentInteractionListener, GameOpponentFragment.OnGameOpponentFragmentInteractionListener {

    @Override
    public void onBattleButtonClick() {
        // GameOpponentFragment interface
        FragmentManager fragmentManager = getFragmentManager();
        GameCombatFragment combatFrag = GameCombatFragment.newInstance(player, activeOpponents.get(0)); //TODO:paziti NULL!
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, combatFrag, "Combat").addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onWinInteraction() {
        // GameCombatFragment interface
    }

    @Override
    public void onLoseInteraction() {
        // GameCombatFragment interface
    }

    private ArrayList<Pantherai> activeOpponents;
    private Pantherai player;

    private int pickedCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //only landscape view in this activity

        // hides the status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_game);

        pickedCode = getIntent().getExtras().getInt("PICKED_CODE");
        generateOpponentsAndPlayer();


        FragmentManager fragmentManager = getFragmentManager();
        GameOpponentFragment opponentFrag = GameOpponentFragment.newInstance(player, activeOpponents);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frameLayout, opponentFrag, "Opponent");
        transaction.commit();
    }

    private void generateOpponentsAndPlayer(){

        //TODO: ovo treba biti randomizirano svaki put, i mozda neka provjera dal je player null ili pickedCode ne valja!
        activeOpponents = new ArrayList<Pantherai>();

        activeOpponents.add(new Taiger());
        activeOpponents.add(new Laion());
        activeOpponents.add(new SnowLeopaird());
        activeOpponents.add(new Leopaird());
        activeOpponents.add(new Jaguair());

        for(int i = 0; i < activeOpponents.size(); i++){
            if(activeOpponents.get(i).getPantheraiColor().ordinal() == pickedCode){
                player = activeOpponents.get(i);
                activeOpponents.remove(i);
                break;
            }
        }

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
