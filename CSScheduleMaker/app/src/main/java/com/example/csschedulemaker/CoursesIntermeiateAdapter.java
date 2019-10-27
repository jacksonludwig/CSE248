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

import com.example.csschedulemaker.courseData.Course;

import java.util.ArrayList;
import java.util.List;

public class CoursesIntermeiateAdapter extends ListAdapter<Course, CoursesIntermeiateAdapter.ViewHolder> {

    private List<Course> myCourses;
    private static final int ADD_CLASS_REQUEST_CODE = 1;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView courseTitleTextView;
        public TextView courseNumberTextView;
        public Button deleteCourseButton;

        public ViewHolder(View itemView) {
            super(itemView);
            courseTitleTextView = (TextView) itemView.findViewById(R.id.my_schedule_course_name);
            courseNumberTextView = (TextView) itemView.findViewById(R.id.my_schedule_course_number);
            deleteCourseButton = (Button) itemView.findViewById(R.id.delete_course_button);
        }
    }

    // checks if the thing being added to the list is unique
    public static final DiffUtil.ItemCallback<Course> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Course>() {
                @Override
                public boolean areItemsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
                    return oldItem.getCourseTitleShort().equalsIgnoreCase(newItem.getCourseTitleShort());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
                    return oldItem.getCourseDescription().equals(newItem.getCourseDescription());
                }
            };

    public CoursesIntermeiateAdapter(List<Course> courses) {
        super(DIFF_CALLBACK);
        myCourses = courses;
        submitList(myCourses);
    }

    @Override
    public void submitList(final List<Course> list) {
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
    public CoursesIntermeiateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.row_layout_course_indermediate, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CoursesIntermeiateAdapter.ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        final Course course = getItem(position);

        // Set item views based on your views and data model
        TextView courseTitleTextView = viewHolder.courseTitleTextView;
        courseTitleTextView.setText(course.getCourseTitleLong());
        TextView courseNumTextView = viewHolder.courseNumberTextView;
        if ((int) (course.getNumberOfCredits()) == 1) {
            courseNumTextView.setText(course.getCourseTitleShort() + ", " + (int) (course.getNumberOfCredits()) + " credit");
        } else {
            courseNumTextView.setText(course.getCourseTitleShort() + ", " + (int) (course.getNumberOfCredits()) + " credits");
        }
        Button delButton = viewHolder.deleteCourseButton;

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setCancelable(true);
                builder.setTitle("Delete Course");
                builder.setMessage("Are you sure you want to remove this course?");
                builder.setPositiveButton("Yes, delete",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                myCourses.remove(course);
                                submitList(myCourses);
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

    }

    public static void showDeleteToast(Context context) {
        Toast.makeText(context, "Course deleted", Toast.LENGTH_SHORT).show();
    }

}
