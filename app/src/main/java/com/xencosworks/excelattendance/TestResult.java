package com.xencosworks.excelattendance;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xencosworks.excelattendance.Room.AppDatabase;
import com.xencosworks.excelattendance.Room.ServeeDB.Servee;

import java.util.ArrayList;
import java.util.List;

public class TestResult extends AppCompatActivity {

    List<Servee> servees = new ArrayList<>();
    AppDatabase db;
    RecyclerView recyclerView;
    ServeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "main-db").allowMainThreadQueries().build();

        servees = db.serveeDao().getAll();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ServeeAdapter(servees);
        recyclerView.setAdapter(adapter);
    }
}
