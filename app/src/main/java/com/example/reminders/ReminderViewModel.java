package com.example.reminders;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;


import java.util.List;

public class ReminderViewModel extends AndroidViewModel {
    public ReminderRepository repository;
    private LiveData<List<RoomReminderItem>> allReminders;

    public ReminderViewModel(@NonNull Application application) {
        super(application);
        repository = new ReminderRepository(application);
        allReminders = repository.getAllReminders();
    }

    public void insert(RoomReminderItem reminder){
        repository.insert(reminder);
    }
    public void delete(RoomReminderItem reminder){
        repository.delete(reminder);
    }
    public void update(RoomReminderItem reminder){
        repository.update(reminder);
    }
    public void deleteAllReminders(){
        repository.deleteAllReminders();
    }


    public LiveData<List<RoomReminderItem>> getAllReminders(){
        System.out.println("[viewmodel] livedata= "+ allReminders.getValue());
        return allReminders;
    }


    public LiveData<RoomReminderItem> getByID(int ID){
        return repository.getByID(ID);
    }



}
