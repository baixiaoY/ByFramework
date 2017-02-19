package com.by.framework.widget.pull;

import android.support.v7.widget.RecyclerView;

/**
 * Created by asus-pc on 2017/2/19.
 */

public interface ILayoutManager {

    RecyclerView.LayoutManager getLayoutManager();

    int findLastVisibleItemPosition();
}
