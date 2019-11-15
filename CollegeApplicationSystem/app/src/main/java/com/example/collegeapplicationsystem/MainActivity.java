package com.example.collegeapplicationsystem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.collegeapplicationsystem.JSONParsing.Utilities;
import com.example.collegeapplicationsystem.database.AppDatabase;
import com.example.collegeapplicationsystem.database.College;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "United States Colleges").build();
       // AppDatabase db = AppDatabase.getInstance(getApplicationContext());

    }
}
