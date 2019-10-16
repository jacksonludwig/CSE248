package com.example.csschedulemaker;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class SemesterAdapter extends RecyclerView.Adapter<SemesterAdapter.SemesterViewHolder> {
    private String[] data;

    public static class SemesterViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public SemesterViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }

    public SemesterAdapter(String[] data) {
        this.data = data;
    }

    @Override
    public SemesterAdapter.SemesterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main, parent, false);

        SemesterViewHolder vh = new SemesterViewHolder(v);
        return vh;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(SemesterViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(data[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.length;
    }
}
