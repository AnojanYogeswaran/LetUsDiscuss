package com.example.letus.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letus.MainActivity;
import com.example.letus.adapter.DiscussionAdapter;
import com.example.letus.R;
import com.example.letus.model.DiscussionModel;

import java.util.ArrayList;

public class DiscussionFragment extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    ArrayList<DiscussionModel> discussions = new ArrayList<DiscussionModel>();
    ListView listViewDiscussion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        discussions.add(new DiscussionModel("Joan", "qui sont les con qui on voté macron", "le 11/04/2022"));
        discussions.add(new DiscussionModel("Chris", "vien on Aram", "le 08/04/2022"));
        discussions.add(new DiscussionModel("Anojan", "ta avancé sur le mémoire ?", "le 12/02/2022"));


        listViewDiscussion = (ListView) findViewById(R.id.listViewDiscussion);
        DiscussionAdapter adapter = new DiscussionAdapter(discussions, this);
        listViewDiscussion.setAdapter(adapter);
        listViewDiscussion.setOnItemClickListener(this);

    }
    @Override
    public void onClick(View view) {

    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        DiscussionModel discussion = discussions.get(i);
        Intent intent = new Intent(this , MessageFragment.class);
        startActivity(intent);
    }
}
