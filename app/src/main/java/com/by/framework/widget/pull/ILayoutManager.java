package com.by.framework.widget.pull;

import android.support.v7.widget.RecyclerView;

import com.by.framework.core.BaseListAdapter;

/**
 * Created by asus-pc on 2017/2/19.
 */

public interface ILayoutManager {

    RecyclerView.LayoutManager getLayoutManager();

    int findLastVisiblePosition();

    void setUpAdapter(BaseListAdapter adapter);
}
