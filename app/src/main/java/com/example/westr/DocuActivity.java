package com.example.westr;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.westr.R;

import java.lang.ref.WeakReference;

public class DocuActivity extends AppCompatActivity {

    private EditText editTextName, editTextDocument;
    private TextView textViewResponse;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docu);

        editTextName = findViewById(R.id.editTextName);
        editTextDocument = findViewById(R.id.editTextDocument);
        textViewResponse = findViewById(R.id.textViewResponse);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String document = editTextDocument.getText().toString();

                if (!name.isEmpty() && !document.isEmpty()) {
                    DocumentRequestTask task = new DocumentRequestTask(DocuActivity.this);
                    task.execute(name, document);
                }
            }
        });
    }

    private static class DocumentRequestTask extends AsyncTask<String, Void, String> {
        private WeakReference<DocuActivity> activityReference;

        DocumentRequestTask(DocuActivity activity) {
            activityReference = new WeakReference<>(activity);
        }

        @Override
        protected String doInBackground(String... params) {
            // Simulate the document request process
            // Replace this with your own logic to submit the request to the barangay

            // Sleep for 2 seconds to simulate network delay
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String name = params[0];
            String document = params[1];

            // Return a response message
            return "Dear " + name + ", your request for " + document + " has been submitted.";
        }

        @Override
        protected void onPostExecute(String result) {
            DocuActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }

            // Update the UI with the response message
            TextView textViewResponse = activity.findViewById(R.id.textViewResponse);
            textViewResponse.setText(result);
        }
    }
}