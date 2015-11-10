package com.lr.ghp.dragsortlistview.util;

import com.lr.ghp.dragsortlistview.modle.ListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ghp on 15/11/10.
 */
public class DataListMeth {

    public static List<ListItem> getNewDataList(){
        List<ListItem> listItems=new ArrayList<ListItem>();
        for(int i=0;i<8;i++){
            ListItem listItem=new ListItem(i+"",i,"List Item Content "+i);
            listItems.add(listItem);
        }
        return listItems;
    }
}
