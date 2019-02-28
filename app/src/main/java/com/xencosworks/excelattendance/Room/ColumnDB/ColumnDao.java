package com.xencosworks.excelattendance.Room.ColumnDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Bola on 2/20/2019.
 */

@Dao
public interface ColumnDao {
    @Query("SELECT * FROM `column`")
    List<Column> getAll();

    @Insert
    void insertAll(Column... columns);

    @Delete
    void delete(Column column);

    @Query("DELETE FROM `column`")
    void deleteAll();
}
