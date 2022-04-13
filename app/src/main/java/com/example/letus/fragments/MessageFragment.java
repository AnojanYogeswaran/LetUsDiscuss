package com.example.letus.fragments;

import android.os.Bundle;
import android.widget.ListView;

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

import java.util.ArrayList;

public class MessageFragment extends AppCompatActivity {

    private RecyclerView mMessageRecycler;
    private MessageAdapter mMessageAdapter;
    ArrayList<MessageModel> messageList = new ArrayList<MessageModel>();
    User user;
    FirebaseUser userMe = FirebaseAuth.getInstance().getCurrentUser();
    User userMoi;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        user = new User("Titi","Titi@gmail.com");
        userMoi = new User(userMe.getDisplayName(),userMe.getEmail());

        messageList.add(new MessageModel("Salut mec", 80, user));
        messageList.add(new MessageModel("Mange tes morts", 80, user ));
        //messageList.add(new MessageModel("ah ouais t'es cool toi",80,userMoi));

        mMessageRecycler = (RecyclerView) findViewById(R.id.recycler_gchat);
        mMessageAdapter = new MessageAdapter(this, messageList);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);
    }


}
