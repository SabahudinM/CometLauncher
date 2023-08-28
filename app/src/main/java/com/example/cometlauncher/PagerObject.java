package com.example.cometlauncher;

import java.util.ArrayList;
import java.util.List;

public class PagerObject {
    private List<AppObject> appList;

    public PagerObject(List<AppObject> appList){
        this.appList = appList;
    }

    public List<AppObject> getAppList() {
        return appList;
    }
}
