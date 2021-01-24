package com.example.trznica_cibalae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SupplyActivity extends AppCompatActivity {


    private Button btn_StartActivity_supply;
    private RecyclerView recyclerView;
    private Button btn_addSupply;
    private JobAdapter jobAdapter;
    private List<Job> mJobs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply);

        InitializeUI();
        setUpRecyclerView();

    }

    private void setUpRecyclerView() {
        mJobs = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerViewSupply);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        fetchData();
    }

    private void InitializeUI(){

        btn_StartActivity_supply = findViewById(R.id.btn_StartActivity);
        btn_StartActivity_supply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SupplyActivity.this ,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btn_addSupply = findViewById(R.id.btn_addSupply);
        btn_addSupply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SupplyActivity.this ,OfferActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("category",1);
                startActivity(intent);
            }
        });

    }

    private void fetchData(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Ponude");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mJobs.clear();
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    Job job = snapshot1.getValue(Job.class);
                    mJobs.add(job);
                }

                jobAdapter = new JobAdapter(SupplyActivity.this, mJobs);
                recyclerView.setAdapter(jobAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}