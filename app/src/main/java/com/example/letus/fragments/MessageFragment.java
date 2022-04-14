package com.example.letus.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letus.R;
import com.example.letus.adapter.MessageAdapter;
import com.example.letus.model.DiscussionModel;
import com.example.letus.model.MessageModel;
import com.example.letus.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessageFragment extends AppCompatActivity {

    private RecyclerView mMessageRecycler;
    private MessageAdapter mMessageAdapter;
    ArrayList<MessageModel> messageList = new ArrayList<MessageModel>();
    User user;
    DatabaseReference databaseReference;
    Button btn_send;
    EditText txt_send;
    //String login, email, birthDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        mMessageAdapter = new MessageAdapter(this, messageList);
        /*databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Get Post object and use the values to update the UI

                String login=snapshot.child("login").getValue(String.class).toString() ;
                String email=snapshot.child("email").getValue(String.class).toString();
                String birthDate=snapshot.child("birthdate").getValue(String.class ).toString();


                // ..
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        user = new User("Hello","hello@gmail.com", "23 janvier 2001");
        messageList.add(new MessageModel("Salut mec", 80, user));
        messageList.add(new MessageModel("Mange tes morts", 80, user));





        //String userid = getIntent().getStringExtra("userid");
        //messageList.add(new MessageModel("ah ouais t'es cool toi",80,userMoi));

        mMessageRecycler = (RecyclerView) findViewById(R.id.recycler_gchat);

        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);


        //username = findViewById(R.id.text_gchat_username);
        btn_send = findViewById(R.id.button_gchat_send);
        txt_send = findViewById(R.id.edit_gchat_message);
        String text = txt_send.getText().toString();




        /*reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                //username.setText(user.getLogin());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


    }

    /*@Override
    public void onClick(View view) {
        Button b= (Button) view;
        btn_send = findViewById(R.id.button_gchat_send);
        txt_send = findViewById(R.id.edit_gchat_message);
        String text = txt_send.getText().toString();
            MessageModel message = new MessageModel(text,userMe);
            startActivity(intent);
        }
    }*/


}
