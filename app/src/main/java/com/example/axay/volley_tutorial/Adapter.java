package com.example.axay.volley_tutorial;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by axay on 20/07/17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{


    List<history_data.DataBean> data;
    Context context;

    public Adapter(List<history_data.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        history_data.DataBean datas =data.get(position);

        holder.leave_type.setText(datas.getType());
        holder.leave_reason.setText(datas.getLeave_reason());
        holder.from_date.setText(datas.getFrom());
        holder.to_date.setText(datas.getTo());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView leave_type;
        private TextView leave_reason;
        private TextView from_date;
        private TextView to_date;


        public ViewHolder(View itemView) {
            super(itemView);

            leave_type=(TextView)itemView.findViewById(R.id.type);
            leave_reason=(TextView)itemView.findViewById(R.id.reason);
            from_date=(TextView)itemView.findViewById(R.id.from_date);
            to_date=(TextView)itemView.findViewById(R.id.to_date);



        }
    }
}
