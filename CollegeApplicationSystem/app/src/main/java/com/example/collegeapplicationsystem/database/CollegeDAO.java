package com.example.collegeapplicationsystem.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.collegeapplicationsystem.JSONParsing.College;

import java.util.List;

@Dao
public interface CollegeDAO {
    @Query("SELECT * FROM college")
    List<College> getAll();

    @Query("SELECT * FROM college WHERE id IN (:ids)")
    List<College> loadAllByIds(int[] ids);

    @Query("SELECT * FROM college WHERE id LIKE :id LIMIT 1")
    College findById(int id);

    @Query("SELECT * FROM college WHERE name LIKE :name")
    College findByName(String name);

    @Query("SELECT * FROM college WHERE sat_math_25 LIKE :score")
    College findBySATMath25(Float score);

    @Query("SELECT * FROM college WHERE sat_math_75 LIKE :score")
    College findBySATMath75(Float score);

    @Query("SELECT * FROM college WHERE sat_reading_25 LIKE :score")
    College findBySATReading25(Float score);

    @Query("SELECT * FROM college WHERE sat_reading_75 LIKE :score")
    College findBySATReading75(Float score);

    @Insert
    void insertAll(College... colleges);

    @Insert
    void insertList(List<College> colleges);

    @Delete
    void delete(College college);

}
