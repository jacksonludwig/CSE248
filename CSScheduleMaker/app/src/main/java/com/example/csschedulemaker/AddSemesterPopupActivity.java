package com.example.csschedulemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.csschedulemaker.courseData.SemesterTypes;

import java.util.ArrayList;
import java.util.List;

public class AddSemesterPopupActivity extends Activity {
    private static final int START_YEAR = 2019;
    private static final int END_YEAR = 2026;

    private static final int ADD_SEMESTER_REQUEST_CODE = 0;

    private Spinner seasonSpinner;
    private Spinner yearSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_semester_window);

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

        connectCancelButton();
        connectSeasonSpinner();
        connectYearSpinner();
        connectAcceptButton();
    }

    private void connectCancelButton() {
        Button close_btn = (Button) findViewById(R.id.cancel_button);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void connectAcceptButton() {
        Button accept_btn = (Button) findViewById(R.id.add_semester_btn);
        accept_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("semKey", seasonSpinner.getSelectedItem().toString()
                        + " " + yearSpinner.getSelectedItem().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void connectSeasonSpinner() {
        seasonSpinner = (Spinner) findViewById(R.id.sem_title_spinner);
        List<String> list = new ArrayList<>();
        list.add(String.valueOf(SemesterTypes.Fall));
        list.add(String.valueOf(SemesterTypes.Winter));
        list.add(String.valueOf(SemesterTypes.Spring));
        list.add(String.valueOf(SemesterTypes.Summer));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seasonSpinner.setAdapter(dataAdapter);
    }

    private void connectYearSpinner() {
        yearSpinner = (Spinner) findViewById(R.id.sem_year_spinner);
        List<Integer> list = new ArrayList<>();
        for (int i = START_YEAR; i <= END_YEAR; i++) {
            list.add(i);
        }
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(dataAdapter);
    }
}
