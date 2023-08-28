package com.example.cometlauncher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;
    List<PagerObject> pagerAppList;
    int cellHeight;


    public ViewPagerAdapter(Context context, List<PagerObject> pagerAppList, int cellHeight){
        this.context = context;
        this.pagerAppList = pagerAppList;
        this.cellHeight = cellHeight;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.pager_layout, container, false);

        final GridView mGridView = layout.findViewById(R.id.grid);
        mGridView.setAdapter(new AppAdapter(context, pagerAppList.get(position).getAppList(), cellHeight));


        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return pagerAppList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
