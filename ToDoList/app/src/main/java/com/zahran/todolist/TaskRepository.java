package com.zahran.todolist;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {
    private  Dao taskDaoList;
    private LiveData<List<Tasks>>getAll;
    public TaskRepository(Application app){
      TaskRoomDataBase db= TaskRoomDataBase.getInstance(app);
      taskDaoList =db.taskDao();
      getAll= taskDaoList.getAll();
    }
    public void insert(Tasks task){
        new InsertAsyncTask(taskDaoList).execute(task);

    } public void update(Tasks task){
        new UpdateAsyncTask(taskDaoList).execute(task);


    } public void delete(Tasks task){
        new DeleteAsyncTask(taskDaoList).execute(task);
    }

    public LiveData<List<Tasks>> getAll() {
        return getAll;
    }
    public void deleteAll(){
        new DeleteAllAsyncTask(taskDaoList).execute();

    }
public static class InsertAsyncTask extends AsyncTask<Tasks,Void,Void>{
        private Dao dao;
        public InsertAsyncTask(Dao mDao){
            dao=mDao;
        }
    @Override
    protected Void doInBackground(Tasks... tasks) {
            dao.insert(tasks[0]);
        return null;
    }
}
    public static class DeleteAsyncTask extends AsyncTask<Tasks,Void,Void>{
        private Dao dao;
        public DeleteAsyncTask(Dao mDao){
            dao=mDao;
        }
        @Override
        protected Void doInBackground(Tasks... tasks) {
            dao.delete(tasks[0]);
            return null;
        }
    }

    public static class UpdateAsyncTask extends AsyncTask<Tasks,Void,Void>{
        private Dao dao;
        public UpdateAsyncTask(Dao mDao){
            dao=mDao;
        }
        @Override
        protected Void doInBackground(Tasks... tasks) {
            dao.update(tasks[0]);
            return null;
        }
    }
    public static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private Dao dao;
        public DeleteAllAsyncTask(Dao mDao){
            dao=mDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();
            return null;
        }
    }
}
