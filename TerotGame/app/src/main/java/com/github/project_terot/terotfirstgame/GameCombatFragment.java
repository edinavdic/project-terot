package com.github.project_terot.terotfirstgame;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.project_terot.terotfirstgame.models.Ability;
import com.github.project_terot.terotfirstgame.models.AbilityATK;
import com.github.project_terot.terotfirstgame.models.Pantherai;

import com.github.project_terot.terotfirstgame.models.Color;

import java.util.Random;

import static com.github.project_terot.terotfirstgame.GameWinFragment.getImageId;


public class GameCombatFragment extends Fragment {

    public interface OnGameCombatFragmentInteractionListener {
        void onWinInteraction();
        void onLoseInteraction();
    }

    private OnGameCombatFragmentInteractionListener mListener;

    private static final String OPPONENT = "OPPONENT";
    private static final String PLAYER = "PLAYER";


    private Pantherai opponent;
    private Pantherai player;

    // TODO: make combat playable!!!!

    // svi viewi potrebni!
    ImageView imageViewPlayerSprite, imageViewOpponentSprite, imageViewOpponentColor, imageViewDefColor
                                                                                    , imageViewMidColor
                                                                                    , imageViewAtkColor;
    ImageButton imageButtonBattle, imageButtonChangeDef, imageButtonChangeMid, imageButtonChangeAtk;
    TextView textViewScreen, textViewPlayerStats, textViewOpponentStats;
    //
    boolean matchOver, playerWon;
    int ordinalDef, ordinalMid, ordinalAtk;
    double healthPlayer, healthOpponent;
    boolean playerFirst;
    int colorBonusPlayer, colorBonusOpponent;

    public GameCombatFragment() {
        // Required empty public constructor
    }


    public static GameCombatFragment newInstance(Pantherai player, Pantherai opponent) {
        GameCombatFragment fragment = new GameCombatFragment();
        Bundle args = new Bundle();
        args.putSerializable(PLAYER, player);
        args.putSerializable(OPPONENT, opponent);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            player =(Pantherai) getArguments().getSerializable(PLAYER);
            opponent =(Pantherai) getArguments().getSerializable(OPPONENT);
            playerWon = false;
            matchOver = false;
            ordinalAtk = 0; ordinalDef = 0; ordinalMid = 0;
            healthPlayer = 100 + (player.getStrength() - 15)*10;
            healthOpponent = 100 + (opponent.getStrength() - 15)*10;
            if(player.getDexterity() >= opponent.getDexterity()) playerFirst = true;
            colorBonusPlayer = player.getIntelligence() - 15;
            colorBonusOpponent = opponent.getIntelligence() - 15;
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_game_combat, container, false);

        imageViewPlayerSprite = (ImageView) rootView.findViewById(R.id.imageViewPlayerSprite);
        imageViewOpponentSprite = (ImageView) rootView.findViewById(R.id.imageViewOpponentSprite);
        imageViewOpponentColor = (ImageView) rootView.findViewById(R.id.imageViewOpponentColor);
        imageViewAtkColor = (ImageView) rootView.findViewById(R.id.imageViewAtkColor);
        imageViewMidColor = (ImageView) rootView.findViewById(R.id.imageViewMidColor);
        imageViewDefColor = (ImageView) rootView.findViewById(R.id.imageViewDefColor);

        imageButtonBattle = (ImageButton) rootView.findViewById(R.id.imageButtonBattle);
        imageButtonChangeDef = (ImageButton) rootView.findViewById(R.id.imageButtonChangeDef);
        imageButtonChangeMid = (ImageButton) rootView.findViewById(R.id.imageButtonChangeMid);
        imageButtonChangeAtk = (ImageButton) rootView.findViewById(R.id.imageButtonChangeAtk);

        textViewScreen = (TextView) rootView.findViewById(R.id.textViewScreen);
        textViewPlayerStats = (TextView) rootView.findViewById(R.id.textViewPlayerStats);
        textViewOpponentStats = (TextView) rootView.findViewById(R.id.textViewOpponentStats);

