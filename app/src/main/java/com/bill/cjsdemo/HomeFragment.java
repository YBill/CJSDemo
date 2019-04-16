package com.bill.cjsdemo;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    private MagicIndicator magicIndicator;
    private CommonNavigator commonNavigator;
    private ViewPager viewPager;
    private MyPagerAdapter mPagerAdapter;
    private List<Fragment> homeList = new ArrayList<>();
    private String[] titles;

    @Override
    protected int getRootLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View rootView) {
        viewPager = rootView.findViewById(R.id.view_pager);
        magicIndicator = rootView.findViewById(R.id.magic_indicator);
        commonNavigator = new CommonNavigator(getActivity());
        mPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        mPagerAdapter.setFragments(homeList);
        viewPager.setAdapter(mPagerAdapter);
        titles = new String[]{
                "闻", "评", "问", "京", "报"
        };
        for (int i = 0; i < titles.length; i++) {
            homeList.add(HomeListFragment.newInstance(titles[i]));
        }
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setTextSize(20);
                colorTransitionPagerTitleView.setNormalColor(ContextCompat.getColor(getActivity(), R.color.black));
                colorTransitionPagerTitleView.setSelectedColor(ContextCompat.getColor(getActivity(), R.color.color_D83C3D));
                colorTransitionPagerTitleView.setText(titles[index]);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
        mPagerAdapter.notifyDataSetChanged();

    }

}
