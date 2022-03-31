package com.example.telemedizin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.telemedizin.Model.Patient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientenAdden extends AppCompatActivity {

    private EditText mVorname, mNachname, mGebDatum, mBathelScore, mPersonalID;
    private Button mSaveBtn;

    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patienten_adden);

        mRef = FirebaseDatabase.getInstance().getReference("Patient");

        mVorname = findViewById(R.id.txtFirstname);
        mNachname = findViewById(R.id.txtLastname);
        mGebDatum = findViewById(R.id.txtGebDatum);
        mBathelScore = findViewById(R.id.txtBathelScore);
        mPersonalID = findViewById(R.id.txtPersonal);
        mSaveBtn = findViewById(R.id.btnAdd_Contact);

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vorname = mVorname.getText().toString();
                String nachname = mNachname.getText().toString();
                String gebDatum = mGebDatum.getText().toString();
                String bathelScore = mBathelScore.getText().toString();
                String personalID = mPersonalID.getText().toString();
                if(!vorname.isEmpty() && !nachname.isEmpty() && !gebDatum.isEmpty() && !bathelScore.isEmpty() && !personalID.isEmpty()) {
                    Patient newPatient = new Patient(vorname, nachname, gebDatum, bathelScore, personalID);
                    mRef.child(personalID).setValue(newPatient);
                    Toast.makeText(PatientenAdden.this, "Hinzugefügt", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PatientenAdden.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(PatientenAdden.this, "Gebe bitte alle Daten ein", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}