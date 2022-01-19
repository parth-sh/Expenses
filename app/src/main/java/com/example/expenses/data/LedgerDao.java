package com.example.expenses.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface LedgerDao {
    @Insert
    void insert(Entry entry);

    @Query("DELETE FROM ledger")
    void deleteAll();

    @Query("SELECT SUM(cost) FROM ledger")
    LiveData<Integer> getEntriesCostObserver();

    @Query("SELECT * FROM ledger ORDER BY id DESC")
    LiveData<List<Entry>> getEntriesListObserver();

//    @Query("SELECT * FROM ledger ORDER BY id DESC")
//    List<Entry> getInitialEntriesList();
}
