package com.example.multiplicationtable;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HistoryActivity extends AppCompatActivity {

    ListView historyList;
    ArrayAdapter<Integer> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        historyList = findViewById(R.id.historyList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MainActivity.historyNumbers);
        historyList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.menu_clear_history) {
            new AlertDialog.Builder(this)
                    .setTitle("Clear All?")
                    .setMessage("Do you want to clear all history?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        MainActivity.historyNumbers.clear();
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, "History cleared", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
