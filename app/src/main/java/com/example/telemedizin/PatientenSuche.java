package com.example.telemedizin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.telemedizin.Model.Patient;
import com.example.telemedizin.ViewHolder.PatientenViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientenSuche extends AppCompatActivity {


    private DatabaseReference mRef;
    private FirebaseRecyclerAdapter<Patient, PatientenViewHolder> mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patienten_suche);

        mRef = FirebaseDatabase.getInstance().getReference("Patient");

        mRecyclerView = findViewById(R.id.patientenSuche_recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager( new LinearLayoutManager(this));

        readPatienten(getIntent().getStringExtra("nachname"));
    }

    public void readPatienten(String nachname) {
        mAdapter = new FirebaseRecyclerAdapter<Patient, PatientenViewHolder>(
                Patient.class,
                R.layout.patient_item,
                PatientenViewHolder.class,
                mRef.orderByChild("nachname").equalTo(nachname))
        {
            @Override
            protected void populateViewHolder(PatientenViewHolder holder, Patient item, int i) {
                holder.mFullName.setText(item.getNachname() + ", " + item.getVorname());
                holder.mRemoveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mRef.child(item.getPersonalID()).removeValue();
                        Toast.makeText(PatientenSuche.this, "Patienten gelöscht!", Toast.LENGTH_SHORT).show();
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

        final Dialog dialog = new Dialog(PatientenSuche.this);
        dialog.setContentView(R.layout.dialog_auswahl);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView miniAkteBtn = dialog.findViewById(R.id.auswahl_miniakte);
        TextView barthelBtn = dialog.findViewById(R.id.auswahl_barthel);

        miniAkteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientenSuche.this, Miniakte.class);
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
                Intent intent = new Intent(PatientenSuche.this, BarthelIndex.class);
                intent.putExtra("score", patient.getBarthelScore());
                intent.putExtra("personalID", patient.getPersonalID());
                startActivity(intent);
            }
        });

    }
}