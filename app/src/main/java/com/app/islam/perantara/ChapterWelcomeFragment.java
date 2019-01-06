package com.app.islam.perantara;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChapterWelcomeFragment extends Fragment {


    public ChapterWelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chapter_welcome, container, false);
        TextView title = view.findViewById(R.id.textCenter);

        int chapterNumber = Integer.parseInt(getActivity().getIntent().getStringExtra("chapterNumber"));
        switch (chapterNumber) {
            case 1:
                title.setText("Bagian 1\nPengenalan Tokoh");
                break;
            case 2:
                title.setText("Bagian 2\nAda Apa Dengan Payudara Saya?");
                break;
            case 3:
                title.setText("Bagian 3\nMengapa Perlu Konsultasi ke Dokter?");
                break;
            default:
                title.setText("Bagian 4\nIbu Tidak Sendirian");
                break;
        }

        // Inflate the layout for this fragment
        return view;
    }

}
