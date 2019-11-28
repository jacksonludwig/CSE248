package com.example.collegeapplicationsystem;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.collegeapplicationsystem.JSONParsing.College;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ViewCollegeDetailsActivity extends AppCompatActivity {
    private static final String TAG = "COMPARISON";

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private College clickedCollege;
    private TextView collegeID;
    private TextView collegeName;
    private TextView collegeReadingScore25;
    private TextView collegeReadingScore75;
    private TextView collegeMathScore25;
    private TextView collegeMathScore75;

    private TextView userReadingScore;
    private TextView userMathScore;
    private TextView userAcceptanceChance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_college_details);

        getCollegeFromIntent();

        setCollegeViews();
        setUserScoreView();
    }

    private void getCollegeFromIntent() {
        clickedCollege = (College) getIntent().getSerializableExtra("clickedCollege");
        if (clickedCollege.getLatestAdmissionsSatScores25thPercentileCriticalReading() == null) {
            clickedCollege.setLatestAdmissionsSatScores25thPercentileCriticalReading(Float.valueOf(0));
        }
        if (clickedCollege.getLatestAdmissionsSatScores75thPercentileCriticalReading() == null) {
            clickedCollege.setLatestAdmissionsSatScores75thPercentileCriticalReading(Float.valueOf(0));
        }
        if (clickedCollege.getLatestAdmissionsSatScores25thPercentileMath() == null) {
            clickedCollege.setLatestAdmissionsSatScores25thPercentileMath(Float.valueOf(0));
        }
        if (clickedCollege.getLatestAdmissionsSatScores75thPercentileMath() == null) {
            clickedCollege.setLatestAdmissionsSatScores75thPercentileMath(Float.valueOf(0));
        }
    }

    private void setCollegeViews() {
        collegeID = findViewById(R.id.college_id_details);
        collegeID.setText(String.valueOf(clickedCollege.getId()));
        collegeName = findViewById(R.id.college_name);
        collegeName.setText(clickedCollege.getSchoolName());
        collegeReadingScore25 = findViewById(R.id.college_reading25);
        collegeReadingScore25.setText(String.valueOf(Math.round(clickedCollege.getLatestAdmissionsSatScores25thPercentileCriticalReading())));
        collegeReadingScore75 = findViewById(R.id.college_reading75);
        collegeReadingScore75.setText(String.valueOf(Math.round(clickedCollege.getLatestAdmissionsSatScores75thPercentileCriticalReading())));
        collegeMathScore25 = findViewById(R.id.college_math25);
        collegeMathScore25.setText(String.valueOf(Math.round(clickedCollege.getLatestAdmissionsSatScores25thPercentileMath())));
        collegeMathScore75 = findViewById(R.id.college_math75);
        collegeMathScore75.setText(String.valueOf(Math.round(clickedCollege.getLatestAdmissionsSatScores75thPercentileMath())));
    }

    private void setUserScoreView() {
        userReadingScore = findViewById(R.id.user_reading_score);
        userMathScore = findViewById(R.id.user_math_score);
        db.collection("userdata")
                .document(user.getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                userMathScore.setText(document.get("math").toString());
                                userReadingScore.setText(document.get("reading").toString());
                                setUserAcceptanceChanceView();
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
    }

    private void setUserAcceptanceChanceView() {
        userAcceptanceChance = findViewById(R.id.status_chance);
        int collegeCombinedScore25 = Math.round(Float.parseFloat(collegeMathScore25.getText().toString())) + Math.round(Float.parseFloat(collegeReadingScore25.getText().toString()));
        int collegeCombinedScore75 = Math.round(Float.parseFloat(collegeMathScore75.getText().toString())) + Math.round(Float.parseFloat(collegeReadingScore75.getText().toString()));
        int userCombinedScore = Math.round(Float.parseFloat(userMathScore.getText().toString())) + Math.round(Float.parseFloat(userReadingScore.getText().toString()));

        if (collegeCombinedScore25 != 0 && collegeCombinedScore75 != 0) {
            if (userCombinedScore > collegeCombinedScore75) {
                userAcceptanceChance.setText("Likely");
                userAcceptanceChance.setTextColor(Color.GREEN);
            } else if (userCombinedScore < collegeCombinedScore75 && userCombinedScore > collegeCombinedScore25) {
                userAcceptanceChance.setText("Somewhat Likely");
                userAcceptanceChance.setTextColor(Color.YELLOW);
            } else {
                userAcceptanceChance.setText("Unlikely");
                userAcceptanceChance.setTextColor(Color.RED);
            }
        } else {
            userAcceptanceChance.setText("UNKNOWN");
            userAcceptanceChance.setTextColor(Color.GRAY);
        }
    }
}
