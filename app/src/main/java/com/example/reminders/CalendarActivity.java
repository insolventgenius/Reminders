package com.example.reminders;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import java.util.Calendar;
import java.util.Date;


public class CalendarActivity extends AppCompatActivity {
    public String cDate;
    public Long date = Calendar.getInstance().getTime().getTime();
    private RoomReminderItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);
        Intent intent = getIntent();
//        Bundle b = intent.getExtras();
        /*
        the id below always pulls the default value rather than the one from
        the last activity
         */
        long lID = intent.getLongExtra("EXTRA_MESSAGE", 1);
        int id = (int) lID;
        System.out.println("[calendar activity] intent ID: " + id);

        final CalendarView calendar = findViewById(R.id.calendarView);
        Button done = (Button) findViewById(R.id.doneButton);


         final ReminderViewModel vm = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ReminderViewModel.class);
        vm.getByID(id).observe(this, new Observer<RoomReminderItem>() {
            @Override
            public void onChanged(RoomReminderItem reminderItem) {
                calendar.setDate(date);
            }
        });


        //we can just have this method set the year, month and day
        //then we create a placeholder for the time which the time picker will fill
        //so the date really needs to be its own object
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                item.setYear(year);
                item.setMonth(month);
                item.setDay(dayOfMonth);
                vm.update(item);
            }
        });



        /*
        might not need a done button if i can just update the DB by having an
        onDateSelected listener do it for me
         */
//        done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                calendar.get
//            }
//        });


    }


}
