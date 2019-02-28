package com.xencosworks.excelattendance.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.xencosworks.excelattendance.Room.ColumnDB.Column;
import com.xencosworks.excelattendance.Room.ColumnDB.ColumnDao;
import com.xencosworks.excelattendance.Room.ServeeDB.Servee;
import com.xencosworks.excelattendance.Room.ServeeDB.ServeeDao;

/**
 * Created by Bola on 2/20/2019.
 */

@Database(entities = {Column.class, Servee.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ColumnDao columnDao();
    public abstract ServeeDao serveeDao();
}
