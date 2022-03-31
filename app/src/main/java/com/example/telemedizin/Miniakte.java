package com.example.telemedizin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Miniakte extends AppCompatActivity {

    private TextView mFullName, mGebDatum, mAnschrift, mSektor, mPersonalID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miniakte);

        mFullName = findViewById(R.id.miniAkte_fullName);
        mGebDatum = findViewById(R.id.miniAkte_gebDatum);
        mAnschrift = findViewById(R.id.miniAkte_anschrift);
        mSektor = findViewById(R.id.miniAkte_sektor);
        mPersonalID = findViewById(R.id.miniAkte_personalID);

        Intent intent = getIntent();

        mFullName.setText(intent.getStringExtra("name"));
        mGebDatum.setText(intent.getStringExtra("gebDatum"));
        //mAnschrift.setText(intent.getStringExtra("anschrift"));
        mSektor.setText(intent.getStringExtra("score"));
        mPersonalID.setText(intent.getStringExtra("personalID"));

    }
}