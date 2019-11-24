package com.example.collegeapplicationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class ViewAccountActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private static final String TAG = "USER_QUERY";
    private static final int EDIT_FIRST_NAME_REQUEST_CODE = 10;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private TextView emailTextView;
    private TextView firstNameTextView;
    private TextView lastNameTextView;
    private TextView mathScoreTextView;
    private TextView readingScoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);

        setAccountView();
    }

    private void setAccountView() {
        setEmailView();
        setNameAndScoreView();
    }

    private void setEmailView() {
        emailTextView = findViewById(R.id.email_textview);
        emailTextView.setText(user.getEmail());
    }

    private void setNameAndScoreView() {
        firstNameTextView = findViewById(R.id.firstname_textview);
        lastNameTextView = findViewById(R.id.lastname_textview);
        mathScoreTextView = findViewById(R.id.math_textview);
        readingScoreTextView = findViewById(R.id.reading_textview);
        db.collection("userdata")
                .document(user.getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                firstNameTextView.setText(document.get("firstName").toString());
                                lastNameTextView.setText(document.get("lastName").toString());
                                mathScoreTextView.setText(document.get("math").toString());
                                readingScoreTextView.setText(document.get("reading").toString());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
    }

    private void updateFirstName(final String name) {
        db.collection("userdata")
                .whereEqualTo("email", user.getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && !task.getResult().isEmpty()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            Map<String, Object> userdata = new HashMap<>();
                            userdata.put("firstName", name);

                            db.collection("userdata").document(user.getEmail())
                                    .update(userdata)
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w("User data entry", "Error writing document", e);
                                        }
                                    });
                            firstNameTextView = findViewById(R.id.firstname_textview);
                            firstNameTextView.setText(name);
                        }
                    }
                });

    }


    public void editAccount(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.edit_account_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.editFirst:
                Intent intent = new Intent(getApplicationContext(), EditFirstNamePopupActivity.class);
                startActivityForResult(intent, EDIT_FIRST_NAME_REQUEST_CODE);
                return true;
            case R.id.editLast:

                return true;
            case R.id.editMath:

                return true;
            case R.id.editReading:

                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_FIRST_NAME_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                updateFirstName(data.getStringExtra("firstName"));
            } else {

            }
        }
    }
}
