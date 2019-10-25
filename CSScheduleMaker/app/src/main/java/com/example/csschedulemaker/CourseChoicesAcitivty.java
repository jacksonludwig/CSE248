package com.example.csschedulemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.csschedulemaker.courseData.CourseTypes;
import com.example.csschedulemaker.courseData.Semester;

public class CourseChoicesAcitivty extends AppCompatActivity {

    CourseTypes selectedType;
    TextView titleTextView;
    Semester semesterFromPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_choices_acitivty);

        setTitle();
        setSemester();

    }

    private void setTitle() {
        CourseTypes selectedType = (CourseTypes) (getIntent().getSerializableExtra("chosenCourseType"));
        TextView titleTextView = (TextView) findViewById(R.id.course_select_title);
        titleTextView.setText(selectedType.displayName());
    }

    private void setSemester() {
        semesterFromPopup = (Semester) (getIntent().getSerializableExtra("originalClassListFromTypePopup"));
    }
}
