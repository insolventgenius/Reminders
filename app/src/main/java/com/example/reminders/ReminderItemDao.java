package com.example.reminders;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReminderItemDao {
    @Insert
    void insert(RoomReminderItem roomReminderItem);
    @Update
    void update(RoomReminderItem roomReminderItem);
    @Delete
    void delete(RoomReminderItem roomReminderItem);



    @Query("DELETE FROM reminder_table")
    void deleteAllReminders();

    @Query("SELECT * FROM reminder_table")
    LiveData<List<RoomReminderItem>> getAllReminders();


    @Query("SELECT * FROM reminder_table WHERE id = :searchID")
    LiveData<RoomReminderItem> getByID(int searchID);
}
