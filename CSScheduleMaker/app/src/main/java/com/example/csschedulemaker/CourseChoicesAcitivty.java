package com.example.csschedulemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CourseChoicesAcitivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_choices_acitivty);

        System.out.println(getIntent().getSerializableExtra("chosenCourseType") + "\t"
                + getIntent().getSerializableExtra("originalClassListFromTypePopup"));

    }
}
