package com.example.csschedulemaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import android.net.Uri;

import com.example.csschedulemaker.courseData.CourseBag;
import com.example.csschedulemaker.courseData.Semester;
import com.example.csschedulemaker.courseData.Utilities;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, Serializable {
    private static final String computerScienceHelpURL = "https://www.sunysuffolk.edu/explore-academics/majors-and-programs/COSC-AS.jsp";

    private static final long serialVersionUID = 7384564696463742131L;

    private static final int ADD_SEMESTER_REQUEST_CODE = 0;
    private static final int OPEN_SPECIFIC_SEMESTER_CODE = 1;

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

        courseBag = Utilities.loadCourses("courseData.dat", getApplicationContext());
        System.out.println(courseBag);

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
            case R.id.helpLink:
                goToUrl(computerScienceHelpURL);
            default:
                return false;
        }
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
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
                if (!searchListByName(newSemester)) {
                    adapter.addMoreSemesters(newSemester);
                    Toast addedToast = Toast.makeText(this, "New semester added", Toast.LENGTH_SHORT);
                    addedToast.show();
                } else {
                    Toast repeatToast = Toast.makeText(this, "Please only add new semesters", Toast.LENGTH_SHORT);
                    repeatToast.show();
                }
            }
        } else if (requestCode == OPEN_SPECIFIC_SEMESTER_CODE) {
            if (resultCode == RESULT_OK) {
                System.out.println("MADE IT BACK AROUND");
                Semester result = (Semester) (data.getSerializableExtra("userSem"));
                int index = searchListBySemester(result);
                semesters.remove(index);
                semesters.add(index, result);
                adapter.submitList(semesters);
            }
        }
    }

    private boolean searchListByName(Semester newSemester) {
        for (Semester semester : semesters) {
            if (newSemester.getSemesterName().equalsIgnoreCase(semester.getSemesterName())) {
                return true;
            }
        }
        return false;
    }

    private int searchListBySemester(Semester newSemester) {
        for (int i = 0; i < semesters.size(); i++) {
            if (newSemester.getSemesterName().equalsIgnoreCase(semesters.get(i).getSemesterName())) {
                return i;
            }
        }
        return -1;
    }

}
