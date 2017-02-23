package com.by.framework.simple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.by.framework.R;
import com.by.framework.core.BaseListFragment;
import com.by.framework.core.BaseViewHolder;
import com.by.framework.widget.pull.PullToRefreshRecycler;

import java.util.ArrayList;

import static com.by.framework.R.id.mSampleListItemLabel;

/**
 * Created by asus-pc on 2017/2/22.
 */

public class SampleListFragment extends BaseListFragment<String> {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler.setRefreshing();
    }

    @Override
    public void onRefresh(final int action) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }

        recycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (action == PullToRefreshRecycler.ACTION_PULL_TO_REFRESH) {
                    mDataList.clear();
                }
                int size = mDataList.size();
                for (int i = size; i < size + 30; i++) {
                    mDataList.add("sample list item " + i);
                }
                adapter.notifyDataSetChanged();
                recycler.onRefreshCompleted();
                if (mDataList.size() < 100) {
                    recycler.enableLoadMore(true);
                } else {
                    recycler.enableLoadMore(false);
                }
            }
        }, 3000);
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sample_list_item, parent, false);
        return new SampleViewHolder(view);
    }

    private class SampleViewHolder extends BaseViewHolder {
        TextView mSampleListItemLabel;
        public SampleViewHolder(View view) {
            super(view);
            mSampleListItemLabel = (TextView) itemView.findViewById(R.id.mSampleListItemLabel);
        }

        @Override
        public void onBindViewHolder(int position) {
            mSampleListItemLabel.setText(mDataList.get(position));
        }

        @Override
        public void onItemClick(View view, int position) {

        }
    }
}
