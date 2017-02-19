package com.by.framework.core;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by asus-pc on 2017/2/19.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void onBind(int position);
}
