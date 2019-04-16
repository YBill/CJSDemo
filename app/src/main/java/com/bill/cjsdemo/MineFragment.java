package com.bill.cjsdemo;

import android.view.View;

import com.gyf.barlibrary.ImmersionBar;

public class MineFragment extends BaseFragment {

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    protected int getRootLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View rootView) {
        ImmersionBar.setTitleBar(getActivity(), rootView.findViewById(R.id.detail_toolbar));
    }
}
