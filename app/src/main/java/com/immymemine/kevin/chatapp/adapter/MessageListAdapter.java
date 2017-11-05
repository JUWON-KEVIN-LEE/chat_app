package com.immymemine.kevin.chatapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.immymemine.kevin.chatapp.R;
import com.immymemine.kevin.chatapp.model.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quf93 on 2017-11-04.
 */

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.Holder> {
    Context context;
    public MessageListAdapter(Context context) {
        this.context = context;
    }

    List<Message> data = new ArrayList<>();
    public void setDataAndRefresh(List<Message> newData) {
        if(newData == null)
            return;

        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(data.get(position).isMine) {
            Log.d("getItemViewType", " ========== ");
            return 8888;
        }
        return super.getItemViewType(position);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        if(viewType == 8888) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat, parent, false);
            Log.d("viewType", " ========== 8888");
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_other, parent, false);
        }

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Message msg = data.get(position);

        if(msg.isMine) {
            holder.setMyMessage();
            holder.my_msg.setText(msg.message);
            holder.my_time.setText(msg.sended_time);
        } else {
            holder.setOtherMessage();
            holder.other_msg.setText(msg.message);
            holder.other_time.setText(msg.sended_time);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView my_time, my_msg, other_time, other_msg;
        LinearLayout my, other;

        public Holder(View itemView) {
            super(itemView);
        }

        public void setMyMessage() {
            my_time = itemView.findViewById(R.id.myTime);
            my_msg = itemView.findViewById(R.id.myMsg);
            my = itemView.findViewById(R.id.my);
        }

        public void setOtherMessage() {
            other_time = itemView.findViewById(R.id.otherTime);
            other_msg = itemView.findViewById(R.id.otherMsg);
            other = itemView.findViewById(R.id.other);
        }
    }
}
