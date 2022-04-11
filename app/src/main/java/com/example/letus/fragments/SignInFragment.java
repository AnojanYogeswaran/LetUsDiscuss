package com.example.letus.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.letus.R;

public class SignInFragment extends Fragment implements View.OnClickListener {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private EditText editTextEmail;
    private EditText editTextBirthDate;
    private CheckBox checkBoxAgree;
    private Button buttonCreateAcc;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_signup, null);
        editTextLogin = v.findViewById(R.id.login);
        editTextPassword = v.findViewById(R.id.password);
        editTextConfirmPassword = v.findViewById(R.id.confirmPassword);
        editTextEmail = v.findViewById(R.id.email);
        editTextBirthDate = v.findViewById(R.id.birthdate);
        checkBoxAgree = v.findViewById(R.id.checkbox_agree);
        buttonCreateAcc = v.findViewById(R.id.buttonSaveContact);
        buttonCreateAcc.setOnClickListener(this);
        return v;
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

        }
    }
}