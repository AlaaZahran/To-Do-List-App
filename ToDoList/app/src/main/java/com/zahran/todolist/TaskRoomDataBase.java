package com.zahran.todolist;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Tasks.class,version = 1)
public abstract class TaskRoomDataBase extends RoomDatabase {
    private static TaskRoomDataBase instance;
    public abstract Dao taskDao();

    public static  synchronized TaskRoomDataBase getInstance(Context context){
        if(instance== null){
            instance= Room.databaseBuilder(context.getApplicationContext(), TaskRoomDataBase.class,"task-dataBase").fallbackToDestructiveMigration().addCallback(roomCallBack).build();

        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallBack=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDataAsyncTask(instance).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
    private static class PopulateDataAsyncTask extends AsyncTask<Void,Void,Void> {
        private Dao dao;
        PopulateDataAsyncTask(TaskRoomDataBase db){
            dao=db.taskDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
