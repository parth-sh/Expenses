package com.example.expenses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton add_items;
    RecyclerView items_list;
    TextView items_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_items = findViewById(R.id.activity_main_fab_add_items);
        items_list = findViewById(R.id.activity_main_rv_items_list);
        items_count = findViewById(R.id.activity_main_tv_items_count);
    }
}