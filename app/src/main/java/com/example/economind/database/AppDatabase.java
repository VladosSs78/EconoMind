package com.example.economind.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CategoryCostClass.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DaoCategoryCostInterface costDao();
}
