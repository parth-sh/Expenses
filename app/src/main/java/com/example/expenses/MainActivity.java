package com.example.expenses;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.expenses.data.Entry;
import com.example.expenses.rv_components.CustomListAdapter;
import com.example.expenses.viewModel.EntryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton add_items;
    RecyclerView entries_list;
    TextView entries_count;
    CustomListAdapter customListAdapter;
    EntryViewModel entryViewModel;

    ActivityResultLauncher<Intent> startCreateEntryActivityForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        String name = intent.getStringExtra(CreateEntryActivity.NAME);
                        String cost = intent.getStringExtra(CreateEntryActivity.COST);
                        Entry entry = new Entry(name, Integer.parseInt(cost), Calendar.getInstance().getTime());
                        entryViewModel.insert(entry);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_items = findViewById(R.id.activity_main_fab_add_entries);
        entries_list = findViewById(R.id.activity_main_rv_entries_list);
        entries_count = findViewById(R.id.activity_main_tv_entries_count);

        add_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCreateEntryActivityForResult.launch(new Intent(MainActivity.this, CreateEntryActivity.class));
            }
        });

        setUpRecyclerView();

        entryViewModel = new ViewModelProvider(this).get(EntryViewModel.class);
        entryViewModel.getEntriesCost().observe(this, cost -> {
            if (cost == null) {
                cost = Integer.valueOf(0);
            }
            entries_count.setText("₹ "+cost);
        });
        entryViewModel.getEntriesList().observe(this, entryList -> {
            // Update the cached copy of entries in the adapter.
            customListAdapter.submitList(entryList);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.delete_entries:
                entryViewModel.deleteAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpRecyclerView() {
        entries_list.setLayoutManager(new LinearLayoutManager(this));
        customListAdapter = new CustomListAdapter(new CustomListAdapter.EntryDiff());
        entries_list.setAdapter(customListAdapter);
    }
}