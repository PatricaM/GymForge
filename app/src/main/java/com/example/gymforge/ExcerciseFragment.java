package com.example.gymforge;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ExcerciseFragment extends Fragment {

    public ExcerciseFragment() {
        // Required empty public constructor
    }
    private Button logoutButton, aB, bR, eS, pE, pI, gL;
    View view, view1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_excercise, container, false);

        logoutButton = view.findViewById(R.id.logoutButton);
        aB = view.findViewById(R.id.aB);
        bR = view.findViewById(R.id.bR);
        eS = view.findViewById(R.id.eS);
        pE = view.findViewById(R.id.pE);
        pI = view.findViewById(R.id.pI);
        gL = view.findViewById(R.id.gL);

        gL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Gluteos.class);
                startActivity(intent);
            }
        });

        pI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Pierna.class);
                startActivity(intent);
            }
        });

        pE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Pecho.class);
                startActivity(intent);
            }
        });

        eS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Espalda.class);
                startActivity(intent);
            }
        });

        bR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Brazos.class);
                startActivity(intent);
            }
        });

        aB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Abdominales.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity(), Login.class);
                startActivity(intent);
            }
        });
        return view;
    }
}