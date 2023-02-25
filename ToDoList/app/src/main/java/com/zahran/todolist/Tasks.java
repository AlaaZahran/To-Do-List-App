package com.zahran.todolist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class Tasks {
    @PrimaryKey(autoGenerate = true)
    private int id;
     private String task;
      private boolean state;
      private  int priority;

    public int getPriority() {
        return priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void setState(boolean state) {
        state = state;
    }
    public boolean getState() {
        return state;
    }

    public Tasks(String task, boolean state,int priority) {
        this.task = task;
        this.state = state;
        this.priority=priority;
    }

    public String getTask() {
            return task;
        }


}
