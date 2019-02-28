package com.xencosworks.excelattendance;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.xencosworks.excelattendance.Room.AppDatabase;
import com.xencosworks.excelattendance.Room.ColumnDB.Column;

import java.util.ArrayList;
import java.util.List;

public class SetupActivity extends AppCompatActivity {

    private static final String TAG = "SetupActivity";
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<Column> columnNames;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "main-db").allowMainThreadQueries().build();
        columnNames = new ArrayList<>();

        refreshViews();

    }
    private void refreshViews(){
        columnNames = db.columnDao().getAll();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new ColumnAdapter(columnNames);
        recyclerView.setAdapter(adapter);
    }
    private void setupTemplateColumns(){
        Log.v("-------------", "temp--------inside");
        db.columnDao().insertAll(new Column("حضور قداس"));
        db.columnDao().insertAll(new Column("حضور مدارس احد"));
        db.columnDao().insertAll(new Column("حضور نادي"));
        db.columnDao().insertAll(new Column("حضور مبكر"));
        refreshViews();
    }
    private void resetColumns(){
        db.columnDao().deleteAll();
        refreshViews();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setup_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.col_template:
                setupTemplateColumns();
                Log.v("-------------", "temp");
                return true;
            case R.id.col_reset:
                resetColumns();
                Log.v("-------------", "reset");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
