package ubi.soft.testlibraries.items.barsitems;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import ubi.soft.testlibraries.R;

public class BarsItemsAdapter extends RealmRecyclerViewAdapter<BarsItems, BarsItemsAdapter.BarsViewHolder> {


    public BarsItemsAdapter(@Nullable OrderedRealmCollection<BarsItems> data) {
        super(data, true);
    }

    @NonNull
    @Override
    public BarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bars_list_item, parent, false);
        return new BarsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarsViewHolder holder, int position) {
        final BarsItems item = getItem(position);
        holder.setItem(item);
    }

    protected static class BarsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_name_bars_item)
        public TextView name;
        BarsItems item;

        BarsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setItem(BarsItems item) {
            this.item = item;
            this.name.setText(item.getName());
        }
    }
}
