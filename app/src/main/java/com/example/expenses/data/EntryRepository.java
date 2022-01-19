package com.example.expenses.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class EntryRepository {
    private LedgerDao ledgerDao;
    private LiveData<List<Entry>> entriesListObserver;
//    private List<Entry> initialEntriesList;
    private LiveData<Integer> entriesCostObserver;

    public EntryRepository(Application application) {
        ExpensesRoomDatabase db = ExpensesRoomDatabase.getDatabase(application);
        ledgerDao = db.ledgerDao();
        entriesListObserver = ledgerDao.getEntriesListObserver();
        entriesCostObserver = ledgerDao.getEntriesCostObserver();
//        initialEntriesList = ledgerDao.getInitialEntriesList();
    }

    public LiveData<List<Entry>> getEntriesListObserver() {
        return entriesListObserver;
    }

    public LiveData<Integer> getEntriesCostObserver() {
        return entriesCostObserver;
    }

//    public List<Entry> getInitialEntriesList() {
//        return initialEntriesList;
//    }

    public void insert(Entry entry) {
        ExpensesRoomDatabase.databaseWriteExecutor.execute(() -> {
            ledgerDao.insert(entry);
        });
    }

    public void deleteAll() {
        ExpensesRoomDatabase.databaseWriteExecutor.execute(() -> {
            ledgerDao.deleteAll();
        });
    }
}
