package ubi.soft.testlibraries.controllers;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;
import ubi.soft.testlibraries.BuildConfig;

public class RealmController {

    private static RealmController instance;
    private Realm realm;

    public static RealmController getInstance() {
        if (instance == null) {
            return new RealmController();
        }
        return instance;
    }

    RealmController() {
        final SyncConfiguration userConfig = new SyncConfiguration.Builder(SyncUser.current(), BuildConfig.BASE_URL + "/~/drinks").build();
        realm = Realm.getInstance(userConfig);
        //realm = Realm.getDefaultInstance();
    }

    public void setupRealmDefault() {

    }


    public <E extends RealmModel> RealmResults<E> getRealmList(Class<E> items) {
        return realm.where(items).findAllAsync();
    }
}
