package com.example.realmdb.User;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

import io.realm.RealmObject;


public class Person extends RealmObject {

    String id;
    String name;
    int dosage;
    String timing;
    String date,time, newtime;



    public Person() {
    }

    public Person(String id, String name, int dosage, String timing, String date, String time, String newtime) {
        this.id=id;
        this.name = name;
        this.dosage = dosage;
        this.timing = timing;
        this.date= date;
        this.time=time;
        this.newtime=newtime;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("user_id", id);
        result.put("name", name);
        result.put("dosage", dosage);
        result.put("timing", timing);
        result.put("date",date);
        result.put("time", time);


        return result;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewtime() {
        return newtime;
    }

    public void setNewtime(String newtime) {
        this.newtime = newtime;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public int getDosage() {

        return dosage;
    }

    public String getTiming() {

        return timing;
    }

    public void setTiming(String timing) {

        this.timing = timing;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

 /*   public String getHr() {
        return date;
    }

    public void setHr(String date) {
        this.date = date;
    }

    public String getMins() {
        return time;
    }

    public void setMins(String time) {
        this.time = time;
    }

*/



    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +"Dosage:" + dosage+ "timing:" + timing +
                '}';
    }
}