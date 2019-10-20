package com.example.csschedulemaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.csschedulemaker.courseData.CourseBag;
import com.example.csschedulemaker.courseData.Semester;
import com.example.csschedulemaker.courseData.Utilities;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private static final String relativeFilePath = System.getProperty("user.dir") + "\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\courseData.dat";

    private static final int ADD_SEMESTER_REQUEST_CODE = 0;

    private ArrayList<Semester> semesters;
    private CourseBag courseBag;
    private RecyclerView semestersRecycler;
    private SemesterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        semestersRecycler = (RecyclerView) findViewById(R.id.semester_RecyclerView);

        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(16);
        semestersRecycler.addItemDecoration(spacesItemDecoration);

        RecyclerView.ItemDecoration itemDecorationHorizLine = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        semestersRecycler.addItemDecoration(itemDecorationHorizLine);


        courseBag = Utilities.loadCourses(relativeFilePath);

        semesters = Utilities.createBaseSemesters(courseBag);

        adapter = new SemesterAdapter(semesters);

        semestersRecycler.setAdapter(adapter);
        semestersRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    public void addNewSemester(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.semester_popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addSemester:
                Intent intent = new Intent(getApplicationContext(), AddSemesterPopupActivity.class);
                startActivityForResult(intent, ADD_SEMESTER_REQUEST_CODE);
                return true;
            default:
                return false;
        }
    }

    // This method is called when the second activity finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == ADD_SEMESTER_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Get String data from Intent
                String returnString = data.getStringExtra("semKey");
                Semester newSemester = new Semester(returnString, courseBag);
                if(!searchListByName(newSemester)) {
                    adapter.addMoreSemesters(newSemester);
                    Toast addedToast = Toast.makeText(this, "New semester added", Toast.LENGTH_SHORT);
                    addedToast.show();
                } else {
                    Toast repeatToast = Toast.makeText(this, "Please only add new semesters", Toast.LENGTH_SHORT);
                    repeatToast.show();
                }
            }
        }
    }

    private boolean searchListByName(Semester newSemester) {
        for(Semester semester : semesters) {
            if(newSemester.getSemesterName().equalsIgnoreCase(semester.getSemesterName())) {
                return true;
            }
        }
        return false;
    }
}
