package com.github.project_terot.terotfirstgame;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class MainMenuFragment extends Fragment {

    // Arguments
    ImageButton imageButtonPlay, imageButtonAbout;


    public interface OnMainMenuFragmentInteractionListener {
        void onPlayButtonClick();
        void onAboutButtonClick();
    }

    private OnMainMenuFragmentInteractionListener mListener;

    public MainMenuFragment() {
        // Required empty public constructor
    }


    public static MainMenuFragment newInstance() {
        return new MainMenuFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);

        imageButtonPlay = (ImageButton) rootView.findViewById(R.id.imageButtonPlay);
        imageButtonAbout = (ImageButton) rootView.findViewById(R.id.imageButtonAbout);

        imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.onPlayButtonClick();

            }
        });

        imageButtonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.onAboutButtonClick();

            }
        });

        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMainMenuFragmentInteractionListener) {
            mListener = (OnMainMenuFragmentInteractionListener) context;
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
