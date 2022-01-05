package com.example.expenses.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "ledger")
public class Entry {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private int cost;

    @TypeConverters({DateConverter.class})
    private Date created_at;

    public Entry(String name, int cost, Date created_at) {
        this.name = name;
        this.cost = cost;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public Date getCreated_at() {
        return this.created_at;
    }
}
