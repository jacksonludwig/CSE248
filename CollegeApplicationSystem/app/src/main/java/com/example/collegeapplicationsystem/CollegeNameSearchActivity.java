package com.example.collegeapplicationsystem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeapplicationsystem.JSONParsing.College;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class CollegeNameSearchActivity extends AppCompatActivity {

    private String searchText;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collegeRef = db.collection("colleges");

    private CollegeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_name_search);

        setSearchQuery();

        startRecycler();
    }

    private void startRecycler() {
        Query query = collegeRef
                .whereGreaterThanOrEqualTo("schoolName", searchText)
                .whereLessThan("schoolName", getEndOfQuery(searchText))
                .orderBy("schoolName", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<College> options = new FirestoreRecyclerOptions.Builder<College>()
                .setQuery(query, College.class)
                .build();

        adapter = new CollegeAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.college_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private void setSearchQuery() {
        searchText = getIntent().getStringExtra("nameSearch");
    }

    private String getEndOfQuery(String search) {
        search = search.trim();
        char lastCharIterated = search.charAt(search.length() - 1);
        search = search.substring(0, search.length() - 1);
        search = search + (char) (lastCharIterated + 1);

        return search;
    }
}
