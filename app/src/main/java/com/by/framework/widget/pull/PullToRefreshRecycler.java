package com.by.framework.widget.pull;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.by.framework.R;
import com.by.framework.core.BaseListActivity;
import com.by.framework.core.BaseListAdapter;
import com.by.framework.utils.L;

/**
 * Created by asus-pc on 2017/2/19.
 */

public class PullToRefreshRecycler extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    public static final int ACTION_PULL_TO_REFRESH = 1;
    public static final int ACTION_LOAD_MORE_REFRESH = 2;
    public static final int ACTION_IDLE = 0;
    private OnRecyclerRefreshListener listener;
    private int mCurrentState;
    private boolean isLoadMoreEnabled = false;
    private boolean isPullToRefreshEnabled = true;
    private ILayoutManager mLayoutManager;
    private BaseListAdapter adapter;

    public PullToRefreshRecycler(Context context) {
        super(context);
        setUpView();
    }


    public PullToRefreshRecycler(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpView();
    }

    public PullToRefreshRecycler(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView();
    }

    private void setUpView() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_pull_refresh,this,true);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                L.i("dy:"+dy);
                if (mCurrentState == ACTION_IDLE && isLoadMoreEnabled && checkIfLoadMore()){
                    L.i("isLoadMoreEnabled:"+isLoadMoreEnabled);
                    L.i("checkIfLoadMore:"+checkIfLoadMore());
                    mCurrentState = ACTION_LOAD_MORE_REFRESH;
                    adapter.onLoadMoreStateChanged(true);
                    mSwipeRefreshLayout.setEnabled(false);
                    listener.onRefresh(ACTION_LOAD_MORE_REFRESH);

                }
            }
        });
    }

    

    private boolean checkIfLoadMore() {
        int position = mLayoutManager.findLastVisiblePosition();
        L.i("position:"+position);
        int totalCount = mLayoutManager.getLayoutManager().getItemCount();
        L.i("totalCount:"+totalCount);
        return totalCount - position < 5;
    }

    public void enableLoadMore(boolean enable){
        isLoadMoreEnabled = enable;
    }

    public void enablePullToRefresh(boolean enable){
        isPullToRefreshEnabled = enable;
        mSwipeRefreshLayout.setEnabled(enable);
    }

    public void setLayoutManager(ILayoutManager manager){
        this.mLayoutManager = manager;
        mRecyclerView.setLayoutManager(manager.getLayoutManager());
    }

    public void addItemDecoration(RecyclerView.ItemDecoration decoration){
        if (decoration != null){
            mRecyclerView.addItemDecoration(decoration);
        }
    }

    public void setAdapter(BaseListAdapter adapter){
        this.adapter = adapter;
        mRecyclerView.setAdapter(adapter);
        mLayoutManager.setUpAdapter(adapter);
    }

    public void setRefreshing(){
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
    }

    public void setOnRefreshListener(OnRecyclerRefreshListener listener){
        this.listener = listener;
    }


    @Override
    public void onRefresh() {
        mCurrentState = ACTION_PULL_TO_REFRESH;
        listener.onRefresh(ACTION_PULL_TO_REFRESH);

    }

    public void onRefreshCompleted() {
        switch (mCurrentState){
            case ACTION_PULL_TO_REFRESH://下拉刷新
                mSwipeRefreshLayout.setRefreshing(false);
                break;
            case ACTION_LOAD_MORE_REFRESH://加载更多
                adapter.onLoadMoreStateChanged(false);
                if (isPullToRefreshEnabled){
                    mSwipeRefreshLayout.setEnabled(true);
                }
                break;
        }
        mCurrentState = ACTION_IDLE;
    }

    public interface OnRecyclerRefreshListener{
        void onRefresh(int action);
    }

}
