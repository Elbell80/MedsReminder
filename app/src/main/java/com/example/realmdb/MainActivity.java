package com.example.realmdb;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.realmdb.User.MyPeople;
import com.example.realmdb.User.Person;
import com.example.realmdb.User.Users;
import com.example.realmdb.User.usersname;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import io.realm.Realm;

//import com.example.realmdb.User.MyRealmObject;

public class MainActivity extends AppCompatActivity {

    private TextView enterNam, enterdosage, enterdescription;
    private EditText nameof_Medicine;
    private EditText dosage;
    private EditText timing, editdate, edittime;
    private TextView textView_log;
    private Button btn_save;
    static EditText myEditTextView;
    //private MainActivity mContext;
    private Realm realm;
    TimePicker timePicker;
    private DatabaseReference mDatabase;
    ValueEventListener mPostListener;
    Intent intent2;
    List<Person> people;
    List<EditText> edittext_list = new ArrayList<EditText>();

    Bundle bundle;
    int isEdit, dosages1;
    Spinner myspinner, myspinner2;
    TextView addTime;
    LayoutInflater layoutInflater;
    LinearLayout mlayout;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        enterNam = (TextView) findViewById(R.id.enterName);
        nameof_Medicine = (EditText) findViewById(R.id.FullName);
        dosage = (EditText) findViewById(R.id.editAge);
        enterdosage = (TextView) findViewById(R.id.enterAge);
        timing = (EditText) findViewById(R.id.description);
        enterdescription = (TextView) findViewById(R.id.enterWeight);
        editdate = (EditText) findViewById(R.id.editdate);
        edittime = (EditText) findViewById(R.id.editTime);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        //textView_log = (TextView) findViewById(R.id.textView_log);
        btn_save = (Button) findViewById(R.id.btn_save);
        myspinner = (Spinner) findViewById(R.id.spinner);
        addTime=(TextView) findViewById(R.id.addtime);
        mlayout = (LinearLayout) findViewById(R.id.addnewlinear);
        myEditTextView = new EditText(this);


        myspinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> myadap = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.medtype));
        myspinner2.setAdapter(myadap);

        realm = Realm.getDefaultInstance();


        edittime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AlertDialog.Builder dialogBuilder= new AlertDialog.Builder(MainActivity.this);
                LayoutInflater layoutInflater=MainActivity.this.getLayoutInflater();
                view = layoutInflater.inflate(R.layout.activity_time_selection, null);
                //dialogBuilder.setView(view);
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Enter Time");
                alertDialog.setCancelable(true);



                alertDialog.setView(mlayout);
                alertDialog.show();


            }
        });

       addTime.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mlayout.addView(createNewEditTextView());
           }
       });
        editdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        editdate.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();

            }
        });
        /*myEditTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View V){
                //showTimeDialog();
                Log.e("myedittext","bhayo");
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        myEditTextView.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }

        });*/

       /* edittime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //showTimeDialog();
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        edittime.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });*/
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitDetails();

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        database.setPersistenceEnabled(true);
        mDatabase = database.getReference();


        intent2 = getIntent();
        bundle = intent2.getExtras();
        isEdit = bundle.getInt("isEdit");
        if (isEdit == 1) {
            String name1 = bundle.getString("name1");
            int dosage1 = bundle.getInt("dosage1");
            String description1 = bundle.getString("description1");
            String hr1 = bundle.getString("hr");
            String mins1 = bundle.getString("mins");

            nameof_Medicine.setText(name1);
            dosage.setText(String.valueOf(dosage1));
            timing.setText(description1);

        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_layout, menu);
        return super.onCreateOptionsMenu(menu);

    }

    public void showTimeDialog() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                edittime.setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }


    /*//String a1= new Integer(22).toString().trim();
        if (nameof_Medicine.length()==0){
            Toast.makeText(MainActivity.this,"Enter the name of the medicine", Toast.LENGTH_SHORT).show();
        }else if(dosage.length()==0){
            Toast.makeText(MainActivity.this,"Enter dosage", Toast.LENGTH_SHORT).show();
        }else if (timing.length()==0){
            Toast.makeText(MainActivity.this,"Enter frequency", Toast.LENGTH_SHORT).show();
        }else {
            String b = dosage.getText().toString().trim();
            int a = Integer.parseInt(b);
            String c = timing.getText().toString().trim();
            //int d = Integer.parseInt(c);
            String s = nameof_Medicine.getText().toString().trim();

            writeToDB(s,a,c);
            //showdata();
            Intent intent = new Intent(MainActivity.this, medslist.class);
            startActivity(intent);
            finish();
        }*/
        /*public void showdata(){
            RealmResults<Person> guests = realm.where(Person.class).findAll();

            String op=" ";
            realm.beginTransaction();

            for (Person guest: guests) {
                op+= guest.toString();
            }
            textView_log.setText(op);

            Log.e("database", op);
            realm.commitTransaction();
        }
*/
    public void submitDetails() {
        String name = nameof_Medicine.getText().toString();
        String dosages = dosage.getText().toString();
        String description = timing.getText().toString();
        //String spin1=myspinner.getSelectedItem().toString();
        String spin2 = myspinner2.getSelectedItem().toString();
        String date = editdate.getText().toString();
        String time = edittime.getText().toString();
        String newtime = myEditTextView.getText().toString();
        if (name.equalsIgnoreCase("")) {
            Toast.makeText(MainActivity.this, "Please enter the name of medicine", Toast.LENGTH_LONG).show();
            return;

        }
        if (TextUtils.isEmpty(description)) {
            Toast.makeText(MainActivity.this, "Please enter the description", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(dosages)) {
            Toast.makeText(MainActivity.this, "Please enter the number of dosages", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(date)) {
            Toast.makeText(MainActivity.this, "Please select the date", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(time)) {
            Toast.makeText(MainActivity.this, "Please select time", Toast.LENGTH_LONG).show();
            return;
        }
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String email = user.getEmail();
        String uid = user.getUid();
        //var cleanEmail = email.replace(/\./g, ',');
        String encodedEmail = encodeEmailid(email);
        String decodeEmail = decodeEmail(encodedEmail);


        if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(dosages) || !TextUtils.isEmpty(description) || !TextUtils.isEmpty(date) || !TextUtils.isEmpty(time)) {

            if (isEdit == 0) {
                String id = mDatabase.push().getKey();


                Medicines med = new Medicines();
               /* Medicines med1=new Medicines();

                med1.setId("asa");
                med1.setName("paraaaa");
                med1.setDescription(description);
                med1.setDosage(dosages);
                med1.setStartDate(date);
*/
                med.setId(id);
                med.setName(name);
                med.setDescription(description);
                med.setDosage(dosages);
                med.setStartDate(date);
                med.setMedicineType(spin2);
                //med.setTime();
                //med.setMedicineType();

                Users users = new Users();
                users.setEmail(email);
                users.setMedicines(med);

                users.setId(uid);

                MyPeople myPeople = new MyPeople();
                myPeople.setUser(users);

                //mDatabase.child("mypeople").child("user").setValue(users);

                mDatabase.child("mypeople").child("user").child("medicines").child(id).setValue(med);
                mDatabase.child("mypeople").child("user").child("email").setValue(email);
                //mDatabase.child("mypeople").setValue(myPeople);

                /*Person details = new Person(id, name, Integer.parseInt(dosages), description,date,time,newtime);
                mDatabase.child("persons").child(id).setValue(details);*/
            } else {

                String id = bundle.getString("id");


                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("persons");
                Person editdetails = new Person(id, name, Integer.parseInt(dosages), description, date, time, newtime);
                Map<String, Object> postValues = editdetails.toMap();
                mDatabase.child(id).updateChildren(postValues);
            }
            /*nameof_Medicine.setText("");
            dosage.setText("");
            timing.setText("");*/
        }


        Log.v("hr", String.valueOf(hour));
        Log.v("mins", String.valueOf(minute));


        /*if (android.os.Build.VERSION.SDK_INT >= 23) {
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                    timePicker.getHour(), timePicker.getMinute(), 0);
        } else {
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                    timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);
        }*/

        setAlarm(calendar.getTimeInMillis());

        // Disable button so there are no multi-posts
        setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();

    }


    public void setEditingEnabled(boolean enabled) {
        enterNam.setEnabled(enabled);
        enterdosage.setEnabled(enabled);
        enterdescription.setEnabled(enabled);
        if (enabled) {
            btn_save.setVisibility(View.GONE);
        } else {
            btn_save.setVisibility(View.VISIBLE);
        }


    }

    static String encodeEmailid(String email) {
        return email.replace(".", ",");
    }


    static String decodeEmail(String email) {
        return email.replace(",", ".");

    }

        /*public void writeToDB(final String name,final int dosage, final String timing){
            realm.executeTransactionAsync(new Realm.Transaction() {
                                              @Override
                                              public void execute(Realm bgRealm) {
                                                  Person user = bgRealm.createObject(Person.class);
                                                  user.setName(name);
                                                  user.setDosage(dosage);
                                                  user.setTiming(timing);
                                                  Log.v("dosage", Integer.toString(dosage));
                                                  Log.v("desc", timing);
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
        }*/


    private void setAlarm(long time) {

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(this, MyAlarm.class);
        Log.e("time", time + "eeeeeeeeeeeeeeeeeeeeee");

        //creating a pending intent using the intent
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);

        //setting the repeating alarm that will be fired every day
        //am.setExact(AlarmManager.RTC_WAKEUP,time,pi);
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pi);

        //am.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pi);
        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, medslist.class);
        startActivity(intent);
    }

    private EditText createNewEditTextView() {
        for (int j = 0; j <= 12; j++) {
            myEditTextView = new EditText(this);
            edittext_list.add(myEditTextView);
            myEditTextView.setId(j);

        }
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        //EditText myEditTextView= new EditText(this);
        myEditTextView.setLayoutParams(lparams);
        lparams.setMargins(10, 5, 10, 0);
        myEditTextView.setPadding(15, 15, 15, 15);
        myEditTextView.setBackground(getDrawable(R.drawable.edittext_rounded));
        myEditTextView.setHint("Pick time");
        myEditTextView.setOnClickListener(edittextListener);
        return myEditTextView;

    }

    private View.OnClickListener edittextListener = new View.OnClickListener() {
        public void onClick(View V) {
            Log.e("myedittext", "bhayo");
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    Toast.makeText(MainActivity.this, "Enter the name of the medicine" + selectedHour + selectedMinute, Toast.LENGTH_SHORT).show();
                    myEditTextView.setText(selectedHour + ":" + selectedMinute);

                }
            }, hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();

        }
    };
}
