package com.app.islam.perantara;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class FormActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    TextInputLayout umurInputLayout;
    TextInputLayout emailInputLayout;
    TextInputEditText umur;
    TextInputEditText email;
    Spinner spinJK;
    Spinner spinPendidikan;
    Spinner spinPekerjaan;
    CardView kirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        umurInputLayout = (TextInputLayout) findViewById(R.id.umurTextInputlayout);
        umur = (TextInputEditText) findViewById(R.id.umurEditText);

        emailInputLayout = (TextInputLayout) findViewById(R.id.emailTextInputlayout);
        email = (TextInputEditText) findViewById(R.id.emailEditText);

        spinJK = (Spinner) findViewById(R.id.spinnerJK);
        ArrayAdapter<CharSequence> adapterJK = ArrayAdapter.createFromResource(this, R.array.jk_array, R.layout.spinner_item);
        adapterJK.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinJK.setAdapter(adapterJK);

        spinPendidikan = (Spinner) findViewById(R.id.spinnerPendidikan);
        ArrayAdapter<CharSequence> adapterPendidikan = ArrayAdapter.createFromResource(this, R.array.pendidikan_array, R.layout.spinner_item);
        adapterPendidikan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinPendidikan.setAdapter(adapterPendidikan);

        spinPekerjaan = (Spinner) findViewById(R.id.spinnerPekerjaan);
        ArrayAdapter<CharSequence> adapterPekerjaan = ArrayAdapter.createFromResource(this, R.array.pekerjaan_array, R.layout.spinner_item);
        adapterPekerjaan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinPekerjaan.setAdapter(adapterPekerjaan);

        kirim = (CardView) findViewById(R.id.cardkirim);

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kirimData();
            }
        });
    }


    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    void kirimData() {

        if (TextUtils.isEmpty(umur.getText())) {

            umurInputLayout.setError(getString(R.string.umur_error));
            umurInputLayout.requestFocus();

        } else {

            if (isValidEmail(email.getText().toString()) == true || TextUtils.isEmpty(email.getText()) == true) {

                mDatabase = FirebaseDatabase.getInstance().getReference();

                //Getting Values
                int umurAngka = Integer.parseInt(umur.getText().toString());
                String jkText = spinJK.getSelectedItem().toString();
                String pendidikanText = spinPendidikan.getSelectedItem().toString();
                String pekerjaanText = spinPekerjaan.getSelectedItem().toString();
                String emailText = email.getText().toString();

                //Creating new user node
                String userId = mDatabase.push().getKey();

                //Creating user Object
                User user = new User(umurAngka, jkText, pendidikanText, pekerjaanText, emailText);

                //Pushing user to 'users node using userID
                mDatabase.child("users").child(userId).setValue(user);

                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                        .putBoolean("isFirstRun", false).apply();

                Intent intent = new Intent(FormActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                finish();

            } else {
                emailInputLayout.setError("Email anda tidak valid!");
                emailInputLayout.requestFocus();
            }

        }

    }

}
