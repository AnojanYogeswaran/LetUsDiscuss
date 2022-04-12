package com.example.letus.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.letus.R;
import com.example.letus.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.DatabaseMetaData;

public class SignInFragment extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private EditText editTextEmail;
    private EditText editTextBirthDate;
    private CheckBox checkBoxAgree;
    private Button buttonCreateAcc;
    User user;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://letus-a7fb1-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        editTextLogin =  (EditText) findViewById(R.id.login);
        editTextPassword =  (EditText) findViewById(R.id.password);
        editTextConfirmPassword = (EditText) findViewById(R.id.confirmPassword);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextBirthDate = (EditText) findViewById(R.id.birthdate);
        checkBoxAgree = (CheckBox) findViewById(R.id.checkbox_agree);
        buttonCreateAcc = (Button) findViewById(R.id.buttonSaveContact);
        buttonCreateAcc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String login = editTextLogin.getText().toString();
        final String Password = editTextPassword.getText().toString();
        final String ConfirmPassword = editTextConfirmPassword.getText().toString();
        final String Email = editTextEmail.getText().toString();
        final String BirthDate = editTextBirthDate.getText().toString();

        if (login.isEmpty() || Password.isEmpty() || ConfirmPassword.isEmpty() ||
                Email.isEmpty() || BirthDate.isEmpty()) {
           Toast.makeText(view.getContext(),
                   "Tout les champs nécessaires", Toast.LENGTH_SHORT).show();
        } else {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.child("users").hasChild(login)) {
                    Toast.makeText(view.getContext() , "Identifiant déjà utilisé" , Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("users").child(login).setValue(login);
                    databaseReference.child("users").child(login).setValue(Password);
                    databaseReference.child("users").child(login).setValue(login);
                    Toast.makeText(view.getContext() , "Création de compte réussi" , Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SignInFragment.this, ProfileFragment.class);
                    intent.putExtra("name" , login);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        }
    }
}