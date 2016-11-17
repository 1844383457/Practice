package net.lzzy.practice.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import net.lzzy.practice.R;
import net.lzzy.practice.logics.PracticeFactory;
import net.lzzy.practice.models.Practice;
import net.lzzy.practice.view.CustomAlertDialog;

import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Practice> practices;
    private Context context;
    private onRecyclerViewItemClickListener itemClickListener = null;
   // private onRecyclerViewItemLongClickListener itemClickLongListener = null;

    public RecyclerViewAdapter(List<Practice> practices) {
        this.practices = practices;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_practice, parent, false));
    }

    public void setOnItemClickListener(onRecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;

    }

//    public void setOnItemLongClickListener(onRecyclerViewItemLongClickListener listener) {
//        this.itemClickLongListener = listener;
//    }


    public interface onRecyclerViewItemClickListener {

        void onItemClick(View v, int position);

    }
//
//    public interface onRecyclerViewItemLongClickListener {
//
//        boolean onItemLongClick(View v, int position);
//
//    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String name = practices.get(position).getName();
        if (name.length() > 15)
            name = name.substring(0, 15).concat("...");
        holder.tv_name.setText(name);
        if (practices.get(position).isDownload()) {
            holder.btn_main.setVisibility(View.GONE);
        } else {
            holder.btn_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CustomAlertDialog alert = new CustomAlertDialog(context, false);
                    alert.setTitle("大纲");
                    alert.setMessage(practices.get(holder.getAdapterPosition()).getOutLines());
                    alert.show();
                }
            });
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null)
                    itemClickListener.onItemClick(view, holder.getAdapterPosition());
            }
        });
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                holder.cb_select.setVisibility(View.VISIBLE);
//                return false;
//            }
//        });
    }

    public void removeItem(int position) {
        PracticeFactory.getInstance().deletePractice(practices.get(position));
        practices.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return practices.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        Button btn_main;

        ViewHolder(View convertView) {
            super(convertView);
            tv_name = (TextView) convertView.findViewById(R.id.item_practice_tv_name);
            btn_main = (Button) convertView.findViewById(R.id.item_practice_btn_main);
        }
    }

    public void moveItem(int fromPosition, int toPosition) {
        //做数据的交换
        if (fromPosition < toPosition) {
            for (int index = fromPosition; index < toPosition; index++) {
                Collections.swap(practices, index, index + 1);
            }
        } else {
            for (int index = fromPosition; index > toPosition; index--) {
                Collections.swap(practices, index, index - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }
}
