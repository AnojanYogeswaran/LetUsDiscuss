package com.example.letus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.letus.R;
import com.example.letus.model.DiscussionModel;

import java.util.ArrayList;

public class DiscussionAdapter extends BaseAdapter {

    private ArrayList<DiscussionModel> discussions;
    private Context context;

    public DiscussionAdapter(ArrayList<DiscussionModel> discussions, Context context) {
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
        firstname.setText(discussions.get(i).getFirstname());

        TextView lastMessage = view.findViewById(R.id.textViewItemPreviewMessage);
        lastMessage.setText(discussions.get(i).getLastmessage());

        TextView date = view.findViewById(R.id.textViewItemDate);
        date.setText(discussions.get(i).getDate());

        return view;
    }
}
