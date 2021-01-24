package com.example.trznica_cibalae;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {

    private Context mcontext;
    private List<Job> mJobs;

    public JobAdapter(Context mcontext, List<Job> mJobs){
        this.mcontext = mcontext;
        this.mJobs = mJobs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.job_item,parent,false);
        return new JobAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(mJobs.get(position).getTitle());
        holder.desc.setText("Opis: " + mJobs.get(position).getDescription());
        holder.cijena.setText("Cijena: " + mJobs.get(position).getCijena());
        holder.kontakt.setText("Kontakt: " + mJobs.get(position).getKontakt());
    }

    @Override
    public int getItemCount() {
        return mJobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView desc;
        private TextView cijena;
        private TextView kontakt;

        public ViewHolder(View itemView){
            super(itemView);
            cijena = itemView.findViewById(R.id.txt_cijena);
            title = itemView.findViewById(R.id.txt_title);
            desc = itemView.findViewById(R.id.txt_desc);
            kontakt = itemView.findViewById(R.id.txt_kontakt);

        }
    }

    public void addNewJob(Job job){
        mJobs.add(job);
        notifyItemInserted(mJobs.size()-1);
    }
}
