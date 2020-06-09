package com.example.reminders;


import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private List<RoomReminderItem> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView task;
        public CheckBox checkBox;
        public Button more;
        public Context context;
        public static final String EXTRA_MESSAGE = "com.example.reminders.ID";





        public MyViewHolder(View itemView){
            super(itemView);
            task = (TextView) itemView.findViewById(R.id.title);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            more = (Button) itemView.findViewById(R.id.moreButton);
            context = itemView.getContext();

        }
    }

    public MyAdapter(List<RoomReminderItem> myDataset){
        mDataset = myDataset;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }


    public void setDataset(List<RoomReminderItem> reminders){
        mDataset = reminders;
        notifyDataSetChanged();
    }
    public void onBindViewHolder(final MyViewHolder holder, int position){
                String a = mDataset.get(position).getTitle();
                final int reminderID = mDataset.get(position).getId();
                holder.task.setText(a);
        View.OnClickListener moreListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreClicked(holder.context, reminderID);
            }
        };
        holder.more.setOnClickListener(moreListener);


    }



    @Override
    public int getItemCount(){
        if(mDataset == null){
            return 0;
        }
         return mDataset.size();
    }

    public void moreClicked(Context context, int reminderID){

        Intent intent = new Intent(context, MoreActivity.class);
        intent.putExtra("com.example.reminders.ID", reminderID);
        context.startActivity(intent);
    }



}
