package com.example.csschedulemaker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csschedulemaker.courseData.CourseTypes;
import com.example.csschedulemaker.courseData.Semester;

import java.util.ArrayList;
import java.util.List;

public class CourseTypeAdapter extends ListAdapter<CourseTypes, CourseTypeAdapter.ViewHolder> {

    private List<CourseTypes> myCourseTypes;
    private Semester semesterFromIntermediate;
    private static final int OPEN_COURSE_TYPE_POPUP_RESULT = 3;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView courseTypeTextView;
        public Button goButton;

        public ViewHolder(View itemView) {
            super(itemView);

            courseTypeTextView = (TextView) itemView.findViewById(R.id.course_type);
            goButton = (Button) itemView.findViewById(R.id.go_button);
        }
    }

    // checks if the thing being added to the list is unique
    public static final DiffUtil.ItemCallback<CourseTypes> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<CourseTypes>() {
                @Override
                public boolean areItemsTheSame(@NonNull CourseTypes oldItem, @NonNull CourseTypes newItem) {
                    // compares name of courseType
                    return oldItem.displayName().equalsIgnoreCase(newItem.displayName());
                }

                @Override
                public boolean areContentsTheSame(@NonNull CourseTypes oldItem, @NonNull CourseTypes newItem) {
                    // compares hashmap of courses in courseType
                    return oldItem.toString().equals(newItem.toString());
                }
            };

    public CourseTypeAdapter(List<CourseTypes> courseTypes, Semester semesterFromIntermediate) {
        super(DIFF_CALLBACK);
        this.semesterFromIntermediate = semesterFromIntermediate;
        myCourseTypes = courseTypes;
        submitList(myCourseTypes);
    }

    @Override
    public void submitList(final List<CourseTypes> list) {
        super.submitList(list != null ? new ArrayList<>(list) : null);
    }

    public void addMoreCourseTypes(List<CourseTypes> newCourseTypes) {
        myCourseTypes.addAll(newCourseTypes);
        submitList(myCourseTypes);
    }

    public void addMoreCourseTypes(CourseTypes courseType) {
        myCourseTypes.add(courseType);
        submitList(myCourseTypes);
    }

    @Override
    public CourseTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.row_layout_course_types, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CourseTypeAdapter.ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        final CourseTypes courseType = getItem(position);

        // Set item views based on your views and data model
        TextView semTextView = viewHolder.courseTypeTextView;
        semTextView.setText(courseType.displayName());
        Button goButton = viewHolder.goButton;


        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, CourseChoicesActivity.class);
                intent.putExtra("chosenCourseType", myCourseTypes.get(position));
                intent.putExtra("originalClassListFromTypePopup", semesterFromIntermediate);
                ((Activity) context).startActivityForResult(intent, OPEN_COURSE_TYPE_POPUP_RESULT);
                //((Activity)context).finish();
            }
        });

    }

}
