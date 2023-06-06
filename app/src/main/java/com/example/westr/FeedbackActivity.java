package com.example.westr;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class FeedbackActivity extends AppCompatActivity {

    private ListView listViewFeedback;
    private EditText editTextFeedback;
    private Button buttonSubmit;
    private List<String> feedbackList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        listViewFeedback = findViewById(R.id.listViewFeedback);
        editTextFeedback = findViewById(R.id.editTextFeedback);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        feedbackList = new ArrayList<>();

        // Retrieve the initial feedbacks from your data source (e.g., server or local database)
        // Add the retrieved feedbacks to the feedbackList
        feedbackList.add("User A - Great app!");
        feedbackList.add("User B - Awesome features!");

        // Create an ArrayAdapter to populate the ListView with the feedback list
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feedbackList);

        // Set the adapter on the ListView
        listViewFeedback.setAdapter(adapter);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback = editTextFeedback.getText().toString().trim();

                if (!feedback.isEmpty()) {
                    // Add the user's feedback to the feedbackList
                    feedbackList.add("User - " + feedback);
                    adapter.notifyDataSetChanged();
                    editTextFeedback.getText().clear();
                    Toast.makeText(FeedbackActivity.this, "Feedback submitted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FeedbackActivity.this, "Please enter your feedback", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listViewFeedback.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected feedback item
                String selectedFeedback = feedbackList.get(position);

                // Show a Toast or perform any action with the selected feedback
                Toast.makeText(FeedbackActivity.this, "Selected Feedback: " + selectedFeedback, Toast.LENGTH_SHORT).show();
            }
        });

        listViewFeedback.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Remove the selected feedback from the list
                feedbackList.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(FeedbackActivity.this, "Feedback deleted", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
