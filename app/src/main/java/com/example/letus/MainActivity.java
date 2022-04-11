package com.example.letus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.letus.fragments.SignInFragment;

public class MainActivity extends AppCompatActivity {

    private SignInFragment signInFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signInFragment = new SignInFragment();

    }
}