package com.by.framework.simple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.by.framework.R;
import com.by.framework.core.BaseActivity;
import com.by.framework.core.BaseListActivity;

import java.util.ArrayList;

/**
 * Created by asus-pc on 2017/2/18.
 */

public class SimpleListActivity extends BaseListActivity implements SwipeRefreshLayout.OnRefreshListener {



    private ArrayList<String> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_simple_list,R.string.sample_list_title);
    }

    @Override
    protected void setUpView() {
        super.setUpView();
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        setRefreshing();
    }



    @Override
    public void onRefresh() {
        mDataList.clear();
        for (int i = 0; i < 50; i++) {
            mDataList.add("sample list item " + i);
        }
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected int getDataCount() {
        return mDataList.size();
    }

    @Override
    protected void onBind(RecyclerView.ViewHolder holder, int position) {
        ((SampleViewHolder)holder).mSampleListItemLabel.setText(mDataList.get(position));
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sample_list_item, parent, false);
        return new SampleViewHolder(view);
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

    class SampleViewHolder extends RecyclerView.ViewHolder{
        TextView mSampleListItemLabel;
        public SampleViewHolder(View itemView) {
            super(itemView);
            mSampleListItemLabel = (TextView) itemView.findViewById(R.id.mSampleListItemLabel);
        }
    }
}
