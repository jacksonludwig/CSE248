package com.example.collegeapplicationsystem.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.collegeapplicationsystem.JSONParsing.College;

@Database(entities = {College.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CollegeDAO collegeDAO();
}
