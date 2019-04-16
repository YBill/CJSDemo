package com.bill.cjsdemo;

import android.view.View;

public class PeoNumFragment extends BaseFragment {

    public static PeoNumFragment newInstance() {
        PeoNumFragment fragment = new PeoNumFragment();
        return fragment;
    }

    @Override
    protected int getRootLayout() {
        return R.layout.content_scrolling;
    }

    @Override
    protected void initView(View rootView) {

    }
}
