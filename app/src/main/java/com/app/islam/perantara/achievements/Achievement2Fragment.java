package com.app.islam.perantara.achievements;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.islam.perantara.R;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Achievement2Fragment extends Fragment {

    LinearLayout sponsors;
    ImageView unpad, vu, kwf;

    public Achievement2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_achievement2, container, false);

        sponsors = rootView.findViewById(R.id.sponsors);
        unpad = rootView.findViewById(R.id.logo_unpad);
        vu = rootView.findViewById(R.id.logo_vu);
        kwf = rootView.findViewById(R.id.logo_kwf);

        if (getActivity().getResources().getConfiguration().orientation == LinearLayout.VERTICAL) {
            sponsors.setOrientation(LinearLayout.VERTICAL);
            unpad.getLayoutParams().height = 0;
            unpad.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
            vu.getLayoutParams().height = 0;
            vu.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
            kwf.getLayoutParams().height = 0;
            kwf.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;

//            unpad.requestLayout()
        } else {
            sponsors.setOrientation(LinearLayout.HORIZONTAL);
            unpad.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
            unpad.getLayoutParams().width = 0;
            vu.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
            vu.getLayoutParams().width = 0;
            kwf.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
            kwf.getLayoutParams().width = 0;
        }

        // Inflate the layout for this fragment
        return rootView;
    }

}
