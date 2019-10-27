package com.example.csschedulemaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csschedulemaker.courseData.Course;
import com.example.csschedulemaker.courseData.CourseBag;
import com.example.csschedulemaker.courseData.Semester;

import java.io.Serializable;

public class AddCoursesIntermediateActivity extends AppCompatActivity implements Serializable {

    private CourseBag courseBag;
    private Semester currentSemesterFromMain;
    private RecyclerView myCoursesRecycler;
    private CoursesIntermeiateAdapter adapter;

    private static final int OPEN_COURSE_POPUP_RESULT = 2;

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
    }

    public void addNewCourse(View view) {
        Intent intent = new Intent(this, CourseSectionSelectionPopupActivity.class);
        intent.putExtra("currentSemesterInter", currentSemesterFromMain);
        startActivityForResult(intent, OPEN_COURSE_POPUP_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OPEN_COURSE_POPUP_RESULT) {
            if (resultCode == RESULT_OK) {
                Course result = (Course) (data.getSerializableExtra("updatedSem"));

                System.out.println("ALMOST THERE " + result);
                adapter.addMoreCourses(result);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("userSem", currentSemesterFromMain); // send the result of Activity3
                setResult(Activity.RESULT_OK, returnIntent);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("USER COURSE ACTIVITY DESTROYED");
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.submitList(currentSemesterFromMain.getSemCourses());
    }
}

