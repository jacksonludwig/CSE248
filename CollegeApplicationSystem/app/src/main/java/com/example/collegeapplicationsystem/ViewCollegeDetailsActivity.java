package com.example.collegeapplicationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.collegeapplicationsystem.JSONParsing.College;

public class ViewCollegeDetailsActivity extends AppCompatActivity {

    College clickedCollege;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_college_details);

        getCollegeFromIntent();
        System.out.println(clickedCollege);
    }

    private void getCollegeFromIntent() {
        clickedCollege = (College) getIntent().getSerializableExtra("clickedCollege");
    }
}
