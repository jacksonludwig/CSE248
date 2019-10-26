package com.example.csschedulemaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.csschedulemaker.courseData.Course;
import com.example.csschedulemaker.courseData.CourseBag;
import com.example.csschedulemaker.courseData.Semester;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AddCoursesIntermediateActivity extends AppCompatActivity implements Serializable {

    private CourseBag courseBag;
    private Semester currentSemesterFromMain;
    private RecyclerView myCoursesRecycler;
    private CoursesIntermeiateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_courses_intermediate);

        currentSemesterFromMain = (Semester) getIntent().getSerializableExtra("originalClassList");

        myCoursesRecycler = (RecyclerView) findViewById(R.id.current_courses_recycler);

        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(16);
        myCoursesRecycler.addItemDecoration(spacesItemDecoration);
        RecyclerView.ItemDecoration itemDecorationHorizLine = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        myCoursesRecycler.addItemDecoration(itemDecorationHorizLine);

        courseBag = currentSemesterFromMain.getCourseBag();

        adapter = new CoursesIntermeiateAdapter(currentSemesterFromMain.getSemCourses());

        myCoursesRecycler.setAdapter(adapter);
        myCoursesRecycler.setLayoutManager(new LinearLayoutManager(this));

      //  adapter.addMoreCourses(courseBag.get("CSE110"));
    }

    public void addNewCourse(View view) {
        Intent intent = new Intent(this, CourseSectionSelectionPopupActivity.class);
        intent.putExtra("currentSemesterInter", currentSemesterFromMain);
        startActivity(intent);
    }


}
