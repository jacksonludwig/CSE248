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

import com.example.csschedulemaker.courseData.Course;
import com.example.csschedulemaker.courseData.Semester;

import java.util.ArrayList;
import java.util.List;

public class CourseChoiceAdapter extends ListAdapter<Course, CourseChoiceAdapter.ViewHolder> {

    private List<Course> myCourses;
    private Semester currentSemester;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView courseTextView;
        public TextView courseNumTextView;
        public Button chooseButton;
        public Button infoButton;

        public ViewHolder(View itemView) {
            super(itemView);

            courseTextView = (TextView) itemView.findViewById(R.id.course_name);
            courseNumTextView = (TextView) itemView.findViewById(R.id.course_number);
            chooseButton = (Button) itemView.findViewById(R.id.choose_button);
            infoButton = (Button) itemView.findViewById(R.id.info_button);
        }
    }

    // checks if the thing being added to the list is unique
    public static final DiffUtil.ItemCallback<Course> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Course>() {
                @Override
                public boolean areItemsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
                    // compares name of course
                    return oldItem.getCourseTitleShort().equalsIgnoreCase(newItem.getCourseTitleShort());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
                    // compares hashmap of courses in course
                    return oldItem.getCourseTitleLong().equals(newItem.getCourseTitleLong());
                }
            };

    public CourseChoiceAdapter(List<Course> courses, Semester currentSemester) {
        super(DIFF_CALLBACK);
        this.currentSemester = currentSemester;
        myCourses = courses;
        submitList(myCourses);
    }

    @Override
    public void submitList(final List<Course> list) {
        /*
        Collections.sort(myCourses, Course.semSeasonComparator);
        Collections.sort(myCourses, Course.semYearComparator);
        */
        super.submitList(list != null ? new ArrayList<>(list) : null);
    }

    public void addMoreCourses(List<Course> newCourses) {
        myCourses.addAll(newCourses);
        submitList(myCourses);
    }

    public void addMoreCourses(Course course) {
        myCourses.add(course);
        submitList(myCourses);
    }

    @Override
    public CourseChoiceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.row_layout_courses, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CourseChoiceAdapter.ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        final Course course = getItem(position);

        // Set item views based on your views and data model
        TextView courseTextView = viewHolder.courseTextView;
        courseTextView.setText(course.getCourseTitleLong());
        TextView courseNumTextView = viewHolder.courseNumTextView;
        courseNumTextView.setText(course.getCourseTitleShort());
        Button chooseButton = viewHolder.chooseButton;
        Button infoButton = viewHolder.infoButton;

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSemester.addClass(course);
            }
        });
    }

}
