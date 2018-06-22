package com.example.islam.jagasehat;


import android.app.Activity;
import android.content.Intent;
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
public class ChapterTwoDoneFragment extends Fragment {


    public ChapterTwoDoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_chapter_two_done, container, false);
//        final int chapterNumber = Integer.parseInt(getActivity().getIntent().getStringExtra("chapterNumber"));
//        Toast.makeText(getActivity(), "" + chapterNumber, Toast.LENGTH_SHORT).show();
        ImageView finishButton = (ImageView) view.findViewById(R.id.finishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("finish", "2");
//                if (chapterNumber == 1) {
//                    resultIntent.putExtra("finish", 1);
//                } else if (chapterNumber == 2) {
//                    resultIntent.putExtra("finish", 2);
//                } else if (chapterNumber == 3) {
//                    resultIntent.putExtra("finish", 3);
//                } else {
//                    resultIntent.putExtra("finish", 4);
//                }

                getActivity().setResult(Activity.RESULT_OK, resultIntent);
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.slide_out_down, R.anim.slide_in_down);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
