package ubi.soft.testlibraries.items.drinksitems;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import ubi.soft.testlibraries.R;
import ubi.soft.testlibraries.ui.activities.DetailActivity;

public class DrinksItemsAdapter extends RealmRecyclerViewAdapter<DrinksItems, DrinksItemsAdapter.DrinksViewHolder> {

    public DrinksItemsAdapter(@Nullable OrderedRealmCollection<DrinksItems> data) {
        super(data, true);
    }

    @NonNull
    @Override
    public DrinksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drinks_list_item, parent, false);
        return new DrinksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinksViewHolder holder, int position) {
        final DrinksItems item = getItem(position);
        holder.setItem(item);
    }

    protected static class DrinksViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_name_list_item)
        public TextView name;
        @BindView(R.id.rating_bar_list_item)
        public RatingBar ratingBar;
        DrinksItems item;

        DrinksViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    context.startActivity(intent);
                }
            });
        }

        void setItem(DrinksItems item) {
            this.item = item;
            this.name.setText(item.getName());
            this.ratingBar.setRating(item.getRating());
        }


    }
}
