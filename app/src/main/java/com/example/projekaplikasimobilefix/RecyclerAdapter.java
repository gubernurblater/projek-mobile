package com.example.projekaplikasimobilefix;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projekaplikasimobilefix.ui.Data;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Data> arrayList = new ArrayList<>();

    public RecyclerAdapter(ArrayList<Data> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.id.setText(arrayList.get(position).getId());
        holder.username.setText(arrayList.get(position).getUsername());
        holder.jadwal.setText(arrayList.get(position).getJadwal());
        holder.status.setText(arrayList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return  arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id, username,jadwal,status;

        public MyViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id);
            username = (TextView) itemView.findViewById(R.id.username);
            jadwal = (TextView) itemView.findViewById(R.id.jadwal);
            status = (TextView) itemView.findViewById(R.id.status);
        }
    }
}
