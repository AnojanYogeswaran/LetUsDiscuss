package com.example.letus.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.letus.MainActivity;
import com.example.letus.R;
import com.example.letus.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends AppCompatActivity {

    private Button DecoButton;
    private DatabaseReference reference;
    private String id;
    private TextView textViewEmail;
    private TextView textViewLogin;
    private TextView textViewBirth;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        DecoButton = (Button) findViewById(R.id.buttonDeco);
        DecoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ProfileFragment.this, MainActivity.class);
                startActivity(intent);
            }
        });
        reference = FirebaseDatabase.getInstance().getReference("users");
        id = user.getUid();
        textViewEmail = (TextView) findViewById(R.id.email);
        textViewLogin = (TextView) findViewById(R.id.login);
        textViewBirth = (TextView) findViewById(R.id.birthdate);

        reference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User profile = snapshot.getValue(User.class);
                if (profile != null) {
                    String username = profile.login;
                    String email = profile.email;
                    String birthdate = profile.birthDate;

                    textViewEmail.setText(email);
                    textViewLogin.setText(username);
                    textViewBirth.setText(birthdate);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}