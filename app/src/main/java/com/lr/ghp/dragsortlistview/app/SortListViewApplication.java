package com.lr.ghp.dragsortlistview.app;

import com.lr.ghp.dragsortlistview.modle.ListItem;

import java.util.List;

/**
 * Created by ghp on 15/11/10.
 */
public class SortListViewApplication extends android.app.Application {
    private List<ListItem> lastListItems;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public List<ListItem> getLastListItems() {
        return lastListItems;
    }

    public void setLastListItems(List<ListItem> lastListItems) {
        this.lastListItems = lastListItems;
    }
}
