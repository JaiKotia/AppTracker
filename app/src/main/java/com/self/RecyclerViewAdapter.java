package com.self;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hello on 13-02-2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<String> appList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView appStatus;

        public MyViewHolder(View view) {
            super(view);
            appStatus = view.findViewById(R.id.app_status);
        }
    }


    public RecyclerViewAdapter(List<String> appList) {
        this.appList = appList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String appStatus = appList.get(position);
        holder.appStatus.setText(appStatus);
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }
}
