package com.example.csschedulemaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;

import com.example.csschedulemaker.courseData.CourseTypes;
import com.example.csschedulemaker.courseData.Semester;

import java.util.ArrayList;

public class CourseSectionSelectionPopupActivity extends AppCompatActivity {

    private Semester semesterFromIntermediate;
    private RecyclerView courseTypeRecycler;
    private CourseTypeAdapter adapter;
    private ArrayList<CourseTypes> courseTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_section_selection_popup);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * .8));

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.x = 0;
        layoutParams.y = 20;

        getWindow().setAttributes(layoutParams);

        semesterFromIntermediate = (Semester) getIntent().getSerializableExtra("currentSemesterInter");
        System.out.println("LINKED CORRECTLY");

        courseTypeRecycler = (RecyclerView) findViewById(R.id.recyclerView);

        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(16);
        courseTypeRecycler.addItemDecoration(spacesItemDecoration);

        RecyclerView.ItemDecoration itemDecorationHorizLine = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        courseTypeRecycler.addItemDecoration(itemDecorationHorizLine);

        courseTypes = new ArrayList<>();
        populateCourseList();

        adapter = new CourseTypeAdapter(courseTypes, semesterFromIntermediate);

        courseTypeRecycler.setAdapter(adapter);
        courseTypeRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private void populateCourseList() {
        courseTypes.add(CourseTypes.COMPSCI);
        courseTypes.add(CourseTypes.ENGLISH);
        courseTypes.add(CourseTypes.HISTORY);
        courseTypes.add(CourseTypes.HUMANITY);
        courseTypes.add(CourseTypes.MATH);
        courseTypes.add(CourseTypes.GYM);
        courseTypes.add(CourseTypes.SCIENCE);
        courseTypes.add(CourseTypes.LANGUAGE);
        courseTypes.add(CourseTypes.SOCIOLOGY);
    }
}
