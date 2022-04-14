package com.example.letus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.letus.adapter.DiscussionAdapter;
import com.example.letus.fragments.DiscussionFragment;
import com.example.letus.fragments.MessageFragment;
import com.example.letus.fragments.SignInFragment;
import com.example.letus.model.DiscussionModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonCreate;
    private FirebaseAuth nAuth;
    ArrayList<DiscussionModel> discussions= new ArrayList<DiscussionModel>();
    ListView listViewdiscussion;
    int selecteditem;
    ImageButton addConv;
    DiscussionAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//abder
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


        // fin abder

        buttonCreate = (Button) findViewById(R.id.intentSignUp);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , SignInFragment.class);
                startActivity(intent);
            }
        });

        buttonLogin = (Button) findViewById(R.id.logAcc);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        nAuth = FirebaseAuth.getInstance();

}

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser user = nAuth.getCurrentUser();
        if(user != null){
            user.reload();
        }
    }


    public void onClick(View view) {

    }

    private void loginUser() {
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        if(Email.isEmpty() || Password.isEmpty()) {
            Toast.makeText(MainActivity.this , "Veuillez remplir tout les champs", Toast.LENGTH_SHORT).show();
        }
        else {
            nAuth.signInWithEmailAndPassword(Email , Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful()) {
                        Toast.makeText(MainActivity.this , "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this , DiscussionFragment.class);
                        startActivity(intent);
                     }
                }
            });
        }
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