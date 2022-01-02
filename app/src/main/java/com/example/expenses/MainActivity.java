package com.example.expenses;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton add_items;
    RecyclerView items_list;
    TextView items_count;

    ActivityResultLauncher<Intent> startAddActivityForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        String name = intent.getStringExtra(CreateEntryActivity.NAME);
                        String cost = intent.getStringExtra(CreateEntryActivity.COST);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_items = findViewById(R.id.activity_main_fab_add_items);
        items_list = findViewById(R.id.activity_main_rv_items_list);
        items_count = findViewById(R.id.activity_main_tv_items_count);

        add_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAddActivityForResult.launch(new Intent(MainActivity.this, CreateEntryActivity.class));
            }
        });
    }
}