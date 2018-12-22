package com.example.realmdb;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

class MyMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

    }
}
