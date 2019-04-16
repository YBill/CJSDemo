package com.bill.cjsdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeListFragment extends BaseFragment {

    private String title;
    private boolean isInitView = false;
    private boolean isVisible = false;
    private boolean isFirst = true;

    public static HomeListFragment newInstance(String title) {
        HomeListFragment fragment = new HomeListFragment();
        fragment.title = title;
        return fragment;
    }

    @Override
    protected int getRootLayout() {
        return R.layout.content_scrolling;
    }

    @Override
    protected void initView(View rootView) {

    }

    private void isCanLoadData() {
        if (isInitView && isVisible) {
            lazyLoad();
        }
    }

    private void lazyLoad() {
        Log.wtf("Bill", title + "--lazyLoad:");

        if (isFirst) {
            isFirst = false;
            firstLoad();
        }
    }

    private void firstLoad() {
        Log.wtf("Bill", title + "--firstLoad:");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Bill", title + "--onCreate:");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.w("Bill", title + "--onCreateView:");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isInitView = true;
        isCanLoadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInitView = false;
        Log.i("Bill", title + "--onDestroyView:");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("Bill", title + "--setUserVisibleHint:" + isVisibleToUser);
        isVisible = isVisibleToUser;
        if (isVisibleToUser) {
            isCanLoadData();
        }
    }
}
