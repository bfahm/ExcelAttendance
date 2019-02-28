package com.xencosworks.excelattendance;

import android.Manifest;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteConstraintException;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ajts.androidmads.library.SQLiteToExcel;
import com.xencosworks.excelattendance.Room.AppDatabase;
import com.xencosworks.excelattendance.Room.ServeeDB.Servee;
public class MainActivity extends AppCompatActivity {

    AppDatabase db;
    private static final String TAG = "MainActivity";
    int returnedResult = -1;
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "main-db").allowMainThreadQueries().build();

        Button navigateToSetup = findViewById(R.id.button_go_to_setup);
        navigateToSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SetupActivity.class));
            }
        });


        Button results = findViewById(R.id.results);
        results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestResult.class));
            }
        });

        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, ReaderActivity.class),1);
            }
        });

        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, ReaderActivity.class),2);
            }
        });

        Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, ReaderActivity.class),3);
            }
        });

        Button btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, ReaderActivity.class),4);
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractToExcel();
            }
        });
    }

    private void extractToExcel() {
        SQLiteToExcel sqliteToExcel = new SQLiteToExcel(this, "main-db");
        sqliteToExcel.exportSingleTable("servee", "servee.xls", new SQLiteToExcel.ExportListener() {
            @Override
            public void onStart() {
                Log.v(TAG, "Started Exporting");
            }

            @Override
            public void onCompleted(String filePath) {
                Log.v(TAG, "Exported Successfully");
                Toast.makeText(MainActivity.this, "Exported Successfully", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG, "Error occurred while exporting "+ e);
                Toast.makeText(MainActivity.this, "An error occurred while exporting", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try{
            returnedResult = Integer.parseInt(data.getData().toString());
            switch (requestCode){
                case 1:
                    affectDatabase(1, returnedResult);
                    break;
                case 2:
                    affectDatabase(2, returnedResult);
                    break;
                case 3:
                    affectDatabase(3, returnedResult);
                    break;
                case 4:
                    affectDatabase(4, returnedResult);
                    break;
            }
        }catch (NullPointerException e){
            Log.v(TAG, "Navigated back without scanning a number" + e);
        }


    }

    private void affectDatabase(int state, int inputId){
        switch (state){
            case 1:
                try{
                    db.serveeDao().insert(new Servee(inputId, 1, 0, 0, 0));
                }catch (SQLiteConstraintException e){
                    db.serveeDao().updateOdas(inputId);
                }
                break;
            case 2:
                try{
                    db.serveeDao().insert(new Servee(inputId, 0, 1, 0, 0));
                }catch (SQLiteConstraintException e){
                    db.serveeDao().updateMdares(inputId);
                }
                break;
            case 3:
                try{
                    db.serveeDao().insert(new Servee(inputId, 0, 0, 1, 0));
                }catch (SQLiteConstraintException e){
                    db.serveeDao().updateNadi(inputId);
                }
                break;
            case 4:
                try{
                    db.serveeDao().insert(new Servee(inputId, 0, 0, 0, 1));
                }catch (SQLiteConstraintException e){
                    db.serveeDao().updateEarly(inputId);
                }
                break;
        }
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

}
