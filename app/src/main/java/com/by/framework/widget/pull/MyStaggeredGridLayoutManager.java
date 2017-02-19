package com.by.framework.widget.pull;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by asus-pc on 2017/2/19.
 */

public class MyStaggeredGridLayoutManager extends StaggeredGridLayoutManager implements ILayoutManager{

    public MyStaggeredGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyStaggeredGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }



    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return this;
    }

    @Override
    public int findLastVisibleItemPosition() {
        int[] positions = null;
        positions = findLastVisibleItemPositions(positions);
        return positions[0];
    }
}
