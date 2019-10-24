package com.example.csschedulemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;

import com.example.csschedulemaker.courseData.Semester;

public class CourseSectionSelectionPopupActivity extends AppCompatActivity {

    private Semester semesterFromIntermediate;

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


    }
}
