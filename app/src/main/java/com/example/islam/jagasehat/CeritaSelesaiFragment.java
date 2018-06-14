package com.example.islam.jagasehat;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CeritaSelesaiFragment extends Fragment {


    public CeritaSelesaiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_cerita_selesai, container, false);

        ImageView finishButton = (ImageView) view.findViewById(R.id.finishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
