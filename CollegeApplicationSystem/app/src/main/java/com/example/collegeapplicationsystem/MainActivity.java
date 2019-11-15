package com.example.collegeapplicationsystem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.collegeapplicationsystem.JSONParsing.College;
import com.example.collegeapplicationsystem.JSONParsing.Holder;
import com.example.collegeapplicationsystem.JSONParsing.JSONRetriever;
import com.example.collegeapplicationsystem.JSONParsing.Utilities;
import com.example.collegeapplicationsystem.database.AppDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "United States Colleges").build();
       // System.out.println(getDatabasePath("United States Colleges").getAbsolutePath());
       // List<College> colleges = Utilities.loadColleges("colleges.dat", getApplicationContext());
       // db.collegeDAO().insertList(colleges);
    }
}
