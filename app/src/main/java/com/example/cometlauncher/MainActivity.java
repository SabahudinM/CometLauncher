package com.example.cometlauncher;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    boolean isBottom = true;
    ViewPager mViewPager;
    int cellHeight;

    int NUMBER_OF_ROWS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initializeHome();
    }

    ViewPagerAdapter mViewPagerAdapter;

    private void initializeHome() {
        ArrayList<PagerObject> pagerAppList = new ArrayList<>();
        ArrayList<AppObject> appList = getInstalledAppList();
        List<AppObject> firstPage= new ArrayList<>();
        for(int i=0; i<4 && i<appList.size(); i++){
            firstPage.add(appList.get(i));
        }
        List<AppObject> secondPage= new ArrayList<>();
        for(int i=4; i<8 && i<appList.size(); i++){
            secondPage.add(appList.get(i));
        }
        List<AppObject> thirdPage= new ArrayList<>();
        for(int i=8; i<12 && i<appList.size(); i++){
            thirdPage.add(appList.get(i));
        }
        List<AppObject> fourthPage= new ArrayList<>();
        for(int i=12; i<16 && i<appList.size(); i++){
            fourthPage.add(appList.get(i));
        }
        pagerAppList.add(new PagerObject(firstPage));
        pagerAppList.add(new PagerObject(secondPage));
        pagerAppList.add(new PagerObject(thirdPage));
        pagerAppList.add(new PagerObject(fourthPage));






        cellHeight = (getDisplayContentHeight()) / NUMBER_OF_ROWS ;
        mViewPagerAdapter = new ViewPagerAdapter(this, pagerAppList, cellHeight);
        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setAdapter(mViewPagerAdapter);
        CustomPageTransformer pageTransformer = new CustomPageTransformer();
        mViewPager.setPageTransformer(true, pageTransformer);

    }
    private ArrayList<AppObject> getInstalledAppList() {
       ArrayList<AppObject> list = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> untreatedAppList = getApplicationContext().getPackageManager().queryIntentActivities(intent, 0);

        for(ResolveInfo untreatedApp : untreatedAppList){
            String appName = untreatedApp.activityInfo.loadLabel(getPackageManager()).toString();
            String appPackageName = untreatedApp.activityInfo.packageName;
            Drawable appImage = untreatedApp.activityInfo.loadIcon(getPackageManager());

            AppObject app = new AppObject(appPackageName, appName, appImage);
            if (!list.contains(app))
                list.add(app);
        }
        return list;
    }



    private int getDisplayContentHeight() {
        final WindowManager windowManager = getWindowManager();
        final Point size = new Point();
        int screenHeight = 0, actionBarHeight = 0, statusBarHeight = 0;


        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resourceId > 0){
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        int contentTop = (findViewById(android.R.id.content)).getTop();
        windowManager.getDefaultDisplay().getSize(size);
        screenHeight = size.y;
        return screenHeight - contentTop - actionBarHeight - statusBarHeight;
    }
    public void itemPress(AppObject app){
        Intent launchAppIntent=getApplicationContext().getPackageManager().getLaunchIntentForPackage((app.getPackageName()));
        if(launchAppIntent!=null){
            getApplicationContext().startActivity(launchAppIntent);
        }
    }

}
