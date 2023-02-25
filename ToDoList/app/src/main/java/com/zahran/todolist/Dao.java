package com.zahran.todolist;
import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Insert
    void insert(Tasks to_do);
    @Update
    void update(Tasks to_do);
    @Delete
    void delete (Tasks to_do);
    @Query("DELETE From Tasks")
    void deleteAll();
    @Query("SELECT * From Tasks")
    LiveData<List<Tasks>> getAll();

}
