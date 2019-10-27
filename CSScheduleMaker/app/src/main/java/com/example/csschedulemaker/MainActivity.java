package com.example.csschedulemaker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csschedulemaker.courseData.CourseBag;
import com.example.csschedulemaker.courseData.Semester;
import com.example.csschedulemaker.courseData.Utilities;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, Serializable {
    private static final String computerScienceHelpURL = "https://www.sunysuffolk.edu/explore-academics/majors-and-programs/COSC-AS.jsp";

    private static final long serialVersionUID = 7384564696463742131L;

    private static final int ADD_SEMESTER_REQUEST_CODE = 0;
    private static final int OPEN_SPECIFIC_SEMESTER_CODE = 1;
    private static final int STORAGE_CODE = 1000;

    private ArrayList<Semester> semesters;
    private CourseBag courseBag;
    private RecyclerView semestersRecycler;
    private SemesterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        semestersRecycler = (RecyclerView) findViewById(R.id.semester_RecyclerView);

        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(16);
        semestersRecycler.addItemDecoration(spacesItemDecoration);

        RecyclerView.ItemDecoration itemDecorationHorizLine = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        semestersRecycler.addItemDecoration(itemDecorationHorizLine);

        courseBag = Utilities.loadCourses("courseData.dat", getApplicationContext());
        System.out.println(courseBag);

        semesters = Utilities.createBaseSemesters(courseBag);

        adapter = new SemesterAdapter(semesters);

        semestersRecycler.setAdapter(adapter);
        semestersRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    public void addNewSemester(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.semester_popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addSemester:
                Intent intent = new Intent(getApplicationContext(), AddSemesterPopupActivity.class);
                startActivityForResult(intent, ADD_SEMESTER_REQUEST_CODE);
                return true;
            case R.id.helpLink:
                goToUrl(computerScienceHelpURL);
                return true;
            case R.id.exportAsPDF:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, STORAGE_CODE);
                    } else {
                        savePdf();
                    }
                } else {
                    savePdf();
                }
                return true;
            default:
                return false;
        }
    }

    private void savePdf() {
        Document mDoc = new Document();
        String fileName = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());
        String filePath = Environment.getExternalStorageDirectory() + "/" + fileName + ".pdf";

        try {
            PdfWriter.getInstance(mDoc, new FileOutputStream(filePath));
            mDoc.open();
            String mText = printSemesters();
            mDoc.addAuthor("CSSchedule App");
            mDoc.add(new Paragraph(mText));
            mDoc.close();
            Toast.makeText(this, fileName + ".pdf exported to " + filePath, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case STORAGE_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    savePdf();
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void goToUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    // This method is called when the second activity finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == ADD_SEMESTER_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Get String data from Intent
                String returnString = data.getStringExtra("semKey");
                Semester newSemester = new Semester(returnString, courseBag);
                if (!searchListByName(newSemester)) {
                    adapter.addMoreSemesters(newSemester);
                    Toast addedToast = Toast.makeText(this, "New semester added", Toast.LENGTH_SHORT);
                    addedToast.show();
                } else {
                    Toast repeatToast = Toast.makeText(this, "Please only add new semesters", Toast.LENGTH_SHORT);
                    repeatToast.show();
                }
            }
        } else if (requestCode == OPEN_SPECIFIC_SEMESTER_CODE) {
            if (resultCode == RESULT_OK) {
                System.out.println("MADE IT BACK AROUND");
                Semester result = (Semester) (data.getSerializableExtra("userSem"));
                int index = searchListBySemester(result);
                semesters.remove(index);
                semesters.add(index, result);
                adapter.submitList(semesters);
            }
        }
    }

    private boolean searchListByName(Semester newSemester) {
        for (Semester semester : semesters) {
            if (newSemester.getSemesterName().equalsIgnoreCase(semester.getSemesterName())) {
                return true;
            }
        }
        return false;
    }

    private int searchListBySemester(Semester newSemester) {
        for (int i = 0; i < semesters.size(); i++) {
            if (newSemester.getSemesterName().equalsIgnoreCase(semesters.get(i).getSemesterName())) {
                return i;
            }
        }
        return -1;
    }

    private String printSemesters() {
        String returnString = "";
        for (int i = 0; i < semesters.size(); i++) {
            Semester semester = semesters.get(i);
            returnString = returnString + semester + "\n";
        }
        return returnString;
    }

}
