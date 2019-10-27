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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csschedulemaker.courseData.Course;
import com.example.csschedulemaker.courseData.Semester;
import com.example.csschedulemaker.courseData.Utilities;

import java.util.ArrayList;
import java.util.List;

public class CourseChoiceAdapter extends ListAdapter<Course, CourseChoiceAdapter.ViewHolder> {

    private List<Course> myCourses;
    private Semester currentSemester;
    private Activity mActivity;

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

    public CourseChoiceAdapter(List<Course> courses, Semester currentSemester, Activity mActivity) {
        super(DIFF_CALLBACK);
        this.currentSemester = currentSemester;
        this.mActivity = mActivity;
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
            public void onClick(final View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
                alertDialog.setTitle("Class Info");
                alertDialog.setMessage("Course Number:\n" + course.getCourseTitleShort() + "\nCourse Description:\n" + course.getCourseDescription() + "\nCredits:\n" + course.getNumberOfCredits());
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });

        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (searchListByTitle(course.getCourseTitleShort()) == -1) {

                    if (course.getCourseTitleShort().equalsIgnoreCase("CSE148")) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setCancelable(true);
                        builder.setTitle("Pre-requisite");
                        builder.setMessage("This Class requires CSE118. If you have not taken that class you can add it here.");
                        builder.setPositiveButton("I HAVE IT, ADD CLASS",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent returnIntent = new Intent();
                                        returnIntent.putExtra("updatedSemester", course);
                                        mActivity.setResult(Activity.RESULT_OK, returnIntent);

                                        mActivity.finish();
                                    }
                                });
                        builder.setNegativeButton("OKAY, ADD THE PRE-REQ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (searchListByTitle("CSE118") == -1) {
                                    Intent returnIntent = new Intent();
                                    returnIntent.putExtra("updatedSemester", myCourses.get(searchAllByTitle("CSE118")));
                                    mActivity.setResult(Activity.RESULT_OK, returnIntent);

                                    mActivity.finish();
                                } else {
                                    Utilities.showRepeatClass(view);
                                }
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else if (course.getCourseTitleShort().equalsIgnoreCase("CSE218")) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setCancelable(true);
                        builder.setTitle("Pre-requisite");
                        builder.setMessage("This Class requires CSE148. If you have not taken that class you can add it here.");
                        builder.setPositiveButton("I HAVE IT, ADD CLASS",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent returnIntent = new Intent();
                                        returnIntent.putExtra("updatedSemester", course);
                                        mActivity.setResult(Activity.RESULT_OK, returnIntent);

                                        mActivity.finish();
                                    }
                                });
                        builder.setNegativeButton("OKAY, ADD THE PRE-REQ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (searchListByTitle("CSE148") == -1) {
                                    Intent returnIntent = new Intent();
                                    returnIntent.putExtra("updatedSemester", myCourses.get(searchAllByTitle("CSE148")));
                                    mActivity.setResult(Activity.RESULT_OK, returnIntent);

                                    mActivity.finish();
                                } else {
                                    Utilities.showRepeatClass(view);
                                }
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else if (course.getCourseTitleShort().equalsIgnoreCase("CSE222")) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setCancelable(true);
                        builder.setTitle("Pre-requisite");
                        builder.setMessage("This Class requires CSE218. If you have not taken that class you can add it here.");
                        builder.setPositiveButton("I HAVE IT, ADD CLASS",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent returnIntent = new Intent();
                                        returnIntent.putExtra("updatedSemester", course);
                                        mActivity.setResult(Activity.RESULT_OK, returnIntent);

                                        mActivity.finish();
                                    }
                                });
                        builder.setNegativeButton("OKAY, ADD THE PRE-REQ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (searchListByTitle("CSE218") == -1) {
                                    Intent returnIntent = new Intent();
                                    returnIntent.putExtra("updatedSemester", myCourses.get(searchAllByTitle("CSE218")));
                                    mActivity.setResult(Activity.RESULT_OK, returnIntent);

                                    mActivity.finish();
                                } else {
                                    Utilities.showRepeatClass(view);
                                }
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else if (course.getCourseTitleShort().equalsIgnoreCase("CSE248")) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setCancelable(true);
                        builder.setTitle("Pre-requisite");
                        builder.setMessage("This Class requires CSE218. If you have not taken that class you can add it here.");
                        builder.setPositiveButton("I HAVE IT, ADD CLASS",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent returnIntent = new Intent();
                                        returnIntent.putExtra("updatedSemester", course);
                                        mActivity.setResult(Activity.RESULT_OK, returnIntent);

                                        mActivity.finish();
                                    }
                                });
                        builder.setNegativeButton("OKAY, ADD THE PRE-REQ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (searchListByTitle("CSE218") == -1) {
                                    Intent returnIntent = new Intent();
                                    returnIntent.putExtra("updatedSemester", myCourses.get(searchAllByTitle("CSE218")));
                                    mActivity.setResult(Activity.RESULT_OK, returnIntent);

                                    mActivity.finish();
                                } else {
                                    Utilities.showRepeatClass(view);
                                }
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else if (course.getCourseTitleShort().equalsIgnoreCase("MAT125")) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setCancelable(true);
                        builder.setTitle("Pre-requisite");
                        builder.setMessage("This Class requires MAT124. If you have not taken that class you can add it here.");
                        builder.setPositiveButton("I HAVE IT, ADD CLASS",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent returnIntent = new Intent();
                                        returnIntent.putExtra("updatedSemester", course);
                                        mActivity.setResult(Activity.RESULT_OK, returnIntent);

                                        mActivity.finish();
                                    }
                                });
                        builder.setNegativeButton("OKAY, ADD THE PRE-REQ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (searchListByTitle("MAT124") == -1) {
                                    Intent returnIntent = new Intent();
                                    returnIntent.putExtra("updatedSemester", myCourses.get(searchAllByTitle("MAT124")));
                                    mActivity.setResult(Activity.RESULT_OK, returnIntent);

                                    mActivity.finish();
                                } else {
                                    Utilities.showRepeatClass(view);
                                }
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else if (course.getCourseTitleShort().equalsIgnoreCase("MAT141")) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setCancelable(true);
                        builder.setTitle("Pre-requisite");
                        builder.setMessage("This Class requires MAT125. If you have not taken that class you can add it here.");
                        builder.setPositiveButton("I HAVE IT, ADD CLASS",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent returnIntent = new Intent();
                                        returnIntent.putExtra("updatedSemester", course);
                                        mActivity.setResult(Activity.RESULT_OK, returnIntent);

                                        mActivity.finish();
                                    }
                                });
                        builder.setNegativeButton("OKAY, ADD THE PRE-REQ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (searchListByTitle("MAT125") == -1) {
                                    Intent returnIntent = new Intent();
                                    returnIntent.putExtra("updatedSemester", myCourses.get(searchAllByTitle("MAT125")));
                                    mActivity.setResult(Activity.RESULT_OK, returnIntent);

                                    mActivity.finish();
                                } else {
                                    Utilities.showRepeatClass(view);
                                }
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else if (course.getCourseTitleShort().equalsIgnoreCase("MAT142")) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setCancelable(true);
                        builder.setTitle("Pre-requisite");
                        builder.setMessage("This Class requires MAT141. If you have not taken that class you can add it here.");
                        builder.setPositiveButton("I HAVE IT, ADD CLASS",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent returnIntent = new Intent();
                                        returnIntent.putExtra("updatedSemester", course);
                                        mActivity.setResult(Activity.RESULT_OK, returnIntent);

                                        mActivity.finish();
                                    }
                                });
                        builder.setNegativeButton("OKAY, ADD THE PRE-REQ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (searchListByTitle("MAT141") == -1) {
                                    Intent returnIntent = new Intent();
                                    returnIntent.putExtra("updatedSemester", myCourses.get(searchAllByTitle("MAT141")));
                                    mActivity.setResult(Activity.RESULT_OK, returnIntent);

                                    mActivity.finish();
                                } else {
                                    Utilities.showRepeatClass(view);
                                }
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else if (course.getCourseTitleShort().equalsIgnoreCase("MAT205")) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setCancelable(true);
                        builder.setTitle("Pre-requisite");
                        builder.setMessage("This Class requires MAT141. If you have not taken that class you can add it here.");
                        builder.setPositiveButton("I HAVE IT, ADD CLASS",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent returnIntent = new Intent();
                                        returnIntent.putExtra("updatedSemester", course);
                                        mActivity.setResult(Activity.RESULT_OK, returnIntent);

                                        mActivity.finish();
                                    }
                                });
                        builder.setNegativeButton("OKAY, ADD THE PRE-REQ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (searchListByTitle("MAT141") == -1) {
                                    Intent returnIntent = new Intent();
                                    returnIntent.putExtra("updatedSemester", myCourses.get(searchAllByTitle("MAT141")));
                                    mActivity.setResult(Activity.RESULT_OK, returnIntent);

                                    mActivity.finish();
                                } else {
                                    Utilities.showRepeatClass(view);
                                }
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else if (course.getCourseTitleShort().equalsIgnoreCase("MAT210")) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setCancelable(true);
                        builder.setTitle("Pre-requisite");
                        builder.setMessage("This Class requires MAT141. If you have not taken that class you can add it here.");
                        builder.setPositiveButton("I HAVE IT, ADD CLASS",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent returnIntent = new Intent();
                                        returnIntent.putExtra("updatedSemester", course);
                                        mActivity.setResult(Activity.RESULT_OK, returnIntent);

                                        mActivity.finish();
                                    }
                                });
                        builder.setNegativeButton("OKAY, ADD THE PRE-REQ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (searchListByTitle("MAT141") == -1) {
                                    Intent returnIntent = new Intent();
                                    returnIntent.putExtra("updatedSemester", myCourses.get(searchAllByTitle("MAT141")));
                                    mActivity.setResult(Activity.RESULT_OK, returnIntent);

                                    mActivity.finish();
                                } else {
                                    Utilities.showRepeatClass(view);
                                }
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }



                    else {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("updatedSemester", course);
                        mActivity.setResult(Activity.RESULT_OK, returnIntent);

                        mActivity.finish();
                    }
                }

                else {
                    Utilities.showRepeatClass(view);
                }
            }
        });
    }

    private int searchListByTitle(String num) {
        for (int i = 0; i < currentSemester.getSemCourses().size(); i++) {
            if (num.equalsIgnoreCase(currentSemester.getSemCourses().get(i).getCourseTitleShort())) {
                return i;
            }
        }
        return -1;
    }

    private int searchAllByTitle(String num) {
        for (int i = 0; i < myCourses.size(); i++) {
            if (num.equalsIgnoreCase(myCourses.get(i).getCourseTitleShort())) {
                return i;
            }
        }
        return -1;
    }
}
