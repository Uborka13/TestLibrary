package ubi.soft.testlibraries.controllers;

import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.SyncCredentials;
import io.realm.SyncUser;

import static ubi.soft.testlibraries.BuildConfig.BASE_URL;

public class RealmController {

    private static final RealmController instance = new RealmController();
    private Realm realm;

    private RealmController() {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController getInstance() {
        return RealmController.instance;
    }

    public <E extends RealmModel> RealmResults<E> getRealmList(@NonNull Class<E> items) {
        return realm.where(items).findAllAsync();
    }

    public <E extends RealmModel> RealmResults<E> getRealmListSorted(@NonNull Class<E> items, @NonNull String sortBy, Sort sort) {
        return realm.where(items).sort(sortBy, sort).findAllAsync();
    }

    public boolean isUserLoggedIn() {
        return SyncUser.current() != null;
    }

    public static void sendUserLoginCredentials(final SyncCredentials credentials, SyncUser.Callback<SyncUser> listener) {
        SyncUser.logInAsync(credentials, BASE_URL, listener);
    }

}
