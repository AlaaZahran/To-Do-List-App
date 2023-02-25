package com.zahran.todolist;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{

    List<Tasks> taskList;
    ClickListener listener;



    public void setListener(ClickListener listener) {
        this.listener = listener;
    }


public TaskAdapter(){}

    public void setTaskList(List<Tasks> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }
    public Tasks getPosition(int position){
    return taskList.get(position);
    }

    @NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tasks,parent,false);
        ViewHolder viewhold=new ViewHolder(v);
        return viewhold;
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tasks task= taskList.get(position);
        holder.txt_newTask.setText(task.getTask());
        holder.txtPriority.setText(String.valueOf(task.getPriority()));
        holder.checkBox.setChecked(task.getState());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if(isChecked)
            {
                holder.txt_newTask.setPaintFlags(holder.checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.txt_newTask.setTextColor(Color.parseColor("#62656D"));

            }
            else {

                holder.checkBox.setChecked(false);

                holder.txt_newTask.setPaintFlags(holder.checkBox.getPaintFlags()&(~Paint.STRIKE_THRU_TEXT_FLAG));
                holder.txt_newTask.setTextColor(Color.parseColor("#000000"));

            }

        }
    }
);




 }


@Override
public int getItemCount() {
    if(taskList==null)
        return 0;

        return taskList.size();
        }

class ViewHolder extends RecyclerView.ViewHolder{
    CheckBox checkBox;
   TextView txt_newTask,txtPriority;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        checkBox=itemView.findViewById(R.id.check_box);
        txt_newTask=itemView.findViewById(R.id.txt_new_task);
        txtPriority=itemView.findViewById(R.id.txt_priority);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int postion=getAdapterPosition();

                listener.onTaskCliked(taskList.get(postion));
            }
        });

    }
}
    public interface ClickListener{
        void onTaskCliked(Tasks tasks);
    }

}





























//    holder.checkBox.setOnClickListener(new View.OnClickListener() {
//          @Override
//        public void onClick(View v) {
////            int fromPosition =position;
////            int toPosition = taskList.size()-1;
//            if(holder.checkBox.isChecked())
//            {   state=true;
//                holder.checkBox.setChecked(true);
//                holder.txt_newTask.setPaintFlags(holder.checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//                holder.txt_newTask.setTextColor(Color.parseColor("#62656D"));
//
////                Log.d(TAG,"postion is : "+position);
////                Log.d(TAG,"from postion is : "+fromPosition);
////                Log.d(TAG,"to postion is : "+toPosition);
////                Tasks item = taskList.get(fromPosition);
////                taskList.remove(fromPosition);
////                taskList.add(toPosition, item);
////                notifyItemMoved(fromPosition, toPosition);
//
////            notifyDataSetChanged();
//            }
//            else {
//                state=false;
//                holder.checkBox.setChecked(false);
//
//                holder.txt_newTask.setPaintFlags(holder.checkBox.getPaintFlags()&(~Paint.STRIKE_THRU_TEXT_FLAG));
//                holder.txt_newTask.setTextColor(Color.parseColor("#000000"));
//
//            }
//        }
//    });