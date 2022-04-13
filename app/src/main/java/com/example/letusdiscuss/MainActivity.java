package com.example.letusdiscuss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.example.mywhatapps.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<DiscussionModel> discussions= new ArrayList<DiscussionModel>();
    ListView listViewdiscussion;
    int selecteditem;
    ImageButton addConv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        discussions.add(new DiscussionModel("Joan","qui sont les con qui on voté macron","le 11/04/2022"));
        discussions.add(new DiscussionModel("Chris","vien on Aram","le 08/04/2022"));
        discussions.add(new DiscussionModel("Anojan","ta avancé sur le mémoire ?","le 12/02/2022"));



        listViewdiscussion = (ListView) findViewById(R.id.listViewDiscussion);
        DiscussionAdapter adapter = new DiscussionAdapter(discussions, this);
        listViewdiscussion.setAdapter(adapter);

        registerForContextMenu(listViewdiscussion);
        listViewdiscussion.setOnItemClickListener(this);

        addConv = (ImageButton) findViewById(R.id.imageAddDiscu);
        


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_context_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuItem_delete){
            discussions.remove(selecteditem);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        selecteditem = i;
        view.post(new Runnable() {
            @Override
            public void run() {
                showPopupMenu(view);
            }
        });
    }

    private void showPopupMenu(View view) {

        PopupMenu popup = new PopupMenu(this, view);

        popup.getMenuInflater().inflate(R.menu.layout_context_menu, popup.getMenu());

        popup.show();
    }
}

