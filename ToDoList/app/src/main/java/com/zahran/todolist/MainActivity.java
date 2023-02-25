 package com.zahran.todolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TaskAdapter adapter;
    RecyclerView rvToDoList;
    FloatingActionButton fabAdd;
    TaskViewModel viewModel;
    boolean state=false;
    String editTask;
    String addTask;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox=findViewById(R.id.check_box);
        fabAdd=findViewById(R.id.fab_add);
        rvToDoList=findViewById(R.id.rv_cards);
        rvToDoList.setLayoutManager(new LinearLayoutManager(this));
        rvToDoList.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TaskViewModel.class);
        adapter=new TaskAdapter();
     adapter.setListener(new TaskAdapter.ClickListener() {
         @Override
         public void onTaskCliked(Tasks tasks) {
             Intent i=new Intent(MainActivity.this, AddEditNewTask.class);
             i.putExtra(AddEditNewTask.EXTRA_ID,tasks.getId());
             i.putExtra(AddEditNewTask.EXTRA_TASK,tasks.getTask());
             startActivityForResult(i,200);
         }
     });
        rvToDoList.setAdapter(adapter);


        viewModel.getAll().observe(this, new Observer<List<Tasks>>() {
            @Override
            public void onChanged(List<Tasks> tasks) {
               adapter.setTaskList(tasks);
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, AddEditNewTask.class);
                startActivityForResult(intent,100);
            }
        });



new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int postion =viewHolder.getAdapterPosition();

            viewModel.delete(adapter.getPosition(postion));

    }

    }).attachToRecyclerView(rvToDoList);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
              addTask = data.getStringExtra(AddEditNewTask.EXTRA_TASK);
              int priority=data.getIntExtra(AddEditNewTask.EXTRA_PRIORITY,1);
                viewModel.insert(new Tasks(addTask,state,priority));

            }
        }
        if (requestCode == 200) {
            if (resultCode == RESULT_OK) {
                editTask=data.getStringExtra(AddEditNewTask.EXTRA_TASK);
                int priority=data.getIntExtra(AddEditNewTask.EXTRA_PRIORITY,1);
                int ID =data.getIntExtra(AddEditNewTask.EXTRA_ID ,-1);
                Tasks tasks=new Tasks(editTask,state,priority);
                tasks.setId(ID);
                viewModel.update(tasks);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater x=getMenuInflater();
        x.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_tasks:
                viewModel.deleteAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);}
    }

}