        //predefined
        imageViewPlayerSprite.setImageResource(getImageId(getActivity(), player.getDrawablePngPath()));
        imageViewOpponentSprite.setImageResource(getImageId(getActivity(), opponent.getDrawablePngPath()));
        imageViewOpponentColor.setImageResource(getImageId(getActivity(), "button_" + opponent.getPantheraiColor().toString().toLowerCase()));
        textViewPlayerStats.setText("STR: " + player.getStrength() + "\nDEX: " + player.getDexterity() + "\nINT: " + player.getIntelligence());
        textViewOpponentStats.setText("STR: " + opponent.getStrength() + "\nDEX: " + opponent.getDexterity() + "\nINT: " + opponent.getIntelligence());

        imageViewDefColor.setImageResource(getImageId(getActivity(), "button_yellow"));
        imageViewMidColor.setImageResource(getImageId(getActivity(), "button_yellow"));
        imageViewAtkColor.setImageResource(getImageId(getActivity(), "button_yellow"));
        //
        final String first;
        if(playerFirst) first = "\nPlayer attacks first.";
        else first = "\nOpponent attacks first.";
        textViewScreen.setText("Player health: " + healthPlayer +"\nOpponent health: " + healthOpponent
                               + "\nColor bonus player: " + colorBonusPlayer + "\nColor bonus opponent: " + colorBonusOpponent
                               + first);
        //
        imageButtonChangeDef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ordinalDef++;
                if(ordinalDef == 5) ordinalDef = 0;
                switch (ordinalDef){
                    case 0: imageViewDefColor.setImageResource(getImageId(getActivity(), "button_yellow")); break;
                    case 1: imageViewDefColor.setImageResource(getImageId(getActivity(), "button_red")); break;
                    case 2: imageViewDefColor.setImageResource(getImageId(getActivity(), "button_blue")); break;
                    case 3: imageViewDefColor.setImageResource(getImageId(getActivity(), "button_orange")); break;
                    case 4: imageViewDefColor.setImageResource(getImageId(getActivity(), "button_green")); break;
                    default: break;
                }
            }
        });
        imageButtonChangeMid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ordinalMid++;
                if(ordinalMid == 5) ordinalMid = 0;
                switch (ordinalMid){
                    case 0: imageViewMidColor.setImageResource(getImageId(getActivity(), "button_yellow")); break;
                    case 1: imageViewMidColor.setImageResource(getImageId(getActivity(), "button_red")); break;
                    case 2: imageViewMidColor.setImageResource(getImageId(getActivity(), "button_blue")); break;
                    case 3: imageViewMidColor.setImageResource(getImageId(getActivity(), "button_orange")); break;
                    case 4: imageViewMidColor.setImageResource(getImageId(getActivity(), "button_green")); break;
                    default: break;
                }
            }
        });
        imageButtonChangeAtk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ordinalAtk++;
                if(ordinalAtk == 5) ordinalAtk = 0;
                switch (ordinalAtk){
                    case 0: imageViewAtkColor.setImageResource(getImageId(getActivity(), "button_yellow")); break;
                    case 1: imageViewAtkColor.setImageResource(getImageId(getActivity(), "button_red")); break;
                    case 2: imageViewAtkColor.setImageResource(getImageId(getActivity(), "button_blue")); break;
                    case 3: imageViewAtkColor.setImageResource(getImageId(getActivity(), "button_orange")); break;
                    case 4: imageViewAtkColor.setImageResource(getImageId(getActivity(), "button_green")); break;
                    default: break;
                }
            }
        });

        imageButtonBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String screen;
                if(matchOver){
                    if(playerWon){
                        mListener.onWinInteraction();
                    }
                    else{
                        mListener.onLoseInteraction();
                    }
                }
                else{
                    screen = turnCalculator(ordinalDef, ordinalMid, ordinalAtk);
                    textViewScreen.setText(screen);
                    if(matchOver){
                        imageButtonBattle.setImageResource(getImageId(getActivity(), "button_ok"));
                    }
                }
            }
        });

        return rootView;
    }

    //main method
    private String turnCalculator(int numDef, int numMid, int numAtk){
        int numDefOp, numMidOp, numAtkOp;
        String screen = "";
        screen = screen + Color.getColor(numDef).toString() + " " + Color.getColor(numMid) + " " + Color.getColor(numAtk) + "\n";
        //Ability abilityAtkPlayer = new AbilityATK(Color.getColor(numAtk), "");
        //Ability abilityMidPlayer = new AbilityATK(Color.getColor(numMid), "");
        //Ability abilityDefPlayer = new AbilityATK(Color.getColor(numDef), "");
        screen = screen + "vs.\n";
        Random rand = new Random();
        numDefOp = rand.nextInt(5); numMidOp = rand.nextInt(5); numAtkOp = rand.nextInt(5);
        screen = screen + Color.getColor(numDefOp).toString() + " " + Color.getColor(numMidOp) + " " + Color.getColor(numAtkOp) + "\n";
        //Ability abilityAtkOpponent = new AbilityATK(Color.getColor(numAtk), "");
        //Ability abilityMidOpponent = new AbilityATK(Color.getColor(numMid), "");
        //Ability abilityDefOpponent = new AbilityATK(Color.getColor(numDef), "");

        Pantherai attacker, defender;
        double damageAttacker, damageDefender;
        double healthAttacker, healthDefender;
        int colorBonusAttacker, colorBonusDefender;
        int numDefAttacker, numMidAttacker, numAtkAttacker, numDefDefender, numMidDefender, numAtkDefender;
        if(playerFirst){
            attacker = player;
            healthAttacker = healthPlayer; colorBonusAttacker = colorBonusPlayer;
            defender = opponent;
            healthDefender = healthOpponent; colorBonusDefender = colorBonusOpponent;
            numDefAttacker = numDef; numMidAttacker = numMid; numAtkAttacker = numAtk;
            numDefDefender = numDefOp; numMidDefender = numMidOp; numAtkDefender = numAtkOp;
        }
        else{
            attacker = opponent;
            healthAttacker = healthOpponent; colorBonusAttacker = colorBonusOpponent;
            defender = player;
            healthDefender = healthPlayer; colorBonusDefender = colorBonusPlayer;
            numDefAttacker = numDefOp; numMidAttacker = numMidOp; numAtkAttacker = numAtkOp;
            numDefDefender = numDef; numMidDefender = numMid; numAtkDefender = numAtk;
        }


        damageAttacker = Ability.AbilityColorCalculator.coeffMat[numAtkAttacker][numDefDefender]*20;
        damageDefender = Ability.AbilityColorCalculator.coeffMat[numAtkDefender][numDefAttacker]*18;
        //1. phase Mid + Atk vs. Mid + Def
        switch (numMidAttacker){
            case 0: damageAttacker+=10; colorBonusAttacker+=1; break;
            case 1: damageAttacker+=30; break;
            case 2: colorBonusAttacker+=2; break;
            case 3: damageAttacker+=attacker.getDexterity()*1.5; break;
            case 4: healthDefender-=5; break;
            default: break;
        }
        switch (numMidDefender){
            case 0: healthDefender+=15; break;
            case 1: healthDefender+=10; damageAttacker-=3; break;
            case 2: colorBonusAttacker-=1; break;
            case 3: healthDefender+=defender.getDexterity(); break;
            case 4: damageAttacker-=9; break;
            default: break;
        }
        damageAttacker+=damageAttacker*colorBonusAttacker/2;
        screen = screen + "Attacker damage: " + damageAttacker + "\n";
        healthDefender = healthDefender - damageAttacker;
        screen = screen + "Defender health: " + healthDefender + "\n";
        //2. phase Def vs. Atk
        damageDefender+=damageDefender*colorBonusDefender/2;
        screen = screen + "Defender damage: " + damageDefender + "\n";
        healthAttacker = healthAttacker - damageDefender;
        screen = screen + "Attacker health: " + healthAttacker + "\n";

        if(playerFirst){
            healthPlayer = healthAttacker;
            healthOpponent = healthDefender;
        }
        else{
            healthOpponent = healthAttacker;
            healthPlayer = healthDefender;
        }

        if(healthPlayer < 0.5 || healthOpponent < 0.5){
            matchOver = true;
            if(healthPlayer > healthOpponent){
                playerWon = true;
                screen = screen + "YOU WON!\n";
            }
            else{
                screen = screen + "YOU LOSE!\n";
            }

        }
        else{
            screen = screen + "NEXT TURN.\n";
        }

        return screen;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnGameCombatFragmentInteractionListener) {
            mListener = (OnGameCombatFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
