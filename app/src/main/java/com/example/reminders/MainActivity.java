package com.example.reminders;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<RoomReminderItem> dataset;
    private LiveData<List<RoomReminderItem>> liveDataset;
    private ReminderViewModel vm;
    private MoreActivity more;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        vm = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ReminderViewModel.class);
        liveDataset = vm.getAllReminders();
        dataset = liveDataset.getValue();


               vm.getAllReminders().observe(this, new Observer<List<RoomReminderItem>>() {
                   @Override
                   public void onChanged(List<RoomReminderItem> roomReminderItems) {
                        mAdapter.setDataset(roomReminderItems);
                   }
               });


        mAdapter = new MyAdapter(dataset);
        recyclerView.setAdapter(mAdapter);
    }

    //todo: the notification needs to be assigned to a channel

//    private void createNotificationChannel() {
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = getString(R.string.channel_name);
//            String description = getString(R.string.channel_description);
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
//            channel.setDescription(description);
//            // Register the channel with the system; you can't change the importance
//            // or other notification behaviors after this
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
//        }
//    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteAllButton:
                vm.deleteAllReminders();
                return true;
            case R.id.debugButton: ;
                boolean a = vm.repository.getAllReminders().getValue() == null;
                Toast.makeText(this.getApplicationContext(), String.valueOf(a), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.addReminder:
                RoomReminderItem newItem =  new RoomReminderItem("new Reminder", "new desc", 2020, 4,4, 14, 30);
                vm.insert(newItem);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

