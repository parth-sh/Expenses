package com.example.expenses.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.expenses.data.Entry;
import com.example.expenses.data.EntryRepository;

import java.util.List;

public class EntryViewModel extends AndroidViewModel {

    private EntryRepository entryRepository;
    private final LiveData<List<Entry>> entriesListObserver;
//    private final List<Entry> initialEntriesList;
    private LiveData<Integer> entriesCostObserver;

    public EntryViewModel(@NonNull Application application) {
        super(application);
        entryRepository = new EntryRepository(application);
        entriesListObserver = entryRepository.getEntriesListObserver();
        entriesCostObserver = entryRepository.getEntriesCostObserver();
//        initialEntriesList = entryRepository.getInitialEntriesList();
    }

    public LiveData<List<Entry>> getEntriesListObserver() {
        return entriesListObserver;
    }

//    public List<Entry> getInitialEntriesList() {
//        return initialEntriesList;
//    }

    public LiveData<Integer> getEntriesCostObserver() {
        return entriesCostObserver;
    }

    public void insert(Entry entry) {
        entryRepository.insert(entry);
    }

    public void deleteAll() {
        entryRepository.deleteAll();
    }
}
