package com.example.collegeapplicationsystem;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.collegeapplicationsystem.JSONParsing.College;
import com.example.collegeapplicationsystem.JSONParsing.Holder;
import com.example.collegeapplicationsystem.JSONParsing.JSONRetriever;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateDBPopupActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private boolean wasUpdated = false;

    private Thread updateThread;
    private int updatedCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_db_pop_up_activity);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * .4));

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.x = 0;
        layoutParams.y = 20;

        getWindow().setAttributes(layoutParams);

        getDataFromGovAPI();
    }

    private void getDataFromGovAPI() {
        updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                JSONRetriever jsonRetriever = new JSONRetriever();

                Holder holder = null;
                try {
                    holder = jsonRetriever.mapAllPagesToObjects();
                } catch (Exception e) {
                    System.out.println("Database update interrupted.");
                }
                if (holder != null) {
                    System.out.println(holder.getColleges().size());
                    for (College college : holder.getColleges()) {
                        db.collection("colleges")
                                .document(String.valueOf(college.getId()))
                                .set(college)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        System.out.println("Colleges Updated: " + ++updatedCount);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        finish();
                                    }
                                });
                    }
                    wasUpdated = true;
                    finish();
                }
            }
        });
        updateThread.setPriority(Thread.MAX_PRIORITY);
        updateThread.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (wasUpdated) {
            Toast.makeText(UpdateDBPopupActivity.this, "Database updated!"
                    , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(UpdateDBPopupActivity.this, "Database update failed"
                    , Toast.LENGTH_SHORT).show();
        }
        updateThread.interrupt();
        updateThread = null;
        stopService(getIntent());
    }
}
