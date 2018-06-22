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
public class ChapterOneDoneFragment extends Fragment {

    public ChapterOneDoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_cerita_selesai, container, false);

        // Defining the ImageView
        ImageView finishButton = (ImageView) view.findViewById(R.id.finishButton);
        // Setting onClick on the ImageView
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent(); // Creating an empty Intent
                resultIntent.putExtra("finish", "1");  // Giving the empty Intent a value to pass, indicating chapter 1 is done
                getActivity().setResult(Activity.RESULT_OK, resultIntent); // Setting result

                getActivity().finish(); // Close the Activity
                getActivity().overridePendingTransition(R.anim.slide_out_down, R.anim.slide_in_down); // Override transition animation
            }
        });

        return view;
    }

}
