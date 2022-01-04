package com.example.expenses.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Entry.class}, version = 1, exportSchema = false)
public abstract class ExpensesRoomDatabase extends RoomDatabase {
    public abstract LedgerDao ledgerDao();

    private static volatile ExpensesRoomDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ExpensesRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ExpensesRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ExpensesRoomDatabase.class,
                            "expenses.sqlite"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
