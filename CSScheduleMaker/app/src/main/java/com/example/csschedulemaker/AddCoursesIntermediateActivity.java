package com.example.csschedulemaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.csschedulemaker.courseData.Course;
import com.example.csschedulemaker.courseData.CourseBag;
import com.example.csschedulemaker.courseData.Semester;

import java.util.ArrayList;
import java.util.List;


public class AddCoursesIntermediateActivity extends AppCompatActivity {

    // not yet sure if this will be used here
    private static final int ADD_CLASS_REQUEST_CODE = 1;

    private CourseBag courseBag;
    private Semester currentSemesterFromMain;
    private List<Course> hashToCourseList;
    private RecyclerView myCoursesRecycler;
    private CoursesIntermeiateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_courses_intermediate);

        currentSemesterFromMain = MainActivity.getCurrentSemester();

        hashToCourseList = new ArrayList<>();
        hashToCourseList.addAll(currentSemesterFromMain.getSemCourses().values());

        
    }
}
