package com.example.collegeapplicationsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeapplicationsystem.JSONParsing.College;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class CollegeRealTimeNameSearchActivity extends AppCompatActivity {
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collegeRef = db.collection("colleges");
    private RecyclerView recyclerView;
    private CollegeAdapter adapter;
    private EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time_name_college_search);

        startRecycler();

        listenToSearch();
    }

    private void startRecycler() {
        Query query = collegeRef.whereEqualTo("schoolName", "");
        Toast.makeText(this, "Query Attempted...", Toast.LENGTH_SHORT).show();

        FirestoreRecyclerOptions<College> options = new FirestoreRecyclerOptions.Builder<College>()
                .setQuery(query, College.class)
                .build();

        adapter = new CollegeAdapter(options);

        recyclerView = findViewById(R.id.college_name_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new CollegeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                College college = documentSnapshot.toObject(College.class);
                Intent intent = new Intent(getApplicationContext(), ViewCollegeDetailsActivity.class);
                intent.putExtra("clickedCollege", college);
                startActivity(intent);
            }
        });
    }

    private void updateRecycler(Query query) {
        FirestoreRecyclerOptions<College> options = new FirestoreRecyclerOptions.Builder<College>()
                .setQuery(query, College.class)
                .build();

        adapter = new CollegeAdapter(options);

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new CollegeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                College college = documentSnapshot.toObject(College.class);
                Intent intent = new Intent(getApplicationContext(), ViewCollegeDetailsActivity.class);
                intent.putExtra("clickedCollege", college);
                startActivity(intent);
            }
        });
    }

    private void listenToSearch() {
        search = findViewById(R.id.college_search_edit_text);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getApplicationContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
                }
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {
                String queryString = query.toString().trim()/*.toLowerCase() put back after db update*/;
                System.out.println(queryString);
                System.out.println(getEndOfQuery(queryString));

               /* Query searchQuery = collegeRef
                        .whereGreaterThanOrEqualTo("schoolName", queryString)
                        .whereLessThan("schoolName", getEndOfQuery(queryString))
                        .orderBy("schoolName", Query.Direction.ASCENDING); */

                Query searchQuery = collegeRef
                        .whereEqualTo("id", 100654);

                updateRecycler(searchQuery);
                System.out.println(recyclerView.getAdapter().getItemCount());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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

    private String getEndOfQuery(String search) {
        if (search.length() > 0) {
            search = search.trim();
            char lastCharIterated = search.charAt(search.length() - 1);
            search = search.substring(0, search.length() - 1);
            search = search + (char) (lastCharIterated + 1);
        }
        return search;
    }
}
