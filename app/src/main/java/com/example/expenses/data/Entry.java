package com.example.expenses.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ledger")
public class Entry {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;

    @NonNull
    private int cost;

    public Entry(@NonNull String name, @NonNull int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
