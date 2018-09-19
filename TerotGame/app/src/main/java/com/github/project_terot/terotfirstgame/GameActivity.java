package com.github.project_terot.terotfirstgame;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.project_terot.terotfirstgame.models.Color;
import com.github.project_terot.terotfirstgame.models.Jaguair;
import com.github.project_terot.terotfirstgame.models.Laion;
import com.github.project_terot.terotfirstgame.models.Leopaird;
import com.github.project_terot.terotfirstgame.models.Pantherai;
import com.github.project_terot.terotfirstgame.models.SnowLeopaird;
import com.github.project_terot.terotfirstgame.models.Taiger;

import java.util.ArrayList;
import java.util.Random;

import static com.github.project_terot.terotfirstgame.models.Color.BLUE;
import static com.github.project_terot.terotfirstgame.models.Color.GREEN;
import static com.github.project_terot.terotfirstgame.models.Color.ORANGE;
import static com.github.project_terot.terotfirstgame.models.Color.RED;
import static com.github.project_terot.terotfirstgame.models.Color.YELLOW;

public class GameActivity extends Activity implements GameWinFragment.OnGameWinFragmentInteractionListener, GameCombatFragment.OnGameCombatFragmentInteractionListener, GameOpponentFragment.OnGameOpponentFragmentInteractionListener {

    @Override
    public void onOkClick(boolean stren, boolean dexte, boolean intell) {
        // GameWinFragment interface
        if(stren)
            player.incStrength();
        else if(dexte)
            player.incDexterity();
        else
            player.incIntelligence();

        //TODO: KADA dođe do nule sa opponentima napraviti izlaz iz IGRANJA
        if(activeOpponents.size() > 0){
            Random rand = new Random();
            int j = rand.nextInt(3);
            for(int i = 0; i < activeOpponents.size(); i++){
                if(j == 0)
                    activeOpponents.get(i).incStrength();
                else if(j == 1)
                    activeOpponents.get(i).incDexterity();
                else
                    activeOpponents.get(i).incIntelligence();
                j = rand.nextInt(3);
            }

            FragmentManager fragmentManager = getFragmentManager();
            GameOpponentFragment opponentFrag = GameOpponentFragment.newInstance(player, activeOpponents);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frameLayout, opponentFrag, "Opponent"); // nema ADD to back stack !!!
            transaction.commit();
        }
        else{
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
            finish(); // izlaz iz GameActivity
        }

    }

    @Override
    public void onBattleButtonClick() {
        // GameOpponentFragment interface
        FragmentManager fragmentManager = getFragmentManager();
        GameCombatFragment combatFrag = GameCombatFragment.newInstance(player, activeOpponents.get(0)); //TODO:paziti NULL!
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, combatFrag, "Combat");
        transaction.commit();
    }

    @Override
    public void onWinInteraction() {
        // GameCombatFragment interface

        // izbacivanje opponenta
        if(activeOpponents.size() > 0)
            activeOpponents.remove(0);

        FragmentManager fragmentManager = getFragmentManager();
        GameWinFragment winFrag = GameWinFragment.newInstance(player.getStrength(), player.getDexterity(), player.getIntelligence());
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, winFrag, "Win");
        transaction.commit();
    }

    @Override
    public void onLoseInteraction() {
        // GameCombatFragment interface
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
        finish();
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

        if(pickedCode < 0 || pickedCode > 4) return; //TODO: izuzetke staviti

        activeOpponents = new ArrayList<Pantherai>();

        Random rand = new Random();
        int j = rand.nextInt(5);

        for(int i = 0; i < 5; i++, j++){ // dodaje u listu opponente i ovisno od odabrane boje igraca
            if(j == 5) j = 0;
            switch (j){
                case 0: {
                    if(RED.ordinal() == pickedCode)
                        player = new Taiger();
                    else
                        activeOpponents.add(new Taiger());
                    break;
                }
                case 1: {
                    if(YELLOW.ordinal() == pickedCode)
                        player = new Laion();
                    else
                        activeOpponents.add(new Laion());
                    break;
                }
                case 2: {
                    if(BLUE.ordinal() == pickedCode)
                        player = new SnowLeopaird();
                    else
                        activeOpponents.add(new SnowLeopaird());
                    break;
                }
                case 3: {
                    if(ORANGE.ordinal() == pickedCode)
                        player = new Leopaird();
                    else
                        activeOpponents.add(new Leopaird());
                    break;
                }
                case 4: {
                    if(GREEN.ordinal() == pickedCode)
                        player = new Jaguair();
                    else
                        activeOpponents.add(new Jaguair());
                    break;
                }
                default: break;
            }
        }
        /*
        for(int i = 0; i < activeOpponents.size(); i++){
            if(activeOpponents.get(i).getPantheraiColor().ordinal() == pickedCode){
                player = activeOpponents.get(i);
                activeOpponents.remove(i);
                break;
            }
        }
        */
    }


    @Override
    public void onBackPressed() {

        //da ne izlazi kad se Back pritisne

        /*
        int count = getFragmentManager().getBackStackEntryCount();

        if(count == 0){
            super.onBackPressed();
        }
        else{
            getFragmentManager().popBackStack();
        }
        */
    }
}
