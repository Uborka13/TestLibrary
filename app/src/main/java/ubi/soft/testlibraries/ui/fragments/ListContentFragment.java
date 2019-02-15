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
import ubi.soft.testlibraries.R;
import ubi.soft.testlibraries.controllers.RealmController;
import ubi.soft.testlibraries.items.drinksitems.DrinksItems;
import ubi.soft.testlibraries.items.drinksitems.DrinksItemsAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListContentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListContentFragment extends Fragment {
    @BindView(R.id.recycler_view_list_fragment)
    public RecyclerView recyclerView;

    private Realm realm;

    private OnFragmentInteractionListener mListener;

    public ListContentFragment() {
        // Required empty public constructor
    }

    public static ListContentFragment newInstance() {
        return new ListContentFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setUpRecyclerView();

    }

    private void setUpRecyclerView() {
        RealmResults<DrinksItems> items = getRealmItems();
        final DrinksItemsAdapter adapter = new DrinksItemsAdapter(items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
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

    public RealmResults<DrinksItems> getRealmItems() {
        return RealmController.getInstance().getRealmList(DrinksItems.class);
        // realm.where(DrinksItems.class).sort("rating", Sort.DESCENDING).findAllAsync();
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
