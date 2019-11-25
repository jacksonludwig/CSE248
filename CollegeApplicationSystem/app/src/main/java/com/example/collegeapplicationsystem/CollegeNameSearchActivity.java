package com.example.collegeapplicationsystem;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeapplicationsystem.JSONParsing.College;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
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

        adapter.setOnItemClickListener(new CollegeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                College college = documentSnapshot.toObject(College.class);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
        Toast.makeText(this, "Query Attempted...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private void setSearchQuery() {
        searchText = getIntent().getStringExtra("nameSearch");
        if (searchText != null) {
            searchText = searchText.trim();
        }
        if(searchText.equals(" ") || searchText.equals("")) {
            searchText = "text not searchable";
        }
    }

    private String getEndOfQuery(String search) {
        search = search.trim();
        char lastCharIterated = search.charAt(search.length() - 1);
        search = search.substring(0, search.length() - 1);
        search = search + (char) (lastCharIterated + 1);

        return search;
    }
}
