package com.example.letus;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letus.model.DiscussionModel;

import java.util.ArrayList;

public class DiscussionActivity extends AppCompatActivity {

    ArrayList<DiscussionModel> discussions = new ArrayList<DiscussionModel>();
    ListView listViewdiscussion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        discussions.add(new DiscussionModel("Joan", "qui sont les con qui on voté macron", "le 11/04/2022"));
        discussions.add(new DiscussionModel("Chris", "vien on Aram", "le 08/04/2022"));
        discussions.add(new DiscussionModel("Anojan", "ta avancé sur le mémoire ?", "le 12/02/2022"));


        listViewdiscussion = (ListView) findViewById(R.id.listViewDiscussion);
        DiscussionAdapter adapter = new DiscussionAdapter(discussions, this);
        listViewdiscussion.setAdapter(adapter);

    }
}
