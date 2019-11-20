package com.example.collegeapplicationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.collegeapplicationsystem.JSONParsing.College;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainSearchMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_search);

        // Example Query
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        /*
        DocumentReference docRef = db.collection("colleges").document("100654");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                College college = documentSnapshot.toObject(College.class);
                System.out.println(college);
                Toast.makeText(MainSearchMenuActivity.this, college.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        DocumentReference docRef = db.collection("userdata").document("jacksonludwig0@gmail.com");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                System.out.println(documentSnapshot.getData());
            }
        });
        */
    }
}
