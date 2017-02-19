package com.by.framework.simple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.by.framework.R;
import com.by.framework.core.BaseListActivity;
import com.by.framework.core.BaseViewHolder;
import com.by.framework.widget.pull.ILayoutManager;
import com.by.framework.widget.pull.MyLinearLayoutManager;
import com.by.framework.widget.pull.MyStaggeredGridLayoutManager;
import com.by.framework.widget.pull.PullToRefreshRecycler;

import java.util.ArrayList;

/**
 * Created by asus-pc on 2017/2/18.
 */

public class SimpleListActivity extends BaseListActivity<String>{



    //private ArrayList<String> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void setUpTitle(int titleResId) {
        super.setUpTitle(R.string.sample_list_title);
    }

    @Override
    protected void setUpView() {
        super.setUpView();
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        recycler.setRefreshing();
    }



//    @Override
//    protected int getDataCount() {
//        return mDataList.size();
//    }

//    @Override
//    protected void onBind(RecyclerView.ViewHolder holder, int position) {
//        ((SampleViewHolder)holder).mSampleListItemLabel.setText(mDataList.get(position));
//    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sample_list_item, parent, false);
        return new SampleViewHolder(view);
    }

    @Override
    protected ILayoutManager getLayoutManager() {
        return new MyLinearLayoutManager(getApplicationContext());
        //return new MyStaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public void onRefresh(final int action) {
        if ( mDataList == null){
            mDataList = new ArrayList<>();
        }
        recycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (action == PullToRefreshRecycler.ACTION_PULL_TO_REFRESH){
                    mDataList.clear();
                }
                int size = mDataList.size();
                for (int i = size; i < size+20; i++) {
                    mDataList.add("sample list item " + i);
                }
                adapter.notifyDataSetChanged();
                recycler.onRefreshCompleted();
                if (mDataList.size()<100){
                    recycler.enableLoadMore(true);
                }else{
                    recycler.enableLoadMore(false);
                }
            }
        },3000);


    }


//    class SampleListAdapter extends RecyclerView.Adapter<SampleViewHolder>{
//
//
//        @Override
//        public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sample_list_item, parent, false);
//            return new SampleViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(SampleViewHolder holder, int position) {
//            holder.mSampleListItemLabel.setText(mDataList.get(position));
//        }
//
//        @Override
//        public int getItemCount() {
//            return mDataList.size();
//        }
//    }

    class SampleViewHolder extends BaseViewHolder {
        TextView mSampleListItemLabel;
        public SampleViewHolder(View itemView) {
            super(itemView);
            mSampleListItemLabel = (TextView) itemView.findViewById(R.id.mSampleListItemLabel);
        }

        @Override
        protected void onBind(int position) {
            mSampleListItemLabel.setText(mDataList.get(position));
        }
    }
}
