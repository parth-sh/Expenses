package com.example.expenses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateEntryActivity extends AppCompatActivity {

    EditText item_name, item_cost;
    Button save_item;

    public static final String NAME = "com.example.android.wordlistsql.NAME";
    public static final String COST = "com.example.android.wordlistsql.COST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entry);

        save_item = findViewById(R.id.activity_create_entry_btn_save);
        item_name = findViewById(R.id.activity_create_entry_et_name);
        item_cost = findViewById(R.id.activity_create_entry_et_cost);

        save_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(item_cost.getText()) && TextUtils.isEmpty(item_name.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    replyIntent.putExtra(NAME, item_name.getText().toString());
                    replyIntent.putExtra(COST, item_cost.getText().toString());
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}