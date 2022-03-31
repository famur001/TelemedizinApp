package com.example.telemedizin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.telemedizin.Common.Common;
import com.example.telemedizin.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button regButton;
    private EditText mPersonalID, mPassword;
    private Button mLoginBtn;
    private DatabaseReference mRef;

    protected void onCreate(Bundle save) {
        super.onCreate(save);
        setContentView(R.layout.activity_main);

        if (Common.isLogged()) {
            startActivity(new Intent(MainActivity.this, Patientenliste.class));
            finish();
        }

        mRef = FirebaseDatabase.getInstance().getReference("User");

        mPersonalID = findViewById(R.id.personalID);
        mPassword = findViewById(R.id.mypass);
        mLoginBtn = findViewById(R.id.btnlogin);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(mPersonalID.getText().toString(), mPassword.getText().toString());
            }
        });

        regButton = (Button) findViewById(R.id.btnNewAccount);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oeffneReg();
            }
        });
    }

    public void oeffneReg() {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    public void login(String personalID, String password) {

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(personalID).exists()) {
                    User user = dataSnapshot.child(personalID).getValue(User.class);
                    if (user.getPassword().equals(password)) {

                        Common.setCurrentUser(user);
                        Toast.makeText(MainActivity.this, "Hi " + Common.getCurrentUser().getFullName(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Patientenliste.class));
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Fehler! überprüfen Sie bitte Ihre Eingaben nochmal!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Du bist noch nicht regestriert!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

}
