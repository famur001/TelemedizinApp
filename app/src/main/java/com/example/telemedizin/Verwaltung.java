package com.example.telemedizin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.telemedizin.Common.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Verwaltung extends AppCompatActivity {

    private Button mAddBtn, mRemoveBtn, mSearchBtn, mMiniAkteBtn, mLogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verwaltung);

        // backend für die PAtientenliste
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        mAddBtn = findViewById(R.id.addBtn);
        mRemoveBtn = findViewById(R.id.removeBtn);
        mSearchBtn = findViewById(R.id.searchBtn);
        mMiniAkteBtn = findViewById(R.id.miniAkteBtn);
        mLogoutBtn = findViewById(R.id.logoutBtn);

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Verwaltung.this, PatientenAdden.class));
            }
        });

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSearchDialog();
            }
        });

        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.logOut();
                startActivity(new Intent(Verwaltung.this, MainActivity.class));
            }
        });

    }

    private void showSearchDialog() {

        final Dialog dialog = new Dialog(Verwaltung.this);
        dialog.setContentView(R.layout.dialog_search);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        EditText vorname = dialog.findViewById(R.id.suche_vorname);
        EditText nachname = dialog.findViewById(R.id.suche_nachname);
        Button suchenBtn = dialog.findViewById(R.id.suche_btn);

        suchenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Verwaltung.this, PatientenSuche.class);
                intent.putExtra("vorname", vorname.getText().toString());
                intent.putExtra("nachname", nachname.getText().toString());
                startActivity(intent);
            }
        });

    }

}