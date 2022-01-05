package com.example.expenses.rv_components;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expenses.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    private final TextView created_at, name;
    private SimpleDateFormat simpleDateFormat;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.recycler_view_item_tv_entry_name);
        this.created_at = itemView.findViewById(R.id.recycler_view_item_tv_entry_created_at);
        this.simpleDateFormat = new SimpleDateFormat("dd MMM, hh:mm a", Locale.US);
        this.simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
    }

    public void bind(int id, String name, int cost, Date created_at) {
        this.name.setText(name);
        this.created_at.setText(this.simpleDateFormat.format(created_at));
    }

    static CustomViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new CustomViewHolder(view);
    }
}
