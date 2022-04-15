package com.example.letus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.letus.R;
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

public class DiscussionAdapter extends BaseAdapter {

    private ArrayList<User> discussions;
    private Context context;
    String theLastMessage;

    public DiscussionAdapter(ArrayList<User> discussions, Context context) {
        this.discussions = discussions;
        this.context = context;
    }

    @Override
    public int getCount() {
        return discussions.size();
    }

    @Override
    public Object getItem(int i) {
        return discussions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.itemdiscution, viewGroup,false);
        }

        TextView firstname= view.findViewById(R.id.textViewItemPrenom);
        firstname.setText(discussions.get(i).getLogin());
        TextView preview = view.findViewById(R.id.textViewItemPreviewMessage);
        lastMessage(discussions.get(i).getId(),preview);

        //TextView lastMessage = view.findViewById(R.id.textViewItemPreviewMessage);
        //lastMessage.setText(discussions.get(i).getLastmessage());

        //TextView date = view.findViewById(R.id.textViewItemDate);
        //date.setText(discussions.get(i).getDate());

        return view;
    }
    private void lastMessage(final String userid, final TextView last_msg){
        theLastMessage = "default";

        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String msg = (String) dataSnapshot.child("message").getValue();
                    String receiver = (String) dataSnapshot.child("receiver").getValue();
                    String sender = (String) dataSnapshot.child("sender").getValue();
                    String sentAt = (String) dataSnapshot.child("sentAt").getValue();
                    MessageModel message = new MessageModel(msg,receiver,sender,sentAt);
                    if (firebaseUser != null && message != null) {
                        if (message.getReceiver().equals(firebaseUser.getUid()) && message.getSender().equals(userid) ||
                                message.getReceiver().equals(userid) && message.getSender().equals(firebaseUser.getUid())) {
                            theLastMessage = message.getMessage();
                        }
                    }
                }
                if(last_msg!= null) {
                    switch (theLastMessage) {
                        case "default":
                            last_msg.setText("No Message");
                            break;
                        default:
                            last_msg.setText(theLastMessage);
                            break;
                    }
                }
                theLastMessage = "default";
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}