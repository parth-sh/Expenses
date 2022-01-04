package com.example.expenses.rv_components;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.expenses.data.Entry;

public class CustomListAdapter extends ListAdapter<Entry, CustomViewHolder> {
    public CustomListAdapter(@NonNull DiffUtil.ItemCallback<Entry> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CustomViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Entry current = this.getItem(position);
        holder.bind(
                current.getId(),
                current.getName(),
                current.getCost()
        );
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
}
