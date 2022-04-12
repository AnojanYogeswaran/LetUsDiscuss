package com.example.letus.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.DatabaseMetaData;
import java.util.Calendar;
import java.util.regex.Pattern;

public class SignInFragment extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private EditText editTextEmail;
    private EditText editTextBirthDate;
    private CheckBox checkBoxAgree;
    private Button buttonCreateAcc;
    User user;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        editTextLogin =  (EditText) findViewById(R.id.login);
        editTextPassword =  (EditText) findViewById(R.id.password);
        editTextConfirmPassword = (EditText) findViewById(R.id.confirmPassword);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextBirthDate = (EditText) findViewById(R.id.birthdate);
        editTextBirthDate.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "JJMMAAAA";
            private Calendar cal = Calendar.getInstance();

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    editTextBirthDate.setText(current);
                    editTextBirthDate.setSelection(sel < current.length() ? sel : current.length());
                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });
        checkBoxAgree = (CheckBox) findViewById(R.id.checkbox_agree);
        buttonCreateAcc = (Button) findViewById(R.id.buttonSaveContact);
        buttonCreateAcc.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
            switch (view.getId())  {
                case R.id.buttonSaveContact:
                    registerUser();
                    break;
            }

    }

    private void registerUser() {
    String Email = editTextEmail.getText().toString();
    String Login = editTextLogin.getText().toString();
    String Password = editTextPassword.getText().toString();
    String ConfirmPass = editTextConfirmPassword.getText().toString();
    String birthDate = editTextBirthDate.getText().toString();

    if(Email.isEmpty() || Login.isEmpty() || Password.isEmpty() || ConfirmPass.isEmpty() || birthDate.isEmpty()) {
        Toast.makeText(SignInFragment.this , "Tous les champs sont requis" , Toast.LENGTH_SHORT).show();

    }
    mAuth.createUserWithEmailAndPassword(Email , Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()) {
                User user = new User(Login , Email , Password , birthDate);
                FirebaseDatabase.getInstance().getReference("users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                        Toast.makeText(SignInFragment.this ,"Utilisateur Créer", Toast.LENGTH_SHORT).show();
                        Intent intent = new  Intent(SignInFragment.this , ProfileFragment.class);
                        startActivity(intent);
                        }
                    }
                });
            }
        }
    });
    }
}