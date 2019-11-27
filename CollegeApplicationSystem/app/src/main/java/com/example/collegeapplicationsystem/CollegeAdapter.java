package com.example.collegeapplicationsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeapplicationsystem.JSONParsing.College;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class CollegeAdapter extends FirestoreRecyclerAdapter<College, CollegeAdapter.CollegeHolder> {

    private static final String NO_DATA = "Data not given";

    private OnItemClickListener onItemClickListener;

    public CollegeAdapter(@NonNull FirestoreRecyclerOptions<College> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final CollegeHolder holder, int position, @NonNull College model) {
        holder.title.setText(model.getSchoolName());
        holder.id.setText(String.valueOf(model.getId()));
        holder.state.setText(model.getSchoolState());
        holder.city.setText(model.getSchoolCity());
        holder.zip.setText(model.getSchoolZip());

        if (String.valueOf(model.getSchoolSchoolUrl()).equals(" ")
                || String.valueOf(model.getSchoolSchoolUrl()).equalsIgnoreCase("")
                || String.valueOf(model.getSchoolSchoolUrl()).equals("null")) {
            holder.url.setText(NO_DATA);
        } else {
            holder.url.setText(model.getSchoolSchoolUrl());
        }

        if (String.valueOf(model.getLatestCostTuitionInState()).equalsIgnoreCase("null")) {
            holder.inStateTuition.setText(NO_DATA);
        } else {
            holder.inStateTuition.setText("$" + String.valueOf(model.getLatestCostTuitionInState()));
        }
        if (String.valueOf(model.getLatestCostTuitionOutOfState()).equalsIgnoreCase("null")) {
            holder.outOfStateTuition.setText(NO_DATA);
        } else {
            holder.outOfStateTuition.setText("$" + String.valueOf(model.getLatestCostTuitionOutOfState()));
        }
        if (String.valueOf(model.getLatestAdmissionsSatScores25thPercentileCriticalReading()).equalsIgnoreCase("null")) {
            holder.reading25.setText(NO_DATA);
        } else {
            holder.reading25.setText(String.valueOf(model.getLatestAdmissionsSatScores25thPercentileCriticalReading()));
        }
        if (String.valueOf(model.getLatestAdmissionsSatScores75thPercentileCriticalReading()).equalsIgnoreCase("null")) {
            holder.reading75.setText(NO_DATA);
        } else {
            holder.reading75.setText(String.valueOf(model.getLatestAdmissionsSatScores75thPercentileCriticalReading()));
        }
        if (String.valueOf(model.getLatestAdmissionsSatScores25thPercentileMath()).equalsIgnoreCase("null")) {
            holder.math25.setText(NO_DATA);
        } else {
            holder.math25.setText(String.valueOf(model.getLatestAdmissionsSatScores25thPercentileMath()));
        }
        if (String.valueOf(model.getLatestAdmissionsSatScores75thPercentileMath()).equalsIgnoreCase("null")) {
            holder.math75.setText(NO_DATA);
        } else {
            holder.math75.setText(String.valueOf(model.getLatestAdmissionsSatScores75thPercentileMath()));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.id.getVisibility() == View.VISIBLE) {
                    holder.id.setVisibility(View.GONE);
                } else {
                    holder.id.setVisibility(View.VISIBLE);
                }
                if (holder.idLabel.getVisibility() == View.VISIBLE) {
                    holder.idLabel.setVisibility(View.GONE);
                } else {
                    holder.idLabel.setVisibility(View.VISIBLE);
                }

                if (holder.state.getVisibility() == View.VISIBLE) {
                    holder.state.setVisibility(View.GONE);
                } else {
                    holder.state.setVisibility(View.VISIBLE);
                }
                if (holder.stateLabel.getVisibility() == View.VISIBLE) {
                    holder.stateLabel.setVisibility(View.GONE);
                } else {
                    holder.stateLabel.setVisibility(View.VISIBLE);
                }

                if (holder.city.getVisibility() == View.VISIBLE) {
                    holder.city.setVisibility(View.GONE);
                } else {
                    holder.city.setVisibility(View.VISIBLE);
                }
                if (holder.cityLabel.getVisibility() == View.VISIBLE) {
                    holder.cityLabel.setVisibility(View.GONE);
                } else {
                    holder.cityLabel.setVisibility(View.VISIBLE);
                }

                if (holder.zip.getVisibility() == View.VISIBLE) {
                    holder.zip.setVisibility(View.GONE);
                } else {
                    holder.zip.setVisibility(View.VISIBLE);
                }
                if (holder.zipLabel.getVisibility() == View.VISIBLE) {
                    holder.zipLabel.setVisibility(View.GONE);
                } else {
                    holder.zipLabel.setVisibility(View.VISIBLE);
                }

                if (holder.url.getVisibility() == View.VISIBLE) {
                    holder.url.setVisibility(View.GONE);
                } else {
                    holder.url.setVisibility(View.VISIBLE);
                }
                if (holder.urlLabel.getVisibility() == View.VISIBLE) {
                    holder.urlLabel.setVisibility(View.GONE);
                } else {
                    holder.urlLabel.setVisibility(View.VISIBLE);
                }

                if (holder.inStateTuition.getVisibility() == View.VISIBLE) {
                    holder.inStateTuition.setVisibility(View.GONE);
                } else {
                    holder.inStateTuition.setVisibility(View.VISIBLE);
                }
                if (holder.inStateTuitionLabel.getVisibility() == View.VISIBLE) {
                    holder.inStateTuitionLabel.setVisibility(View.GONE);
                } else {
                    holder.inStateTuitionLabel.setVisibility(View.VISIBLE);
                }

                if (holder.outOfStateTuition.getVisibility() == View.VISIBLE) {
                    holder.outOfStateTuition.setVisibility(View.GONE);
                } else {
                    holder.outOfStateTuition.setVisibility(View.VISIBLE);
                }
                if (holder.outOfStateTuitionLabel.getVisibility() == View.VISIBLE) {
                    holder.outOfStateTuitionLabel.setVisibility(View.GONE);
                } else {
                    holder.outOfStateTuitionLabel.setVisibility(View.VISIBLE);
                }

                if (holder.reading25.getVisibility() == View.VISIBLE) {
                    holder.reading25.setVisibility(View.GONE);
                } else {
                    holder.reading25.setVisibility(View.VISIBLE);
                }
                if (holder.reading25Label.getVisibility() == View.VISIBLE) {
                    holder.reading25Label.setVisibility(View.GONE);
                } else {
                    holder.reading25Label.setVisibility(View.VISIBLE);
                }

                if (holder.reading75.getVisibility() == View.VISIBLE) {
                    holder.reading75.setVisibility(View.GONE);
                } else {
                    holder.reading75.setVisibility(View.VISIBLE);
                }
                if (holder.reading75Label.getVisibility() == View.VISIBLE) {
                    holder.reading75Label.setVisibility(View.GONE);
                } else {
                    holder.reading75Label.setVisibility(View.VISIBLE);
                }

                if (holder.math25.getVisibility() == View.VISIBLE) {
                    holder.math25.setVisibility(View.GONE);
                } else {
                    holder.math25.setVisibility(View.VISIBLE);
                }
                if (holder.math25Label.getVisibility() == View.VISIBLE) {
                    holder.math25Label.setVisibility(View.GONE);
                } else {
                    holder.math25Label.setVisibility(View.VISIBLE);
                }

                if (holder.math75.getVisibility() == View.VISIBLE) {
                    holder.math75.setVisibility(View.GONE);
                } else {
                    holder.math75.setVisibility(View.VISIBLE);
                }
                if (holder.math75Label.getVisibility() == View.VISIBLE) {
                    holder.math75Label.setVisibility(View.GONE);
                } else {
                    holder.math75Label.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @NonNull
    @Override
    public CollegeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.college_item,
                parent, false);
        return new CollegeHolder(view);
    }

    class CollegeHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView id;
        TextView idLabel;
        TextView state;
        TextView stateLabel;
        TextView city;
        TextView cityLabel;
        TextView zip;
        TextView zipLabel;
        TextView url;
        TextView urlLabel;
        TextView inStateTuition;
        TextView inStateTuitionLabel;
        TextView outOfStateTuition;
        TextView outOfStateTuitionLabel;
        TextView reading25;
        TextView reading25Label;
        TextView reading75;
        TextView reading75Label;
        TextView math25;
        TextView math25Label;
        TextView math75;
        TextView math75Label;

        public CollegeHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.collegeTitle);
            id = itemView.findViewById(R.id.id);
            idLabel = itemView.findViewById(R.id.idLabel);
            state = itemView.findViewById(R.id.state);
            stateLabel = itemView.findViewById(R.id.stateLabel);
            city = itemView.findViewById(R.id.city);
            cityLabel = itemView.findViewById(R.id.cityLabel);
            zip = itemView.findViewById(R.id.zip);
            zipLabel = itemView.findViewById(R.id.zipLabel);
            url = itemView.findViewById(R.id.url);
            urlLabel = itemView.findViewById(R.id.urlLabel);
            inStateTuition = itemView.findViewById(R.id.inStateTuition);
            inStateTuitionLabel = itemView.findViewById(R.id.inStateTuitionLabel);
            outOfStateTuition = itemView.findViewById(R.id.outOfStateTuition);
            outOfStateTuitionLabel = itemView.findViewById(R.id.outOfStateTuitionLabel);
            reading25 = itemView.findViewById(R.id.reading25);
            reading25Label = itemView.findViewById(R.id.reading25Label);
            reading75 = itemView.findViewById(R.id.reading75);
            reading75Label = itemView.findViewById(R.id.reading75Label);
            math25 = itemView.findViewById(R.id.math25);
            math25Label = itemView.findViewById(R.id.math25Label);
            math75 = itemView.findViewById(R.id.math75);
            math75Label = itemView.findViewById(R.id.math75Label);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                        onItemClickListener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
