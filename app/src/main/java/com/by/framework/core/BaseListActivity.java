package com.by.framework.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.by.framework.R;
import com.by.framework.widget.pull.DividerItemDecoration;
import com.by.framework.widget.pull.ILayoutManager;
import com.by.framework.widget.pull.MyLinearLayoutManager;
import com.by.framework.widget.pull.PullToRefreshRecycler;

import java.util.ArrayList;

/**
 * Created by asus-pc on 2017/2/18.
 */

public abstract class BaseListActivity<T> extends BaseActivity implements PullToRefreshRecycler.OnRecyclerRefreshListener {

    protected BaseListAdapter adapter;
    protected ArrayList<T> mDataList;
    protected PullToRefreshRecycler recycler;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_base_list,-1);
    }

    @Override
    protected void setUpView() {
        recycler = (PullToRefreshRecycler)findViewById(R.id.pullToRefreshRecycler);
    }

    @Override
    protected void setUpData() {
        adapter = new ListAdapter();
        recycler.setLayoutManager(getLayoutManager());
        recycler.setOnRefreshListener(this);
        recycler.addItemDecoration(getItemDecoration());
        recycler.setAdapter(adapter);

    }

    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(getApplicationContext(),R.drawable.list_divider);
    }

    protected ILayoutManager getLayoutManager() {
        return new MyLinearLayoutManager(getApplicationContext());
    }

//    public class BaseListAdapter extends RecyclerView.Adapter<BaseViewHolder>{
//
//
//        private static final int VIEW_TYPE_LOAD_MORE_FOOTER = 100;
//        private boolean isShowLoadMoreFoot;
//
//        @Override
//        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            if (viewType==VIEW_TYPE_LOAD_MORE_FOOTER){
//                return getLoadMoreFooter(parent);
//            }
//            return getViewHolder(parent,viewType);
//        }
//
//        protected BaseViewHolder getLoadMoreFooter(ViewGroup parent) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_pull_to_refresh_footer,parent,false);
//            return new LoadMoreFooterViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(BaseViewHolder holder, int position) {
//            //onBind(holder,position);
//            if (isShowLoadMoreFoot && position == getItemCount()-1){
//                if ( holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams){
//                    StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams)holder.itemView.getLayoutParams();
//                    params.setFullSpan(true);
//                }
//
//            }
//            holder.onBind(position);
//        }
//
//        @Override
//        public int getItemCount() {
//            return mDataList!=null ? mDataList.size()+(isShowLoadMoreFoot?1:0):0;
//        }
//
//        @Override
//        public int getItemViewType(int position) {
//            if (isShowLoadMoreFoot && position == getItemCount()-1){
//                return VIEW_TYPE_LOAD_MORE_FOOTER;
//            }
//            return super.getItemViewType(position);
//        }
//
//        public void showLoadMoreFoot(boolean isShow) {
//            this.isShowLoadMoreFoot = isShow;
//            if(isShow){
//                //解决Warn:Scroll callbacks might be run during a measure & layout pass where you cannot change the RecyclerView data
//                recycler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        notifyItemInserted(getItemCount());
//                    }
//                });
//
//            }else{
//                notifyItemRemoved(getItemCount());
//            }
//        }
//
//        public boolean isFooterView(int position) {
//            return isShowLoadMoreFoot && position == getItemCount()-1;
//        }
//
//        private class LoadMoreFooterViewHolder extends BaseViewHolder {
//            public LoadMoreFooterViewHolder(View view) {
//                super(view);
//            }
//
//            @Override
//            protected void onBind(int position) {
//
//            }
//        }
//    }

//    protected abstract int getDataCount();

    //protected abstract void onBind(BaseViewHolder holder, int position);

    public class ListAdapter extends BaseListAdapter{


        @Override
        protected BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
            return getViewHolder(parent,viewType);
        }

        @Override
        protected int getDataCount() {
            return  mDataList!= null ? mDataList.size() : 0;
        }

        @Override
        protected int getDataViewType(int position) {
            return getItemType(position);
        }

        @Override
        public boolean isSectionHeader(int position) {
            return BaseListActivity.this.isSectionHeader(position);
        }
    }

    protected int getItemType(int position) {
        return 0;
    }

    protected abstract BaseViewHolder getViewHolder(ViewGroup parent, int viewType);

    protected boolean isSectionHeader(int position) {
        return false;
    }
}
