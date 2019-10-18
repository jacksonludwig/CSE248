package com.example.csschedulemaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csschedulemaker.courseData.Semester;

import java.util.List;

public class SemesterAdapter extends RecyclerView.Adapter<SemesterAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView semesterTextView;
        public Button messageButton;

        public ViewHolder(View itemView) {
            super(itemView);

            semesterTextView = (TextView) itemView.findViewById(R.id.semester_name);
            messageButton = (Button) itemView.findViewById(R.id.message_button);
        }
    }

    private List<Semester> mySemesters;

    public SemesterAdapter(List<Semester> mySemesters) {
        this.mySemesters = mySemesters;
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
        Semester semester = mySemesters.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.semesterTextView;
        textView.setText(semester.getSemesterName());
        Button button = viewHolder.messageButton;
        button.setText("Adjust");
    }

    @Override
    public int getItemCount() {
        return mySemesters.size();
    }


}
