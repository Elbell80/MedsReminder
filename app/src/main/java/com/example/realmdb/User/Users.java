package com.example.realmdb.User;

import com.example.realmdb.Medicines;

import java.util.HashMap;
import java.util.Map;

public class Users {

    private String email;
    private String id;
    private Medicines medicines;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Medicines getMedicines() {
        return medicines;
    }

    public void setMedicines(Medicines medicines) {
        this.medicines = medicines;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}