package com.example.expenses.rv_components;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expenses.R;
import com.example.expenses.data.Entry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomAdapter extends ListAdapter<Entry, RecyclerView.ViewHolder> {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class EntryViewHolder extends RecyclerView.ViewHolder {
        public static int month = 0;
        //TODO: Remove use of context cause of potential memory leak
        private static Context context;
        private final TextView month_banner,created_at, name, cost;
        private final SimpleDateFormat simpleDateFormat, monthBannerDateFormat;

        public EntryViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.recycler_view_item_tv_entry_name);
            this.cost = itemView.findViewById(R.id.recycler_view_item_tv_entry_cost);
            this.created_at = itemView.findViewById(R.id.recycler_view_item_tv_entry_created_at);
            this.month_banner = itemView.findViewById(R.id.recycler_view_item_tv_month_banner);

            this.simpleDateFormat = new SimpleDateFormat("dd MMM, h:mm a", Locale.US);
            this.monthBannerDateFormat = new SimpleDateFormat("MMMM yyy", Locale.US);
        }

        public void bind(int id, String name, int cost, Date created_at, boolean show_banner) {
            if (show_banner) {
                this.month_banner.setText(this.monthBannerDateFormat.format(created_at));
            } else {
                this.month_banner.setVisibility(View.GONE);
            }
            this.name.setText(name);
            this.created_at.setText(this.simpleDateFormat.format(created_at));
            this.cost.setText("- ₹ "+cost);
            if (cost > 1000) {
                this.cost.setTextColor(ContextCompat.getColor(context,R.color.red_A200));
            } else {
                this.cost.setTextColor(ContextCompat.getColor(context,R.color.green_A200));
            }
        }

        static EntryViewHolder create(ViewGroup parent) {
            context = parent.getContext();
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.recycler_view_item, parent, false);
            return new EntryViewHolder(view);
        }
    }

    public CustomAdapter(@NonNull DiffUtil.ItemCallback<Entry> diffCallback) {
        super(diffCallback);
    }

    static public class EntryDiff extends DiffUtil.ItemCallback<Entry> {

        @Override
        public boolean areItemsTheSame(@NonNull Entry oldItem, @NonNull Entry newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Entry oldItem, @NonNull Entry newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return EntryViewHolder.create(parent);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Entry current = this.getItem(position);
        ((EntryViewHolder) holder).bind(
                current.getId(),
                current.getName(),
                current.getCost(),
                current.getCreated_at(),
                true
        );
    }
}
