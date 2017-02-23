package com.by.framework.simple;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.by.framework.R;
import com.by.framework.core.BaseSectionListActivity;
import com.by.framework.core.BaseViewHolder;
import com.by.framework.widget.pull.ILayoutManager;
import com.by.framework.widget.pull.MyGridLayoutManager;
import com.by.framework.widget.pull.MyLinearLayoutManager;
import com.by.framework.widget.pull.MyStaggeredGridLayoutManager;
import com.by.framework.widget.pull.PullToRefreshRecycler;
import com.by.framework.widget.pull.section.SectionData;
import com.by.framework.widget.pull.section.SectionGridLayoutManager;

import java.util.ArrayList;

/**
 * Created by asus-pc on 2017/2/22.
 */

public class SampleSectionListActivity extends BaseSectionListActivity<String> {


    @Override
    protected void setUpTitle(int titleResId) {
        super.setUpTitle(R.string.title_recycler_section_activity);
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        recycler.setRefreshing();
    }

    @Override
    protected BaseViewHolder onCreateSectionViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sample_list_item, parent, false);
        return new SampleViewHolder(view);
    }

    @Override
    protected ILayoutManager getLayoutManager() {
        //MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(getApplicationContext());
        return new SectionGridLayoutManager(getApplicationContext(),3);
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return null;
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
                mDataList.add(new SectionData(true, size, "header " + size));
                for (int i = size; i < size + 30; i++) {
                    mDataList.add(new SectionData("sample list item" + i));
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

    private class SampleViewHolder extends BaseViewHolder {
        TextView mSampleListItemLabel;

        public SampleViewHolder(View itemView) {
            super(itemView);
            mSampleListItemLabel = (TextView) itemView.findViewById(R.id.mSampleListItemLabel);
        }

        @Override
        public void onBindViewHolder(int position) {
            mSampleListItemLabel.setText(mDataList.get(position).t);
        }

        @Override
        public void onItemClick(View view, int position) {

        }
    }
}
