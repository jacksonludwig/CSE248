package com.example.csschedulemaker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csschedulemaker.courseData.Semester;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SemesterAdapter extends ListAdapter<Semester, SemesterAdapter.ViewHolder> {

    private List<Semester> mySemesters;
    private static final int OPEN_SPECIFIC_SEMESTER_CODE = 1;

    private TextView semTextView;
    private TextView classNumTextView;
    private Button adjButton;
    private Button delButton;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView semesterTextView;
        public TextView classNumTextView;
        public Button adjustButton;
        public Button deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);

            semesterTextView = (TextView) itemView.findViewById(R.id.semester_name);
            classNumTextView = (TextView) itemView.findViewById(R.id.class_amount);
            adjustButton = (Button) itemView.findViewById(R.id.adjust_button);
            deleteButton = (Button) itemView.findViewById(R.id.delete_button);
        }
    }

    // checks if the thing being added to the list is unique
    public static final DiffUtil.ItemCallback<Semester> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Semester>() {
                @Override
                public boolean areItemsTheSame(@NonNull Semester oldItem, @NonNull Semester newItem) {
                    // compares name of semester
                    return oldItem.getSemesterName().equalsIgnoreCase(newItem.getSemesterName());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Semester oldItem, @NonNull Semester newItem) {
                    // compares hashmap of courses in semester
                    return oldItem.getSemCourses().equals(newItem.getSemCourses());
                }
            };

    public SemesterAdapter(List<Semester> semesters) {
        super(DIFF_CALLBACK);
        mySemesters = semesters;
        submitList(mySemesters);
    }

    @Override
    public void submitList(final List<Semester> list) {
        Collections.sort(mySemesters, Semester.semSeasonComparator);
        Collections.sort(mySemesters, Semester.semYearComparator);
        super.submitList(list != null ? new ArrayList<>(list) : null);
    }

    public void addMoreSemesters(List<Semester> newSemesters) {
        mySemesters.addAll(newSemesters);
        submitList(mySemesters);
    }

    public void addMoreSemesters(Semester semester) {
        mySemesters.add(semester);
        submitList(mySemesters);
    }

    @Override
    public SemesterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.row_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SemesterAdapter.ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        final Semester semester = getItem(position);

        // Set item views based on your views and data model
        semTextView = viewHolder.semesterTextView;
        semTextView.setText(semester.getSemesterName());
        classNumTextView = viewHolder.classNumTextView;
        classNumTextView.setText(String.valueOf(semester.getNumClasses()) + " classes");
        adjButton = viewHolder.adjustButton;
        delButton = viewHolder.deleteButton;

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setCancelable(true);
                builder.setTitle("Delete Semester");
                builder.setMessage("Are you sure you want to remove this semester?");
                builder.setPositiveButton("Yes, delete",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mySemesters.remove(semester);
                                submitList(mySemesters);
                                showDeleteToast(builder.getContext());
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do not delete
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        adjButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AddCoursesIntermediateActivity.class);
                intent.putExtra("originalClassList", mySemesters.get(position));
                ((Activity) context).startActivityForResult(intent, OPEN_SPECIFIC_SEMESTER_CODE);

            }
        });
    }

    public static void showDeleteToast(Context context) {
        Toast.makeText(context, "Semester deleted", Toast.LENGTH_SHORT).show();
    }

}
