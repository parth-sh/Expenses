package com.example.expenses.rv_components;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expenses.R;
import com.example.expenses.data.Entry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Entry> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder0 extends RecyclerView.ViewHolder {
        private static Context context;
        private final TextView created_at, name, cost;
        private SimpleDateFormat simpleDateFormat;

        public ViewHolder0(View view) {
            super(view);
            this.name = itemView.findViewById(R.id.recycler_view_item_tv_entry_name);
            this.cost = itemView.findViewById(R.id.recycler_view_item_tv_entry_cost);
            this.created_at = itemView.findViewById(R.id.recycler_view_item_tv_entry_created_at);
            this.simpleDateFormat = new SimpleDateFormat("dd MMM, h:mm a", Locale.US);
            this.simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        }

        public void bind(int id, String name, int cost, Date created_at) {
            this.name.setText(name);
            this.created_at.setText(this.simpleDateFormat.format(created_at));
            this.cost.setText("- ₹ "+cost);
            if (cost > 1000) {
                this.cost.setTextColor(ContextCompat.getColor(context,R.color.red_A200));
            } else {
                this.cost.setTextColor(ContextCompat.getColor(context,R.color.green_A200));
            }
        }

        static ViewHolder0 create(ViewGroup parent) {
            context = parent.getContext();
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.recycler_view_item, parent, false);
            return new ViewHolder0(view);
        }
    }

    public CustomAdapter(List<Entry> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("DEBUGGING", "ViewHolder0 created");
        return ViewHolder0.create(parent);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("DEBUGGING", "ViewHolder0 binding");
        Entry current = this.localDataSet.get(position);
        ((ViewHolder0) holder).bind(
                current.getId(),
                current.getName(),
                current.getCost(),
                current.getCreated_at()
        );
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.toArray().length;
    }
}
