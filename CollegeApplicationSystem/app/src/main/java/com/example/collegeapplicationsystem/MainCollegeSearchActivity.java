package com.example.collegeapplicationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.collegeapplicationsystem.JSONParsing.College;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainCollegeSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_college_search);

        // Example Query
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("colleges").document("100654");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                College college = documentSnapshot.toObject(College.class);
                System.out.println(college);
            }
        });

    }
}
