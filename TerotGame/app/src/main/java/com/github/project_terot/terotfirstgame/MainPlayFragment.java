package com.github.project_terot.terotfirstgame;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class MainPlayFragment extends Fragment {

    private static final int LAION = 0;
    private static final int TAIGER = 1;
    private static final int JAGUAIR = 2;
    private static final int SNOW_LEOPAIRD = 3;
    private static final int LEOPAIRD = 4;

    ImageButton imageButtonPick;
    RecyclerView recyclerViewPantherai;


    public interface OnMainPlayFragmentInteractionListener {
        void onPickButtonClick(int pickCode);
    }

    private OnMainPlayFragmentInteractionListener mListener;



    public MainPlayFragment() {
        // Required empty public constructor
    }

    public static MainPlayFragment newInstance() {
        return new MainPlayFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main_play, container, false);

        imageButtonPick = (ImageButton) rootView.findViewById(R.id.imageButtonPick);
        recyclerViewPantherai = (RecyclerView) rootView.findViewById(R.id.recyclerViewPantherai);
        // custom adapter, ali treba napraviti i one sve klase za pantherai i to i u njih smjestiti path do drawable i slati listu pantherai
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
