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
    private final LiveData<List<Entry>> entriesList;
    private LiveData<Integer> entriesCost;

    public EntryViewModel(@NonNull Application application) {
        super(application);
        entryRepository = new EntryRepository(application);
        entriesList = entryRepository.getEntriesList();
        entriesCost = entryRepository.getEntriesCost();
    }

    public LiveData<List<Entry>> getEntriesList() {
        return entriesList;
    }

    public LiveData<Integer> getEntriesCost() {
        return entriesCost;
    }

    public void insert(Entry entry) {
        entryRepository.insert(entry);
    }

    public void deleteAll() {
        entryRepository.deleteAll();
    }
}
