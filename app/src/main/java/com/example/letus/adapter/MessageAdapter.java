package com.example.letus.adapter;

import android.content.Context;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letus.model.MessageModel;
import com.example.letus.model.User;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView {
    private ArrayList<MessageModel> MessageModel;
    private ArrayList<MessageModel> MessageMe;
    private Context context;

    public MessageAdapter(@NonNull Context context) {
        super(context);
    }
}
