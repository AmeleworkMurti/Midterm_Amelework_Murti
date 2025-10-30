package com.example.multiplicationtable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText inputNumber;
    Button btnGenerate, btnHistory;
    ListView listView;
    ArrayList<String> tableList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    static ArrayList<Integer> historyNumbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNumber = findViewById(R.id.inputNumber);
        btnGenerate = findViewById(R.id.btnGenerate);
        btnHistory = findViewById(R.id.btnHistory);
        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tableList);
        listView.setAdapter(adapter);

        btnGenerate.setOnClickListener(v -> generateTable());
        btnHistory.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(i);
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            showDeleteDialog(position);
        });
    }
// takes a number and generates the multiplication
    private void generateTable() {
        String input = inputNumber.getText().toString();
        if (input.isEmpty()) {
            Toast.makeText(this, "Enter a number", Toast.LENGTH_SHORT).show();
            return;
        }

        int num = Integer.parseInt(input);
        tableList.clear();
        for (int i = 1; i <= 10; i++) {
            tableList.add(num + " × " + i + " = " + (num * i));
        }
        adapter.notifyDataSetChanged();

        if (!historyNumbers.contains(num)) {
            historyNumbers.add(num);
        }
    }
      // Delete dialog pop up
    private void showDeleteDialog(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Delete row?")
                .setMessage("Do you want to delete this row?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    String deleted = tableList.remove(position);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(this, "Deleted: " + deleted, Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", null)
                .show();
    }
}
