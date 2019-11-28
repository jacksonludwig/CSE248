package com.example.collegeapplicationsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainSearchMenuActivity extends AppCompatActivity {
    private static final int STATE_REQUEST_CODE = 50;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private String searchText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_search);
    }

    public void openAccountView(View view) {
        Intent intent = new Intent(getApplicationContext(), ViewAccountActivity.class);
        startActivity(intent);
    }

    public void openNameSearchInput(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
        builder.setTitle("College Name Search");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("SEARCH", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                searchText = input.getText().toString();
                Intent intent = new Intent(getApplicationContext(), CollegeSearchActivity.class);
                intent.putExtra("queryType", "name");
                intent.putExtra("search", searchText);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void openNameIDSearchInput(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
        builder.setTitle("College IPEDS ID Search");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("SEARCH", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                searchText = input.getText().toString();
                Intent intent = new Intent(getApplicationContext(), CollegeSearchActivity.class);
                intent.putExtra("queryType", "id");
                intent.putExtra("search", searchText);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void openNameStateSearchInput(View view) {
        Intent intent = new Intent(getApplicationContext(), SearchByStatePopupActivity.class);
        startActivityForResult(intent, STATE_REQUEST_CODE);
    }

    public void openFavoritesView(View view) {
        Intent intent = new Intent(getApplicationContext(), CollegeSearchActivity.class);
        intent.putExtra("queryType", "favorites");
        intent.putExtra("search", "placeholder");
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == STATE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Intent intent = new Intent(getApplicationContext(), CollegeSearchActivity.class);
                intent.putExtra("queryType", "state");
                intent.putExtra("search", data.getStringExtra("chosenState"));
                startActivity(intent);
            } else {
                // state search canceled
            }
        }
    }
}
