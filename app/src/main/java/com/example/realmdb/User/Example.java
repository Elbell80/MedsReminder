package com.example.realmdb.User;

import java.util.HashMap;
import java.util.Map;

public class Example {

    private MyPeople myPeople;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public MyPeople getMyPeople() {
        return myPeople;
    }

    public void setMyPeople(MyPeople myPeople) {
        this.myPeople = myPeople;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
