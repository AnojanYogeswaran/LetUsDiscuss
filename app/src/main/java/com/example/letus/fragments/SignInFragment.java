package com.example.letus.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.letus.R;
import androidx.core.content.ContextCompat;
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
import java.util.regex.Pattern;

public class SignInFragment extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private EditText editTextEmail;
    private EditText editTextBirthDate;
    private CheckBox checkBoxAgree;
    private Button buttonCreateAcc;
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
        checkBoxAgree = (CheckBox) findViewById(R.id.checkbox_agree);
        buttonCreateAcc = (Button) findViewById(R.id.buttonSaveContact);
        buttonCreateAcc.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

        checkBoxAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    buttonCreateAcc.setBackgroundTintList(ContextCompat.getColorStateList(SignInFragment.this, R.color.blue));
                }
                else{
                    buttonCreateAcc.setBackgroundTintList(ContextCompat.getColorStateList(SignInFragment.this, R.color.gray));
                }
            }
    });
    }

    private void registerUser() {
    String Email = editTextEmail.getText().toString();
    String Login = editTextLogin.getText().toString();
    String Password = editTextPassword.getText().toString();
    String ConfirmPass = editTextConfirmPassword.getText().toString();
    String birthDate = editTextBirthDate.getText().toString();

    if(Email.isEmpty() || Login.isEmpty() || Password.isEmpty() || ConfirmPass.isEmpty() || birthDate.isEmpty()) {
        Toast.makeText(SignInFragment.this , "Tous les champs sont requis" , Toast.LENGTH_SHORT).show();
            return;
    }
    mAuth.createUserWithEmailAndPassword(Email , Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()) {
                User user = new User(Login , Email , birthDate);
                FirebaseDatabase.getInstance().getReference("users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                        Toast.makeText(SignInFragment.this ,"Utilisateur Créer", Toast.LENGTH_SHORT).show();
                        Intent intent = new  Intent(SignInFragment.this , MessageFragment.class);
                        startActivity(intent);
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignInFragment.this, "Error"+ task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }
    });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case    R.id.buttonSaveContact:
                registerUser();
                break;
        }
    }
}