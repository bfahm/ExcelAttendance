package com.xencosworks.excelattendance.Room.ServeeDB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Bola on 2/20/2019.
 */

@Entity (tableName = "servee")
public class Servee {
    public Servee(int serveeNumber, int isOdas, int isMdaresAhad, int isNadi, int isEarly) {
        this.serveeNumber = serveeNumber;
        this.isOdas = isOdas;
        this.isMdaresAhad = isMdaresAhad;
        this.isNadi = isNadi;
        this.isEarly = isEarly;
    }

    @ColumnInfo(name = "ser_num")
    @PrimaryKey
    public int serveeNumber;

    @ColumnInfo(name = "ser_odas")
    public int isOdas;

    @ColumnInfo(name = "ser_mdares")
    public int isMdaresAhad;

    @ColumnInfo(name = "ser_nadi")
    public int isNadi;

    @ColumnInfo(name = "ser_early")
    public int isEarly;
}
