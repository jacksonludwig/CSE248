package com.example.csschedulemaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.csschedulemaker.courseData.CourseBag;
import com.example.csschedulemaker.courseData.Semester;
import com.example.csschedulemaker.courseData.Utilities;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String relativeFilePath = System.getProperty("user.dir") + "\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\courseData.dat";
    ArrayList<Semester> semesters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView semestersRecycler = (RecyclerView) findViewById(R.id.semester_RecyclerView);

        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(16);
        semestersRecycler.addItemDecoration(spacesItemDecoration);

        RecyclerView.ItemDecoration itemDecorationHorizLine = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        semestersRecycler.addItemDecoration(itemDecorationHorizLine);


        CourseBag courseBag = Utilities.loadCourses(relativeFilePath);

        semesters = Utilities.createBaseSemesters(courseBag);

        SemesterAdapter adapter = new SemesterAdapter();
        adapter.addMoreSemesters(semesters);

        semestersRecycler.setAdapter(adapter);
        semestersRecycler.setLayoutManager(new LinearLayoutManager(this));

    }


}
