package com.github.project_terot.terotfirstgame;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.project_terot.terotfirstgame.models.Pantherai;
import com.github.project_terot.terotfirstgame.models.Taiger;

import java.util.ArrayList;


public class GameOpponentFragment extends Fragment {


    public interface OnGameOpponentFragmentInteractionListener {
        void onBattleButtonClick();
    }

    private OnGameOpponentFragmentInteractionListener mListener;


    private static final String ACTIVE_OPPONENTS = "ACTIVE_OPPONENTS";
    private static final String PLAYER = "PLAYER";

    private ArrayList<Pantherai> activeOpponents;
    private Pantherai player;
    // TODO:find best practice for seriazable or Parcable

    //layout arguments
    ImageView imageViewPlayerSprite;
    ImageView imageViewPlayerColor;
    TextView textViewPlayerType;
    TextView textViewPlayerStats;
    ImageButton imageButtonBattle;
    ImageView imageViewBattleColor;
    //
    RecyclerView recyclerViewOpponents;
    CustomAdapterOpponents customAdapter;
    RecyclerView.LayoutManager layoutManager;
    //


    public GameOpponentFragment() {
        // Required empty public constructor
    }


    public static GameOpponentFragment newInstance(Pantherai player, ArrayList<Pantherai> activeOpponents) {
        GameOpponentFragment fragment = new GameOpponentFragment();
        Bundle args = new Bundle();
        args.putSerializable(ACTIVE_OPPONENTS, activeOpponents);
        args.putSerializable(PLAYER, player);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            activeOpponents =(ArrayList<Pantherai>) getArguments().getSerializable(ACTIVE_OPPONENTS);
            player = (Pantherai) getArguments().getSerializable(PLAYER);
        }
    }

    //// for setImageResource()
    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
    ////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_game_opponent, container, false);

        imageViewPlayerSprite = (ImageView) rootView.findViewById(R.id.imageViewPlayerSprite);
        imageViewPlayerColor = (ImageView) rootView.findViewById(R.id.imageViewPlayerColor);
        textViewPlayerStats = (TextView) rootView.findViewById(R.id.textViewPlayerStats);
        textViewPlayerType = (TextView) rootView.findViewById(R.id.textViewPlayerType);
        imageButtonBattle = (ImageButton) rootView.findViewById(R.id.imageButtonBattle);
        imageViewBattleColor = (ImageView) rootView.findViewById(R.id.imageViewBattleColor);
        recyclerViewOpponents = (RecyclerView) rootView.findViewById(R.id.recyclerViewOpponents);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        customAdapter = new CustomAdapterOpponents(getActivity(), activeOpponents);

        recyclerViewOpponents.setLayoutManager(layoutManager);
        recyclerViewOpponents.setAdapter(customAdapter);

        // TODO: pripaziti kad ne bude vise opponenta da se ne poziva ovaj fragment u GameActivity
        imageButtonBattle.setImageResource(getImageId(getActivity(), "button_play"));
        imageViewBattleColor.setImageResource(getImageId(getActivity(), "button_" + activeOpponents.get(0).getPantheraiColor().toString().toLowerCase()));

        //TODO: make sprite for BATTLE pointing at color and text to press!


        imageViewPlayerSprite.setImageResource(getImageId(getActivity(), player.getDrawablePngPath()));
        imageViewPlayerColor.setImageResource(getImageId(getActivity(), "button_" + player.getPantheraiColor().toString().toLowerCase()));
        textViewPlayerStats.setText("STR: " + player.getStrength() + "\nDEX: " + player.getDexterity() + "\nINT: " + player.getIntelligence());
        switch (player.getPantheraiColor()){
            case RED: textViewPlayerType.setText("Taiger"); break;
            case BLUE: textViewPlayerType.setText("Snow Leopaird"); break;
            case GREEN: textViewPlayerType.setText("Jaguair"); break;
            case ORANGE: textViewPlayerType.setText("Leopaird"); break;
            case YELLOW: textViewPlayerType.setText("Laion"); break;
            default: break;
        }


        imageButtonBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onBattleButtonClick();
            }
        });


        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnGameOpponentFragmentInteractionListener) {
            mListener = (OnGameOpponentFragmentInteractionListener) context;
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
