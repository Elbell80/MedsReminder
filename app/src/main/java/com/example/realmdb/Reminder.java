package com.example.realmdb;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.Calendar;


public class Reminder extends AppCompatActivity implements View.OnClickListener {

    TimePicker timePicker;
    private Button btn_set;
   /* private EditText hr, mins, sec;
    private EditText editTitle;

    Realm realm;
    Timer timer;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        /*editTitle = (EditText) findViewById(R.id.editTitle);
        hr = (EditText) findViewById(R.id.hour);
        mins = (EditText) findViewById(R.id.minutes);
        sec = (EditText) findViewById(R.id.seconds);*/


        timePicker = (TimePicker) findViewById(R.id.timePicker);
        btn_set = (Button) findViewById(R.id.set);
        btn_set.setOnClickListener(this);
        //realm=Realm.getDefaultInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void onClick(View view) {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        if (android.os.Build.VERSION.SDK_INT >= 23) {
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                    timePicker.getHour(), timePicker.getMinute(), 0);
        } else {
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                    timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);
        }

        setAlarm(calendar.getTimeInMillis());
        //writeDB(calendar);


    /*public void writeDB(float final time, ){
            realm.executeTransactionAsync(new Realm.Transaction() {
                                              @Override
                                              public void execute(Realm bgRealm) {
                                                  Timer user = bgRealm.createObject(Timer.class);
                                                  user.setTime(time);
                                                  user.setDosage(age);
                                                  user.setTiming(weight);
                                                  //user.setAge(age);
                                                  //user.setWeight(weight);
                                              }
                                          }, new Realm.Transaction.OnSuccess() {
                                              @Override
                                              public void onSuccess() {
                                                  Log.v("Database","Data Inserted");


                                              }
                                          }, new Realm.Transaction.OnError() {
                                              @Override
                                              public void onError(Throwable error) {
                                                  Log.e("Database",error.getMessage());

                                              }
                                          }
            );
        }
*/

        /*String h=hr.getText().toString().trim();
        int hr=Integer.parseInt(h);
        String m=mins.getText().toString().trim();
        int min=Integer.parseInt(m);
        String seconds=sec.getText().toString().trim();
        int sec=Integer.parseInt(seconds);

        realm.beginTransaction();
        timer = realm.createObject(Timer.class);
        timer.setTitle(editTitle.getText().toString());
        timer.setHr(hr);
        timer.setMin(min);
        timer.setSec(sec);
        realm.commitTransaction();

        Toast.makeText(Reminder.this,"Alarm Set", Toast.LENGTH_SHORT).show();

*/


    }

    private void setAlarm(long time) {

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(this, MyAlarm.class);
        Log.e("time",time+"eeeeeeeeeeeeeeeeeeeeee");

        //creating a pending intent using the intent
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);

        //setting the repeating alarm that will be fired every day
        //am.setExact(AlarmManager.RTC_WAKEUP,time,pi);
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP,time, AlarmManager.INTERVAL_DAY,pi);

        //am.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pi);
        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Reminder.this, MainActivity.class);
        startActivity(intent);
    }
}





