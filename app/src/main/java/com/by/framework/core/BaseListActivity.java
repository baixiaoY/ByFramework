package com.by.framework.core;

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

import java.util.ArrayList;

/**
 * Created by asus-pc on 2017/2/18.
 */

public abstract class BaseListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {


    protected SwipeRefreshLayout swipeRefreshLayout;
    protected RecyclerView recyclerView;
    protected SampleListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void setUpView() {
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
    }

    @Override
    protected void setUpData() {
        recyclerView.setLayoutManager(getLayoutManager());
        adapter = new SampleListAdapter();
        recyclerView.setAdapter(adapter);

    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getApplicationContext());
    }

    protected void setRefreshing(){
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
               onRefresh();
            }
        });
    }



    public class SampleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return getViewHolder(parent,viewType);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            onBind(holder,position);
        }

        @Override
        public int getItemCount() {
            return getDataCount();
        }
    }

    protected abstract int getDataCount();

    protected abstract void onBind(RecyclerView.ViewHolder holder, int position);

    protected abstract RecyclerView.ViewHolder getViewHolder(ViewGroup parent, int viewType);


}
