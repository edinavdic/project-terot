package com.github.project_terot.terotfirstgame;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.project_terot.terotfirstgame.models.Jaguair;
import com.github.project_terot.terotfirstgame.models.Laion;
import com.github.project_terot.terotfirstgame.models.Leopaird;
import com.github.project_terot.terotfirstgame.models.Pantherai;
import com.github.project_terot.terotfirstgame.models.SnowLeopaird;
import com.github.project_terot.terotfirstgame.models.Taiger;

import java.util.ArrayList;


public class MainPlayFragment extends Fragment  {

    private static final int LAION = 0;
    private static final int TAIGER = 1;
    private static final int JAGUAIR = 2;
    private static final int SNOW_LEOPAIRD = 3;
    private static final int LEOPAIRD = 4;

    private int pickedCode;

    ImageButton imageButtonPick;
    ImageButton imageButtonBack;
    RecyclerView recyclerViewPantherai;
    CustomAdapterSelection customAdapter;
    RecyclerView.LayoutManager layoutManager;


    public interface OnMainPlayFragmentInteractionListener {
        void onPickButtonClick(int pickCode);
        void onBackButtonClick();
    }
    private OnMainPlayFragmentInteractionListener mListener;


    ArrayList<Pantherai> list;

    public MainPlayFragment() {
        // Required empty public constructor
    }

    public static MainPlayFragment newInstance() {
        return new MainPlayFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //test values
        list = new ArrayList<>();
        list.add(new Taiger()); list.add(new Laion());
        list.add(new Jaguair()); list.add(new SnowLeopaird());
        list.add(new Leopaird());
        //
        pickedCode = -1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main_play, container, false);

        imageButtonPick = (ImageButton) rootView.findViewById(R.id.imageButtonPick);
        imageButtonBack = (ImageButton) rootView.findViewById(R.id.imageButtonBack);
        recyclerViewPantherai = (RecyclerView) rootView.findViewById(R.id.recyclerViewPantherai);
        layoutManager = new LinearLayoutManager(getActivity());
        customAdapter = new CustomAdapterSelection(getActivity(), list, new CustomAdapterSelection.OnAdapterClickListener(){
            @Override
            public void onItemClick(int pickCode) {
                // CustomAdapterSelection interface
                pickedCode = pickCode;
            }
        });

        recyclerViewPantherai.setLayoutManager(layoutManager);
        recyclerViewPantherai.setAdapter(customAdapter);


        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.onBackButtonClick();
            }
        });

        imageButtonPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pickedCode != -1)
                    mListener.onPickButtonClick(pickedCode);
                else {
                    Toast t = Toast.makeText(getActivity(), "Character not selected!", Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.BOTTOM | Gravity.LEFT,0,0);
                    t.show();
            }   }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMainPlayFragmentInteractionListener) {
            mListener = (OnMainPlayFragmentInteractionListener) context;
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
