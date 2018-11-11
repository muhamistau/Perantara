package com.app.islam.perantara.chapter2;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.islam.perantara.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChapterTwoDoneFragment extends Fragment {


    public ChapterTwoDoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chapter_two_done, container, false);
    }

}
