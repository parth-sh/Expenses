package com.example.expenses.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class EntryRepository {
    private LedgerDao ledgerDao;
    private LiveData<List<Entry>> entriesList;
    private LiveData<Integer> entriesCost;

    public EntryRepository(Application application) {
        ExpensesRoomDatabase db = ExpensesRoomDatabase.getDatabase(application);
        ledgerDao = db.ledgerDao();
        entriesList = ledgerDao.getEntriesList();
        entriesCost = ledgerDao.getEntriesCost();
    }

    public LiveData<List<Entry>> getEntriesList() {
        return entriesList;
    }

    public LiveData<Integer> getEntriesCost() {
        return entriesCost;
    }

    public void insert(Entry entry) {
        ExpensesRoomDatabase.databaseWriteExecutor.execute(() -> {
            ledgerDao.insert(entry);
        });
    }
}
