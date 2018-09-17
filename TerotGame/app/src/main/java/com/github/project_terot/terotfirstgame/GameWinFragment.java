package com.github.project_terot.terotfirstgame;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class GameWinFragment extends Fragment {

    public interface OnGameWinFragmentInteractionListener {
        void onOkClick(boolean stren, boolean dexte, boolean intell);
    }

    private OnGameWinFragmentInteractionListener mListener;


    ImageButton buttonAddStr, buttonAddDex, buttonAddInt;
    TextView textViewStr, textViewDex, textViewInt;
    ImageButton imageButtonOk;


    private int strenghtValue, dexterityValue, intelligenceValue;

    private boolean strAdded, dexAdded, intAdded;

    public GameWinFragment() {
        // Required empty public constructor
    }


    public static GameWinFragment newInstance(int strenghtValue, int dexterityValue, int intelligenceValue) {
        GameWinFragment fragment = new GameWinFragment();
        Bundle args = new Bundle();
        args.putInt("Strength", strenghtValue);
        args.putInt("Dexterity", dexterityValue);
        args.putInt("Intelligence", intelligenceValue);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            strenghtValue = getArguments().getInt("Strength");
            dexterityValue = getArguments().getInt("Dexterity");
            intelligenceValue = getArguments().getInt("Intelligence");
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
        View rootView = inflater.inflate(R.layout.fragment_game_win, container, false);

        imageButtonOk = (ImageButton) rootView.findViewById(R.id.imageButtonOk);
        buttonAddStr = (ImageButton) rootView.findViewById(R.id.imageButtonAddStrength);
        buttonAddDex = (ImageButton) rootView.findViewById(R.id.imageButtonAddDexterity);
        buttonAddInt = (ImageButton) rootView.findViewById(R.id.imageButtonAddIntelligence);
        textViewStr = (TextView) rootView.findViewById(R.id.textViewStrength);
        textViewDex = (TextView) rootView.findViewById(R.id.textViewDexterity);
        textViewInt = (TextView) rootView.findViewById(R.id.textViewIntelligence);

        strAdded = false; dexAdded = false; intAdded = false;

        textViewStr.setText("STR: " + strenghtValue);
        textViewDex.setText("DEX: " + dexterityValue);
        textViewInt.setText("INT: " + intelligenceValue);

        buttonAddStr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!strAdded && !dexAdded && !intAdded){
                    strenghtValue++;
                    textViewStr.setText("STR: " + strenghtValue);
                    buttonAddStr.setImageResource(getImageId(getActivity(),"button_x"));
                    buttonAddDex.setImageResource(getImageId(getActivity(), "sprite_blank"));
                    buttonAddInt.setImageResource(getImageId(getActivity(), "sprite_blank"));
                    strAdded = true;
                }
                else if(strAdded){
                    strenghtValue--;
                    textViewStr.setText("STR: " + strenghtValue);
                    buttonAddStr.setImageResource(getImageId(getActivity(),"button_plus"));
                    buttonAddDex.setImageResource(getImageId(getActivity(), "button_plus"));
                    buttonAddInt.setImageResource(getImageId(getActivity(), "button_plus"));
                    strAdded = false;
                }
            }
        });
        buttonAddDex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!strAdded && !dexAdded && !intAdded){
                    dexterityValue++;
                    textViewDex.setText("DEX: " + dexterityValue);
                    buttonAddDex.setImageResource(getImageId(getActivity(),"button_x"));
                    buttonAddStr.setImageResource(getImageId(getActivity(), "sprite_blank"));
                    buttonAddInt.setImageResource(getImageId(getActivity(), "sprite_blank"));
                    dexAdded = true;
                }
                else if(dexAdded){
                    dexterityValue--;
                    textViewDex.setText("DEX: " + dexterityValue);
                    buttonAddStr.setImageResource(getImageId(getActivity(),"button_plus"));
                    buttonAddDex.setImageResource(getImageId(getActivity(), "button_plus"));
                    buttonAddInt.setImageResource(getImageId(getActivity(), "button_plus"));
                    dexAdded = false;
                }
            }
        });
        buttonAddInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!strAdded && !dexAdded && !intAdded){
                    intelligenceValue++;
                    textViewInt.setText("INT: " + intelligenceValue);
                    buttonAddInt.setImageResource(getImageId(getActivity(),"button_x"));
                    buttonAddStr.setImageResource(getImageId(getActivity(), "sprite_blank"));
                    buttonAddDex.setImageResource(getImageId(getActivity(), "sprite_blank"));
                    intAdded = true;
                }
                else if(intAdded){
                    intelligenceValue--;
                    textViewInt.setText("INT: " + intelligenceValue);
                    buttonAddStr.setImageResource(getImageId(getActivity(),"button_plus"));
                    buttonAddDex.setImageResource(getImageId(getActivity(), "button_plus"));
                    buttonAddInt.setImageResource(getImageId(getActivity(), "button_plus"));
                    intAdded = false;
                }
            }
        });

        imageButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!strAdded && !dexAdded && !intAdded){
                    Toast toast = Toast.makeText(getActivity(), "Stats not modified.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM | Gravity.LEFT,0,0);
                    toast.show();
                }
                else
                    mListener.onOkClick(strAdded, dexAdded, intAdded);
            }
        });


        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnGameWinFragmentInteractionListener) {
            mListener = (OnGameWinFragmentInteractionListener) context;
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
