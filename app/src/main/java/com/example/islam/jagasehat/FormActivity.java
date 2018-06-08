package com.example.islam.jagasehat;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    String[] jenisKelamin = {"Laki-laki", "Perempuan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Spinner spinJK = (Spinner) findViewById(R.id.spinnerJK);
        ArrayAdapter<CharSequence> adapterJK = ArrayAdapter.createFromResource(this, R.array.jk_array, R.layout.spinner_item);
        adapterJK.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinJK.setAdapter(adapterJK);

        Spinner spinPendidikan = (Spinner) findViewById(R.id.spinnerPendidikan);
        ArrayAdapter<CharSequence> adapterPendidikan = ArrayAdapter.createFromResource(this, R.array.pendidikan_array, R.layout.spinner_item);
        adapterPendidikan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinPendidikan.setAdapter(adapterPendidikan);

        Spinner spinPekerjaan = (Spinner) findViewById(R.id.spinnerPekerjaan);
        ArrayAdapter<CharSequence> adapterPekerjaan = ArrayAdapter.createFromResource(this, R.array.pekerjaan_array, R.layout.spinner_item);
        adapterPekerjaan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinPekerjaan.setAdapter(adapterPekerjaan);
    }

    void next (View view) {

        Intent intent = new Intent(FormActivity.this, DaftarActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);

    }
}
