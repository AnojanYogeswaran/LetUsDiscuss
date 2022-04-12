package com.example.letus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.letus.fragments.ProfileFragment;
import com.example.letus.fragments.SignInFragment;
import com.example.letus.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
    protected void onStart() {
        super.onStart();
        FirebaseUser user = nAuth.getCurrentUser();
        if( user == null) {
            startActivity(new Intent(MainActivity.this , MainActivity.class));
        }
    }

    @Override
    public void onClick(View view) {

    }

    private void loginUser() {
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        if(Email.isEmpty() || Password.isEmpty()) {
            Toast.makeText(MainActivity.this , "Veuillez remplir tout les champs", Toast.LENGTH_SHORT).show();
        } else {
            nAuth.signInWithEmailAndPassword(Email , Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful()) {
                        Toast.makeText(MainActivity.this , "Login Succes", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this , ProfileFragment.class);
                        startActivity(intent);
                     }
                }
            });
        }
    }
}