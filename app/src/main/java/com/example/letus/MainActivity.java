package com.example.letus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.letus.fragments.DiscussionFragment;
import com.example.letus.fragments.MessageFragment;
import com.example.letus.fragments.SignInFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonCreate;
    private FirebaseAuth nAuth;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCreate = (Button) findViewById(R.id.intentSignUp);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , SignInFragment.class);
                startActivity(intent);
            }
        });

        buttonLogin = (Button) findViewById(R.id.logAcc);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        nAuth = FirebaseAuth.getInstance();

}

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser user = nAuth.getCurrentUser();
        if(user != null){
            user.reload();
        }
    }


    public void onClick(View view) {

    }

    private void loginUser() {
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        if(Email.isEmpty() || Password.isEmpty()) {
            Toast.makeText(MainActivity.this , "Veuillez remplir tout les champs", Toast.LENGTH_SHORT).show();
        }
        else {
            nAuth.signInWithEmailAndPassword(Email , Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful()) {
                        Toast.makeText(MainActivity.this , "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this , DiscussionFragment.class);
                        startActivity(intent);
                     }
                }
            });
        }
    }
}