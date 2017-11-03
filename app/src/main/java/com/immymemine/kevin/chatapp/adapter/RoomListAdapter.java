package com.immymemine.kevin.chatapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.immymemine.kevin.chatapp.R;
import com.immymemine.kevin.chatapp.model.ChatRoom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quf93 on 2017-11-02.
 */

public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.Holder>{

    public RoomListAdapter() {
    }

    List<ChatRoom> mRoomList = new ArrayList<>();
    public void setDataAndRefresh(List<ChatRoom> newRoomList) {
        if(newRoomList == null)
            return;

        mRoomList.clear();
        mRoomList.addAll(newRoomList);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_room, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ChatRoom chatRoom = mRoomList.get(position);
        holder.title.setText( chatRoom.getTitle() );
        holder.content.setText( chatRoom.getLast_message() );
        holder.date.setText( chatRoom.getLast_message_time() );
    }

    @Override
    public int getItemCount() {
        return mRoomList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, date, content;

        public Holder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            content = itemView.findViewById(R.id.content);
        }
    }
}
