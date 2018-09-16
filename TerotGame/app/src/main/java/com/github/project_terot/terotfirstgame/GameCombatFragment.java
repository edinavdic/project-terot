package com.github.project_terot.terotfirstgame;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.project_terot.terotfirstgame.models.Pantherai;


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
    // just to keep flow
    Button buttonWin;
    Button buttonLose;
    //

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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_game_combat, container, false);

        // just for now
        buttonLose = (Button) rootView.findViewById(R.id.buttonLose);
        buttonWin = (Button) rootView.findViewById(R.id.buttonWin);
        //
        buttonWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onWinInteraction();
            }
        });
        buttonLose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onLoseInteraction();
            }
        });
        //

        return rootView;
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
