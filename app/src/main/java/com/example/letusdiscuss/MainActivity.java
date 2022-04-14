package com.example.letusdiscuss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
    DiscussionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        discussions.add(new DiscussionModel("Joan","qui sont les con qui on voté macron","le 11/04/2022"));
        discussions.add(new DiscussionModel("Chris","vien sur LOL on va Aram","le 08/04/2022"));
        discussions.add(new DiscussionModel("Anojan","ta avancé sur le mémoire ?","le 12/02/2022"));



        listViewdiscussion = (ListView) findViewById(R.id.listViewDiscussion);
        adapter = new DiscussionAdapter(discussions, this);
        listViewdiscussion.setAdapter(adapter);

        registerForContextMenu(listViewdiscussion);
        listViewdiscussion.setOnItemClickListener(this);

        addConv = (ImageButton) findViewById(R.id.imageAddDiscu);



        listViewdiscussion.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int
                    position, long id) {

                // it will get the position of selected item from the ListView

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_menu_delete)
                        .setTitle("Are you sure...")
                        .setMessage("Do you want to delete the selected item..?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                discussions.remove(selecteditem);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No" , null).show();

                return true;
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        selecteditem = i;
        view.post(new Runnable() {
           // @Override
            public void run() {
            }
        });
    }

}

