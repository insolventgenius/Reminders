package com.example.reminders;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import java.util.List;

public class ReminderRepository {
    private ReminderItemDao reminderItemDao;
    private LiveData<List<RoomReminderItem>> allReminders;
    public int count = 0;
    private static ReminderDatabase instance;
    private ReminderDatabase database;

    public ReminderRepository(Application application) {
        database = getInstance(application.getApplicationContext());
        reminderItemDao = database.reminderItemDao();
        allReminders = reminderItemDao.getAllReminders();
    }

    public static synchronized ReminderDatabase getInstance(Context context) {
        if (instance == null) {
            System.out.println("Database instance created");
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ReminderDatabase.class, "reminder_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public void insert(final RoomReminderItem reminder) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                reminderItemDao.insert(reminder);
                allReminders = reminderItemDao.getAllReminders();
            }
        }).start();
        count++;
        System.out.println("[Repository] Reminder Inserted #" + count);
        System.out.println("[Repository] Database: " + database.toString());
    }

    public void update(final RoomReminderItem reminder) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                reminderItemDao.update(reminder);
            }
        }).start();
        System.out.println("Repository: reminder updated");
    }

    public void delete(final RoomReminderItem reminder) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ReminderItemDao dao = reminderItemDao;
                dao.delete(reminder);
            }
        }).start();
    }

    public void deleteAllReminders() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ReminderItemDao dao = reminderItemDao;
                dao.deleteAllReminders();
            }
        }).start();
    }


    public LiveData<List<RoomReminderItem>> getAllReminders() {
        System.out.println("[repo] livedata= "+ allReminders.getValue());
        return allReminders;
    }

    public LiveData<RoomReminderItem> getByID(int ID){
        return reminderItemDao.getByID(ID);
    }

    }

