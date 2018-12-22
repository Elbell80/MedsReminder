package com.example.realmdb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.realmdb.User.MedsAdapter;
import com.example.realmdb.User.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.Realm;

public class medslist extends AppCompatActivity {
    private FloatingActionButton fab;
    Realm realm;
    RecyclerView recyclerView1;
    MedsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Person> people;
    private DatabaseReference mDatabase;
    ValueEventListener mPostListener;
    EditText editTextName,editdosage, editdesc,editHr, editMins, mynewEdittext;
    ImageButton updatebtn,delbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medslist);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        recyclerView1= (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView1.setHasFixedSize(true);
        recyclerView1.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        layoutManager=new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(layoutManager);
        updatebtn=(ImageButton) findViewById(R.id.updatebtn);
        delbtn=(ImageButton) findViewById(R.id.del);
        mDatabase = FirebaseDatabase.getInstance().getReference("persons");



        /*RealmConfiguration configuration= new RealmConfiguration.Builder().build();
        realm= Realm.getInstance(configuration);
*/
        /*realm = Realm.getDefaultInstance();
        medlist=new ArrayList<>();
        //RealmResults<Person> guests = realm.where(Person.class).findAll();
        medlist= realm.where(Person.class).findAll();
*/

        fab= (FloatingActionButton) findViewById(R.id.btnfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(medslist.this, MainActivity.class);
                i.putExtra("isEdit",0);
                startActivity(i);
            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                //iterating through all the nodes
                people = new ArrayList<Person>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    Person post = postSnapshot.getValue(Person.class);

                    people.add(post);
                    adapter= new MedsAdapter(medslist.this, people);
                    recyclerView1.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    recyclerView1.setPadding(10,10,10,10);

                    //adding artist to the list
                    //persons.add(post);
                }




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("Main", "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(medslist.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        mDatabase.addValueEventListener(postListener);
        // [END post_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mPostListener = postListener;
    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (mPostListener != null) {
            mDatabase.removeEventListener(mPostListener);
        }

    }




}
