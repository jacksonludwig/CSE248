package com.example.csschedulemaker;

import android.content.Context;
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
    public void onBindViewHolder(SemesterAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        final Semester semester = getItem(position);

        // Set item views based on your views and data model
        TextView semTextView = viewHolder.semesterTextView;
        semTextView.setText(semester.getSemesterName());
        TextView classNumTextView = viewHolder.classNumTextView;
        classNumTextView.setText(String.valueOf(semester.getNumClasses()) + " classes");
        Button adjButton = viewHolder.adjustButton;
        Button delButton = viewHolder.deleteButton;

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mySemesters.remove(semester);
                submitList(mySemesters);
                System.out.println(mySemesters);
                showDeleteToast(view.getContext());
            }
        });


        adjButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public static void showDeleteToast(Context context) {
        Toast.makeText(context, "Semester deleted", Toast.LENGTH_SHORT).show();
    }

}
