package com.by.framework.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.by.framework.R;
import com.by.framework.widget.pull.DividerItemDecoration;
import com.by.framework.widget.pull.ILayoutManager;
import com.by.framework.widget.pull.MyLinearLayoutManager;
import com.by.framework.widget.pull.PullToRefreshRecycler;

import java.util.ArrayList;

/**
 * Created by asus-pc on 2017/2/22.
 */

public abstract class BaseListFragment<T> extends BaseFragment implements PullToRefreshRecycler.OnRecyclerRefreshListener {

    protected BaseListAdapter adapter;
    protected PullToRefreshRecycler recycler;
    protected ArrayList<T> mDataList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_list,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler = (PullToRefreshRecycler) view.findViewById(R.id.pullToRefreshRecycler);
        setUpAdapter();
        recycler.setOnRefreshListener(this);
        recycler.setLayoutManager(getLayoutManager());
        recycler.addItemDecoration(getItemDecoration());
        recycler.setAdapter(adapter);
    }



    protected void setUpAdapter() {
        adapter = new ListAdapter();

    }

    protected ILayoutManager getLayoutManager() {
        return new MyLinearLayoutManager(getContext());
    }

    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(getContext(), R.drawable.list_divider);
    }


    private class ListAdapter extends BaseListAdapter {

        @Override
        protected BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
            return getViewHolder(parent,viewType);
        }

        @Override
        protected int getDataCount() {
            return mDataList != null ? mDataList.size() : 0;
        }

        @Override
        protected int getDataViewType(int position) {
            return getItemType(position);
        }
    }

    protected int getItemType(int position) {
        return 0;
    }

    protected abstract BaseViewHolder getViewHolder(ViewGroup parent, int viewType);
}
