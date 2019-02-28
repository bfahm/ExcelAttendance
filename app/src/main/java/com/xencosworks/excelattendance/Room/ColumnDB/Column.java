package com.xencosworks.excelattendance.Room.ColumnDB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Bola on 2/20/2019.
 */

@Entity (tableName = "column")
public class Column {
    public Column(String columnName){
        this.columnName = columnName;
    }
    @PrimaryKey (autoGenerate = true)
    public int cid;

    @ColumnInfo(name = "col_name")
    public String columnName;
}
