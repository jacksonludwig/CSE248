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

public class CollegeAdapter extends FirestoreRecyclerAdapter<College, CollegeAdapter.CollegeHolder> {

    private static final String NO_DATA = "Data not given";

    public CollegeAdapter(@NonNull FirestoreRecyclerOptions<College> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CollegeHolder holder, int position, @NonNull College model) {
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
        TextView state;
        TextView city;
        TextView zip;
        TextView url;
        TextView inStateTuition;
        TextView outOfStateTuition;
        TextView reading25;
        TextView reading75;
        TextView math25;
        TextView math75;

        public CollegeHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.collegeTitle);
            id = itemView.findViewById(R.id.id);
            state = itemView.findViewById(R.id.state);
            city = itemView.findViewById(R.id.city);
            zip = itemView.findViewById(R.id.zip);
            url = itemView.findViewById(R.id.url);
            inStateTuition = itemView.findViewById(R.id.inStateTuition);
            outOfStateTuition = itemView.findViewById(R.id.outOfStateTuition);
            reading25 = itemView.findViewById(R.id.reading25);
            reading75 = itemView.findViewById(R.id.reading75);
            math25 = itemView.findViewById(R.id.math25);
            math75 = itemView.findViewById(R.id.math75);
        }
    }
}
