package com.xencosworks.excelattendance;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xencosworks.excelattendance.Room.ServeeDB.Servee;

import java.util.List;

/**
 * Created by Bola on 2/20/2019.
 */

class ServeeAdapter extends RecyclerView.Adapter<ServeeAdapter.ViewHolder> {

    List<Servee> servees;
    private static final String TAG = "ColumnAdapter";

    public ServeeAdapter(List<Servee> servees) {
        this.servees = servees;
    }

    @Override
    public ServeeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.servee_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ServeeAdapter.ViewHolder holder, int position) {
        holder.tvEntryNumber.setText(position+1+"");
        holder.tvServeeNumber.setText(servees.get(position).serveeNumber+"");
        holder.tvServeeOdas.setText(servees.get(position).isOdas+"");
        holder.tvServeeMdares.setText(servees.get(position).isMdaresAhad+"");
        holder.tvServeeEarly.setText(servees.get(position).isEarly+"");
        holder.tvServeeNadi.setText(servees.get(position).isNadi+"");
    }

    @Override
    public int getItemCount() {
        Log.v(TAG, servees.size()+"");
        return servees.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvEntryNumber;
        public TextView tvServeeNumber;
        public TextView tvServeeOdas;
        public TextView tvServeeMdares;
        public TextView tvServeeEarly;
        public TextView tvServeeNadi;
        public ViewHolder(View itemView) {
            super(itemView);
            tvEntryNumber = itemView.findViewById(R.id.entry_num);
            tvServeeNumber = itemView.findViewById(R.id.servee_id);
            tvServeeOdas = itemView.findViewById(R.id.servee_odas);
            tvServeeMdares = itemView.findViewById(R.id.servee_mdares_ahad);
            tvServeeEarly = itemView.findViewById(R.id.servee_early);
            tvServeeNadi = itemView.findViewById(R.id.servee_nadi);
        }
    }
}
