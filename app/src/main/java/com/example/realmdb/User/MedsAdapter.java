package com.example.realmdb.User;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.realmdb.MainActivity;
import com.example.realmdb.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MedsAdapter extends RecyclerView.Adapter<MedsAdapter.MyViewHolder>{
    List<Person> people;
    Context context;
    private DatabaseReference mDatabase;

    public MedsAdapter(Context context, List<Person> people) {
        this.context=context;
        this.people = people;
    }

    @NonNull
    @Override
    public MedsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mDatabase = FirebaseDatabase.getInstance().getReference("mypeople");
        //TextView view= (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_medslist,parent,false);
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main,parent,false);
        //view.setOnLongClickListener(mOnclickListener);
        MyViewHolder vh= new MyViewHolder(view);
        //return new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Person person = people.get(position);
        holder.name.setText(person.getName());
        holder.dosage.setText(String.valueOf(person.getDosage()));
        holder.timing.setText(person.getTiming());
        holder.hour.setText(String.valueOf(person.getDate()));
        holder.mins.setText(String.valueOf(person.getTime()));

    }

    @Override
    public int getItemCount() {
        //
        // return medlist.size();
        int arr = 0;

        try{
            if(people.size()==0){

                arr = 0;

            }
            else{

                arr= people.size();
            }



        }catch (Exception e){



        }

        return arr;

    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,dosage,timing, hour, mins,col;
        ImageButton updatebtn,delbtn;

        public MyViewHolder(View itemView){
            super(itemView);
            //Log.v("id",String.valueOf(getAdapterPosition()));

            name= itemView.findViewById(R.id.txtmeds);
            dosage=itemView.findViewById(R.id.medosage);
            timing=itemView.findViewById(R.id.meddesc);
            hour=itemView.findViewById(R.id.timepickerhr);
            //col=itemView.findViewById(R.id.colon);
            mins=itemView.findViewById(R.id.timepickermins);
            updatebtn=itemView.findViewById(R.id.updatebtn);
            delbtn=itemView.findViewById(R.id.del);
            delbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("id", people.get(getAdapterPosition()).getId());
                    deleteRecord(people.get(getAdapterPosition()).getId());
                }
            });
            updatebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i= new Intent(context, MainActivity.class);
                    i.putExtra("isEdit",1);
                    i.putExtra("id", people.get(getAdapterPosition()).getId());
                    i.putExtra("name1", people.get(getAdapterPosition()).getName());
                    i.putExtra("dosage1", people.get(getAdapterPosition()).getDosage());
                    i.putExtra("description1", people.get(getAdapterPosition()).getTiming());
                    i.putExtra("hr", people.get(getAdapterPosition()).getDate());
                    i.putExtra("mins", people.get(getAdapterPosition()).getTime());
                    context.startActivity(i);



                }
            });


        }
        public boolean deleteRecord(String id){
            mDatabase = FirebaseDatabase.getInstance().getReference("persons").child(people.get(getAdapterPosition()).getId());
            mDatabase.removeValue();
            return true;
        }
    }



}
