package com.example.telemedizin;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.telemedizin.Model.Patient;
import com.example.telemedizin.Model.User;
import com.example.telemedizin.ViewHolder.PatientenViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Patientenliste extends AppCompatActivity {

    private ImageView mAddBtn;

    private DatabaseReference mRef;
    private FirebaseRecyclerAdapter<Patient, PatientenViewHolder> mAdapter;
    private RecyclerView mRecyclerView;

    protected void onCreate(Bundle save) {
        super.onCreate(save);
        setContentView(R.layout.activity_patientenliste);

        mRef = FirebaseDatabase.getInstance().getReference("Patient");

        mRecyclerView = findViewById(R.id.patientenListe_recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager( new LinearLayoutManager(this));

        mAddBtn = findViewById(R.id.patientenListe_addBtn);
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Patientenliste.this, Verwaltung.class));
            }
        });

        readAllPatienten();

    }

    public void readAllPatienten() {
        mAdapter = new FirebaseRecyclerAdapter<Patient, PatientenViewHolder>(
                Patient.class,
                R.layout.patient_item,
                PatientenViewHolder.class,
                mRef)
        {
            @Override
            protected void populateViewHolder(PatientenViewHolder holder, Patient item, int i) {
                holder.mFullName.setText(item.getNachname() + ", " + item.getVorname());
                holder.mRemoveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mRef.child(item.getPersonalID()).removeValue();
                        Toast.makeText(Patientenliste.this, "Patienten gelöscht!", Toast.LENGTH_SHORT).show();
                    }
                });
                holder.patientenItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog(item);
                    }
                });
            }
        };
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void showDialog(Patient patient) {

        final Dialog dialog = new Dialog(Patientenliste.this);
        dialog.setContentView(R.layout.dialog_auswahl);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView miniAkteBtn = dialog.findViewById(R.id.auswahl_miniakte);
        TextView barthelBtn = dialog.findViewById(R.id.auswahl_barthel);

        miniAkteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Patientenliste.this, Miniakte.class);
                intent.putExtra("name", patient.getVorname() + " " + patient.getNachname());
                intent.putExtra("gebDatum", patient.getGeburtsdatum());
                intent.putExtra("score", patient.getBarthelScore());
                intent.putExtra("personalID", patient.getPersonalID());
                startActivity(intent);
            }
        });
        barthelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Patientenliste.this, BarthelIndex.class);
                intent.putExtra("score", patient.getBarthelScore());
                intent.putExtra("personalID", patient.getPersonalID());
                startActivity(intent);
            }
        });

    }

}
