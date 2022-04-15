package com.example.letus.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageFragment extends AppCompatActivity {

    private RecyclerView mMessageRecycler;
    private MessageAdapter mMessageAdapter;
    ArrayList<MessageModel> messageList = new ArrayList<MessageModel>();
    User user;
    User user2;
    FirebaseUser fuser;
    DatabaseReference databaseReference;
    TextView username;
    Button btn_send;
    EditText txt_send;
    String userid,userlogin;
    DatabaseReference reference;


    //String login, email, birthDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        fuser = FirebaseAuth.getInstance().getCurrentUser();



        mMessageAdapter = new MessageAdapter(this, messageList);


        user2 = new User("Chris","Chris01@toto.fr", "23 Janvier 2001","WhTZJXWBfkRVYW8sz37RQUc618w2");
        /*messageList.add(new MessageModel("Salut mec", 80, userid,fuser.getUid()));
        messageList.add(new MessageModel("Go taffer le projet là", 80,userid, fuser.getUid()));
        messageList.add(new MessageModel("Mange tes morts", 80,fuser.getUid(), userid));
        messageList.add(new MessageModel("FF", 80,fuser.getUid(), userid));*/

        //messageList.add(new MessageModel("ah ouais t'es cool toi",80,userMoi));

        Bundle bundle = getIntent().getExtras();
        userid = bundle.getString("userid");
        userlogin = bundle.getString("userlogin");

        mMessageRecycler = (RecyclerView) findViewById(R.id.recycler_gchat);




        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);

        username = findViewById(R.id.text_gchat_username);
        btn_send = findViewById(R.id.button_gchat_send);
        txt_send = findViewById(R.id.edit_gchat_message);
        String text = txt_send.getText().toString();
        username.setText(userlogin);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = txt_send.getText().toString();
                if (!msg.equals("")){
                    sendMessage(fuser.getUid(),userid,msg);
                    MessageModel nMsg = new MessageModel(msg,userid,fuser.getUid(),20);
                    messageList.add(nMsg);
                    mMessageRecycler.setAdapter(mMessageAdapter);

                }
                else{
                    Toast.makeText(MessageFragment.this,"You can't send empty message",
                            Toast.LENGTH_SHORT).show();
                }
                txt_send.setText("");
            }
        });

        reference = FirebaseDatabase.getInstance().getReference().child("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                readMessage(fuser.getUid(),userid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




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
    private void sendMessage (String sender, String receiver, String message){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("message", message);
        hashMap.put("sentAt", 50);
        FirebaseDatabase.getInstance().getReference().child("chats").push().setValue(hashMap);
    }

    private void readMessage (String myid, String userid){
        messageList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference().child("chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String msg = (String) dataSnapshot.child("message").getValue();
                    String receiver = (String) dataSnapshot.child("receiver").getValue();
                    String sender = (String) dataSnapshot.child("sender").getValue();
                    long sentAt = (long) dataSnapshot.child("sentAt").getValue();
                    MessageModel message = new MessageModel(msg,receiver,sender,sentAt);

                    if((message.getReceiver().equals(myid) && message.getSender().equals(userid)) ||
                            (message.getSender().equals(myid) && message.getReceiver().equals(userid))){
                        messageList.add(message);
                    }
                    mMessageAdapter = new MessageAdapter(MessageFragment.this, messageList);
                    mMessageRecycler.setAdapter(mMessageAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



}
