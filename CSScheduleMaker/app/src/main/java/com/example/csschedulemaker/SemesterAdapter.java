package com.example.csschedulemaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csschedulemaker.courseData.Semester;

import java.util.ArrayList;
import java.util.List;

public class SemesterAdapter extends ListAdapter<Semester, SemesterAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView semesterTextView;
        public Button messageButton;
        public Button deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);

            semesterTextView = (TextView) itemView.findViewById(R.id.semester_name);
            messageButton = (Button) itemView.findViewById(R.id.message_button);
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
                    //  return false;
                }
            };

    private List<Semester> mySemesters;

    public SemesterAdapter() {
        super(DIFF_CALLBACK);
        mySemesters = new ArrayList<>();
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
        Semester semester = getItem(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.semesterTextView;
        textView.setText(semester.getSemesterName());
        Button button = viewHolder.messageButton;
        button.setText("Adjust");
        Button delButton = viewHolder.deleteButton;
        delButton.setText("Delete");
    }

    /* ListAdapter manages the item count
    @Override
    public int getItemCount() {
        return mySemesters.size();
    }
    */

}
