package com.example.expenses.rv_components;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expenses.R;

public class CustomViewHolder extends RecyclerView.ViewHolder{

    private final TextView cost;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        this.cost = itemView.findViewById(R.id.textView);
    }

    public void bind (String text, String cost) {
        this.cost.setText(cost+"-->"+text);
    }

    static CustomViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item,parent,false);
        return new CustomViewHolder(view);
    }
}
