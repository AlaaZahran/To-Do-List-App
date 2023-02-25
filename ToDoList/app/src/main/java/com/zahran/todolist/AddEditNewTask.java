package com.zahran.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNewTask extends AppCompatActivity {
    EditText etAdd;
    Button btnAdd;
    NumberPicker numberPicker;
    public  static  final  String EXTRA_ID="com.zahran.todolist.EXTRA_ID";

    public  static  final  String EXTRA_TASK="com.zahran.todolist.EXTRA_TASK";
    public  static  final  String EXTRA_PRIORITY="com.zahran.todolist.EXTRA_PRIORITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);
        etAdd=findViewById(R.id.et_new_task);
        btnAdd=findViewById(R.id.btn_add);
        numberPicker=findViewById(R.id.num_pick);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);


        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String task=etAdd.getText().toString().trim();
                int priority=numberPicker.getValue();
                if(!task.isEmpty() ){
                    Intent data = new Intent();
                    data.putExtra(EXTRA_TASK, task);
                    data.putExtra(EXTRA_PRIORITY,priority);
                    int id=getIntent().getIntExtra(EXTRA_ID,-1);
                        data.putExtra(EXTRA_ID,id);
                    setResult(RESULT_OK, data);
                    finish();
                }
               if(task.isEmpty()) {
                    Toast.makeText(AddEditNewTask.this,"Please fill this field",Toast.LENGTH_SHORT).show();
                    return;

                }
            }
        });
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        Intent intent=getIntent();
        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Task");
            etAdd.setText(intent.getStringExtra(EXTRA_TASK));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
        }

        else {setTitle("Add New Task");}



    }
}