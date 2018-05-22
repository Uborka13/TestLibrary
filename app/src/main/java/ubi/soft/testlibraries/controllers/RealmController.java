package ubi.soft.testlibraries.controllers;

import io.realm.Realm;

public class RealmController<T> {

    private static RealmController instance;
    private Realm realm;

    public static RealmController getInstance() {
        if (instance == null) {
            return new RealmController();
        }
        return instance;
    }

    public void setupRealmDefault() {

    }
}
