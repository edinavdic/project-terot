package com.github.project_terot.terotfirstgame;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




public class GameOpponentFragment extends Fragment {


    public interface OnGameOpponentFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private OnGameOpponentFragmentInteractionListener mListener;


    private static final String PICKED_CODE = "pickedCode";

    private int pickCode;



    public GameOpponentFragment() {
        // Required empty public constructor
    }


    public static GameOpponentFragment newInstance(int pickCode) {
        GameOpponentFragment fragment = new GameOpponentFragment();
        Bundle args = new Bundle();
        args.putInt(PICKED_CODE, pickCode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pickCode = getArguments().getInt(PICKED_CODE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_game_opponent, container, false);


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
