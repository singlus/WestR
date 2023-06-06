package com.example.westr;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private ListView listViewNews;
    private List<String> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        listViewNews = findViewById(R.id.listViewNews);
        newsList = new ArrayList<>();

        // Populate the news list with sample data (replace with your own data)
        newsList.add("News 1");
        newsList.add("News 2");
        newsList.add("News 3");

        // Create an ArrayAdapter to populate the ListView with the news list
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, newsList);

        // Set the adapter on the ListView
        listViewNews.setAdapter(adapter);

        // Set item click listener to show a toast with the selected news item
        listViewNews.setOnItemClickListener((parent, view, position, id) -> {
            String selectedNews = newsList.get(position);
            Toast.makeText(NewsActivity.this, selectedNews, Toast.LENGTH_SHORT).show();
        });
    }
}