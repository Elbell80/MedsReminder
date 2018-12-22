package com.example.realmdb.User;

import java.util.HashMap;
import java.util.Map;

public class MyPeople {

    private Users user;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}