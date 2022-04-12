package com.example.letus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.letus.fragments.SignInFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity  {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonCreate;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://letus-a7fb1-default-rtdb.firebaseio.com/");


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCreate = (Button) findViewById(R.id.intentSignUp);
        buttonLogin = (Button) findViewById(R.id.logAcc);
        editTextLogin = (EditText) findViewById(R.id.login);
        editTextPassword = (EditText) findViewById(R.id.password);



        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String login = editTextLogin.getText().toString();
                final String Password = editTextPassword.getText().toString();

                if (login.isEmpty() || Password.isEmpty()) {
                    Toast.makeText(view.getContext(),
                            "Tout les champs nécessaires", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , SignInFragment.class );
                startActivity(intent);

            }
        });




    }

}