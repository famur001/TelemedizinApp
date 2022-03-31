package com.example.telemedizin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.telemedizin.Model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    Button buttonEins;
    EditText editEins;
    EditText editzwei;
    EditText editDrei;

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_signup);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference userTabelle = database.getReference("User");

        buttonEins = (Button) findViewById(R.id.buttonAcount);
        editEins = (EditText) findViewById(R.id.editName);
        editzwei = (EditText) findViewById(R.id.editPass);
        editDrei = (EditText) findViewById(R.id.editEmail);
                buttonEins.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onClick(View view){

                        final String name = editEins.getText().toString().trim();
                        final String anmeldeID = editDrei.getText().toString().trim();
                        final String password = editzwei.getText().toString().trim();

                        if(TextUtils.isEmpty(name)){
                            editEins.setError("Name ist leer");
                            return;

                        }
                        if(TextUtils.isEmpty(password)){
                            editzwei.setError("Passwort ist leer");
                            return;
                        }

                        User newUser = new User(anmeldeID, name, password);
                        userTabelle.child(anmeldeID).setValue(newUser);
                        Toast.makeText(SignUp.this, "Du wurdest erfolgreich registriert", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUp.this, MainActivity.class));

                    }

        });
    }
}
