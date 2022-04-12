package com.example.letus.fragments;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.letus.R;
import com.example.letus.model.MessageModel;
import com.example.letus.model.User;

import java.util.ArrayList;

public class MessageFragment extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }


}
