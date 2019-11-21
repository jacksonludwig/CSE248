package com.example.collegeapplicationsystem;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.collegeapplicationsystem.JSONParsing.College;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainSearchMenuActivity extends AppCompatActivity {

    ArrayList<College> collegeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_search);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("colleges")
                .whereGreaterThanOrEqualTo("schoolName", "Stony")
                .whereLessThan("schoolName", "Stonz")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Query", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d("Query", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

}
