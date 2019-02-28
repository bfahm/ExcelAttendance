package com.xencosworks.excelattendance;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xencosworks.excelattendance.Room.ColumnDB.Column;

import java.util.List;

/**
 * Created by Bola on 2/20/2019.
 */

class ColumnAdapter extends RecyclerView.Adapter<ColumnAdapter.ViewHolder> {

    List<Column> columnNames;
    private static final String TAG = "ColumnAdapter";

    public ColumnAdapter(List<Column> columnNames) {
        this.columnNames = columnNames;
    }

    @Override
    public ColumnAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.column_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ColumnAdapter.ViewHolder holder, int position) {
        holder.tvColumnNumber.setText(position+1+"");
        holder.tvColumnName.setText(columnNames.get(position).columnName);
    }

    @Override
    public int getItemCount() {
        Log.v(TAG, columnNames.size()+"");
        return columnNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvColumnNumber;
        public TextView tvColumnName;
        public ImageButton ibColumnOptions;
        public ViewHolder(View itemView) {
            super(itemView);
            tvColumnNumber = itemView.findViewById(R.id.col_num);
            tvColumnName = itemView.findViewById(R.id.col_name);
            ibColumnOptions = itemView.findViewById(R.id.col_options);
        }
    }
}
