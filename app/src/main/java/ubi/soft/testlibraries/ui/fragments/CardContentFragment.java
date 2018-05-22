package ubi.soft.testlibraries.ui.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;
import ubi.soft.testlibraries.BuildConfig;
import ubi.soft.testlibraries.R;
import ubi.soft.testlibraries.items.barsitems.BarsItems;
import ubi.soft.testlibraries.items.barsitems.BarsItemsAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CardContentFragmentListener} interface
 * to handle interaction events.
 * Use the {@link CardContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardContentFragment extends Fragment {

    @BindView(R.id.recycler_view_list_fragment)
    public RecyclerView recyclerView;

    private Realm realm;

    private CardContentFragmentListener mListener;

    public CardContentFragment() {
        // Required empty public constructor
    }

    public static CardContentFragment newInstance(String param1, String param2) {
        return new CardContentFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setUpRealm();

    }

    private void setUpRealm() {
        final SyncConfiguration userConfig = new SyncConfiguration.Builder(SyncUser.current(), BuildConfig.AUTH_URL + "/~/drinks").build();
        Realm.getInstanceAsync(userConfig, new Realm.Callback() {
            @Override
            public void onSuccess(@NonNull Realm realm) {
                setRealm(realm);
                setUpRecyclerView();
                // Realm is now downloaded and ready. New changes to Realm will continue
                // to be synchronized in the background as normal.
            }
        });

    }


    private void setUpRecyclerView() {
        RealmResults<BarsItems> items = getRealmItems();
        final BarsItemsAdapter adapter = new BarsItemsAdapter(items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    public RealmResults<BarsItems> getRealmItems() {
        return realm.where(BarsItems.class).sort("rating", Sort.DESCENDING).findAllAsync();
    }

    void setRealm(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface CardContentFragmentListener {
        void onFragmentInteraction(Uri uri);
    }
}
