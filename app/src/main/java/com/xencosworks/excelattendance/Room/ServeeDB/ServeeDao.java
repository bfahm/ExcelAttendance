package com.xencosworks.excelattendance.Room.ServeeDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.xencosworks.excelattendance.Room.ColumnDB.Column;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bola on 2/20/2019.
 */

@Dao
public interface ServeeDao {
    @Query("SELECT * FROM `servee`")
    List<Servee> getAll();

    @Insert
    void insert(Servee servee);

    @Delete
    void delete(Servee servee);

    @Query("DELETE FROM `Servee`")
    void deleteAll();

    @Query("UPDATE `servee` SET `ser_odas` = 1 WHERE `ser_num` == :ser_num")
    void updateOdas(int ser_num);

    @Query("UPDATE `servee` SET `ser_mdares` = 1 WHERE `ser_num` == :ser_num")
    void updateMdares(int ser_num);

    @Query("UPDATE `servee` SET `ser_nadi` = 1 WHERE `ser_num` == :ser_num")
    void updateNadi(int ser_num);

    @Query("UPDATE `servee` SET `ser_early` = 1 WHERE `ser_num` == :ser_num")
    void updateEarly(int ser_num);

}
