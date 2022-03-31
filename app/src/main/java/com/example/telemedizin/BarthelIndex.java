package com.example.telemedizin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.telemedizin.Common.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BarthelIndex extends AppCompatActivity {

    private RadioGroup nulll, eins, zwei, drei, vier, fuenf, sechs, sieben, acht, neun;
    private TextView mTotalScore;
    private Button speichern;
    int counter;

    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barthel_index);

        String personalID = getIntent() != null ? getIntent().getStringExtra("personalID") : "-1";
        mRef = FirebaseDatabase.getInstance().getReference("Patient").child(personalID);

        nulll = findViewById(R.id.nulll);
        eins = findViewById(R.id.eins);
        zwei = findViewById(R.id.zwei);
        drei = findViewById(R.id.drei);
        vier = findViewById(R.id.vier);
        fuenf = findViewById(R.id.fuenf);
        sechs = findViewById(R.id.sechs);
        sieben = findViewById(R.id.sieben);
        acht = findViewById(R.id.acht);
        neun = findViewById(R.id.neun);

        mTotalScore = findViewById(R.id.barthelTotalScore);
        String barthelScore = getIntent() != null ? getIntent().getStringExtra("score") : "-1";
        mTotalScore.setText("Total Score: " + barthelScore);

        speichern = findViewById(R.id.speicherButton);

        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton nulllChecked = findViewById(nulll.getCheckedRadioButtonId());
                String str = nulllChecked.getText().toString();
                String zahl = str.replaceAll("[^0-9\\.]", "");

                RadioButton einsChecked = findViewById(eins.getCheckedRadioButtonId());
                String str1 = einsChecked.getText().toString();
                String zahl1 = str1.replaceAll("[^0-9\\.]", "");

                RadioButton zweiChecked = findViewById(zwei.getCheckedRadioButtonId());
                String str2 = zweiChecked.getText().toString();
                String zahl2 = str2.replaceAll("[^0-9\\.]", "");

                RadioButton dreiChecked = findViewById(drei.getCheckedRadioButtonId());
                String str3 = dreiChecked.getText().toString();
                String zahl3 = str3.replaceAll("[^0-9\\.]", "");

                RadioButton vierChecked = findViewById(vier.getCheckedRadioButtonId());
                String str4 = vierChecked.getText().toString();
                String zahl4 = str4.replaceAll("[^0-9\\.]", "");

                RadioButton fuenfChecked = findViewById(fuenf.getCheckedRadioButtonId());
                String str5 = fuenfChecked.getText().toString();
                String zahl5 = str5.replaceAll("[^0-9\\.]", "");

                RadioButton sechsChecked = findViewById(sechs.getCheckedRadioButtonId());
                String str6 = sechsChecked.getText().toString();
                String zahl6 = str6.replaceAll("[^0-9\\.]", "");

                RadioButton siebenChecked = findViewById(sieben.getCheckedRadioButtonId());
                String str7 = siebenChecked.getText().toString();
                String zahl7 = str7.replaceAll("[^0-9\\.]", "");

                RadioButton achtChecked = findViewById(acht.getCheckedRadioButtonId());
                String str8 = achtChecked.getText().toString();
                String zahl8 = str8.replaceAll("[^0-9\\.]", "");

                RadioButton neunChecked = findViewById(neun.getCheckedRadioButtonId());
                String str9 = neunChecked.getText().toString();
                String zahl9 = str9.replaceAll("[^0-9\\.]", "");

                String[] zahlarray = {zahl, zahl1, zahl2, zahl3, zahl4, zahl5, zahl6, zahl7, zahl8, zahl9};
                for (String wert : zahlarray) {
                    counter += Integer.parseInt(wert);
                }

                Toast.makeText(BarthelIndex.this, counter + " wurde aktualisiert", Toast.LENGTH_SHORT).show();
                mRef.child("barthelScore").setValue(String.valueOf(counter));
                mTotalScore.setText("Total Score: " + counter);
            }
        });

    }


}