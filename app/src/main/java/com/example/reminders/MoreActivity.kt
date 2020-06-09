package com.example.reminders

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import java.util.*


const val EXTRA_MESSAGE = "com.example.Reminders.sameID"

class MoreActivity : AppCompatActivity() {
    var reminderText: EditText? =  null
    var title: String? = null
    var description: String? = null
    var date: DateTimeHolder? = null
    var id = 0
    var item = RoomReminderItem("title", "description", 2020, 4,4, 14, 30)
    val CHANNEL_ID = "1"




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.more_activity)
        var b: Bundle? = Bundle()
        b = intent.extras
        val reminderID = b!!.getInt("com.example.reminders.ID")
        val id = reminderID
        println("id: $id")
        title = "some string"


        val vm : ReminderViewModel by viewModels()




        reminderText = findViewById<View>(R.id.reminderView) as EditText
        val sunday = findViewById<CheckBox>(R.id.sundayBox)
        val monday = findViewById<CheckBox>(R.id.mondayBox)
        val tuesday = findViewById<CheckBox>(R.id.tuesdayBox)
        val wednesday = findViewById<CheckBox>(R.id.wednesdayBox)
        val thursday = findViewById<CheckBox>(R.id.thursdayBox)
        val friday = findViewById<CheckBox>(R.id.fridayBox)
        val saturday = findViewById<CheckBox>(R.id.saturdayBox)
        val doneButton = findViewById<Button>(R.id.doneButton)
        val notes = findViewById<EditText>(R.id.editNotes)
        val dateButton = findViewById<Button>(R.id.dateButton)
        val timePicker = findViewById<TimePicker>(R.id.timePicker)
        val info = findViewById<TextView>(R.id.infoView)

        val note = notes.text.toString()

        dateButton.setOnClickListener{
            val intent =  Intent(this, CalendarActivity::class.java).apply { intent.putExtra(EXTRA_MESSAGE, id) }
            startActivity(intent)
        }


        val titleObserver: Observer<RoomReminderItem> = Observer { s ->
            item = s
            reminderText!!.setText(s.title)
            notes.setText(s.description)
            println("title observed")
        }



        if (vm != null) {
            vm.getByID(id).observe(this, titleObserver)}
        if (vm == null) {
            println("view model is null")
        }








        doneButton.setOnClickListener{
            item.setHour(timePicker.hour)
            item.setMinute(timePicker.minute)
            item.title = reminderText!!.text.toString()
            item.description = notes.text.toString()
            vm!!.update(item)
            info.text = "Reminder set: " + item.title + " " + item.day + " " + item.year + " " + item.hour + " " + item.minute

        }





    }
//          ***********When the notification is turned on call this method***********
//    private fun setCalendar(){
//
//        val myIntent = Intent(this, NotifyService::class.java)
//        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        val pendingIntent = PendingIntent.getService(this, 0, myIntent, 0)
//
//        val calendar: Calendar = Calendar.getInstance()
//        calendar.set(Calendar.SECOND, 0)
//        calendar.set(Calendar.MINUTE, 0)
//        calendar.set(Calendar.HOUR, 0)
//        calendar.set(Calendar.AM_PM, Calendar.AM)
//        calendar.add(Calendar.DAY_OF_MONTH, 1)
//    }


}
