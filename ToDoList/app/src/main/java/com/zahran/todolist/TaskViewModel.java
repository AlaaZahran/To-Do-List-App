package com.zahran.todolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository repository;
    private LiveData<List<Tasks>> allList;
    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository=new TaskRepository(application);
        allList=repository.getAll();
    }
    public void insert(Tasks task){
        repository.insert(task);
    }
    public void delete(Tasks task){
        repository.delete(task);
    }
    public void update(Tasks task){
        repository.update(task);
    }
    public void deleteAll(){
        repository.deleteAll();
    }
    public LiveData<List<Tasks>> getAll(){
       return allList;
    }
}
