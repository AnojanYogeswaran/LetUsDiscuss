package com.example.letus.adapter;

import android.content.Context;
import android.os.Message;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.firebase.installations.Utils;

import java.util.ArrayList;
import java.util.List;


public class MessageAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<MessageModel> mMessageList;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 0;
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private FirebaseUser user;

    public MessageAdapter(Context context, ArrayList<MessageModel> messageList) {
        mContext = context;
        mMessageList = messageList;
        user = FirebaseAuth.getInstance().getCurrentUser();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_me, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_other, parent, false);
            return new ReceivedMessageHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel message =  mMessageList.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    public int getItemViewType(int position){
        MessageModel message = (mMessageList.get(position));
        if (message.getSender().getEmail().equals(this.user.getEmail())){
            return VIEW_TYPE_MESSAGE_SENT;
        }
        else
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }


    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
    TextView messageText, timeText, nameText;
    ImageView profileImage;

    ReceivedMessageHolder(View itemView) {
        super(itemView);
        messageText = (TextView) itemView.findViewById(R.id.text_gchat_message_other);
        timeText = (TextView) itemView.findViewById(R.id.text_gchat_timestamp_other);
        nameText = (TextView) itemView.findViewById(R.id.text_gchat_user_other);
        //profileImage = (ImageView) itemView.findViewById(R.id.image_gchat_profile_other);
    }

    void bind(MessageModel message) {
        messageText.setText(message.getMessage());

        // Format the stored timestamp into a readable String using method.
        //timeText.setText(Utils.formatDateTime(message.getCreatedAt()));
        nameText.setText(message.getSender().getLogin());

        // Insert the profile image from the URL into the ImageView.
        //Utils.displayRoundImageFromUrl(mContext, message.getSender().getProfileUrl(), profileImage);
    }
}
    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;

        SentMessageHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.text_gchat_message_me);
            timeText = (TextView) itemView.findViewById(R.id.text_gchat_timestamp_me);
        }

        void bind(MessageModel message) {
            messageText.setText(message.getMessage());

            // Format the stored timestamp into a readable String using method.
            //timeText.setText(Utils.formatDateTime(message.getCreatedAt()));
        }
    }
}