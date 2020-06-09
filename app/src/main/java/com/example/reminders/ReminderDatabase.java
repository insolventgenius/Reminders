package com.example.reminders;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = RoomReminderItem.class, version = 1)
public abstract class ReminderDatabase extends RoomDatabase {


    public abstract ReminderItemDao reminderItemDao();





}
