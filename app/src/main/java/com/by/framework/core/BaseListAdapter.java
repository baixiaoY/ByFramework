package com.by.framework.core;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.by.framework.R;

/**
 * Created by asus-pc on 2017/2/22.
 */

public abstract class BaseListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    protected static final int VIEW_TYPE_LOAD_MORE_FOOTER = 100;
    protected boolean isLoadMoreFooterShown;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_LOAD_MORE_FOOTER){
            return onCreateLoadMoreFooterViewHolder(parent);
        }
        return onCreateNormalViewHolder(parent, viewType);
    }

    protected abstract BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType);

    protected BaseViewHolder onCreateLoadMoreFooterViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_pull_to_refresh_footer, parent, false);
        return new LoadMoreFooterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (isLoadMoreFooterShown && position == getItemCount()-1){
            if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                params.setFullSpan(true);
            }
        }
        holder.onBindViewHolder(position);
    }

    @Override
    public int getItemCount() {
        return getDataCount() + (isLoadMoreFooterShown ? 1:0);
    }

    protected abstract int getDataCount();

    @Override
    public int getItemViewType(int position) {
        if (isLoadMoreFooterShown && position == getItemCount()-1){
            return VIEW_TYPE_LOAD_MORE_FOOTER;
        }
        return getDataViewType(position);
    }

    protected int getDataViewType(int position) {
        return 0;
    }

    public void onLoadMoreStateChanged(boolean isShown) {
        this.isLoadMoreFooterShown = isShown;
        if (isShown){
            notifyItemInserted(getItemCount());
        }else{
            notifyItemRemoved(getItemCount());
        }

    }

    public boolean isFooterView(int position) {
        return isLoadMoreFooterShown && position == getItemCount()-1;
    }

    public boolean isSectionHeader(int position) {
        return false;
    }

    private static class LoadMoreFooterViewHolder extends BaseViewHolder {
        public LoadMoreFooterViewHolder(View view) {
            super(view);
        }

        @Override
        public void onBindViewHolder(int position) {

        }

        @Override
        public void onItemClick(View view, int position) {

        }
    }


}
