package com.immymemine.kevin.chatapp.util;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.immymemine.kevin.chatapp.model.Message;

import java.util.List;

/**
 * Created by quf93 on 2017-11-04.
 */

public class ListDiffUtil extends DiffUtil.Callback {
    List<Message> mOldList;
    List<Message> mNewList;

    public ListDiffUtil(List<Message> oldList, List<Message> newList) {
        this.mOldList = oldList;
        this.mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList != null ? mOldList.size() : 0 ;
    }

    @Override
    public int getNewListSize() {
        return mNewList != null ? mNewList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).equals(mNewList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Message oldMessage = mOldList.get(oldItemPosition);
        Message newMessage = mNewList.get(newItemPosition);


        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
