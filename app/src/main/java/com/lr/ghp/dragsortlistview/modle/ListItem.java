package com.lr.ghp.dragsortlistview.modle;

import java.io.Serializable;

/**
 * Created by ghp on 15/11/10.
 */
public class ListItem implements Serializable{
    private String id;//ID
    private int order;//顺序
    private String itemContent;//内容

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }

    public ListItem() {
    }

    public ListItem(String id, int order, String itemContent) {
        this.id = id;
        this.order = order;
        this.itemContent = itemContent;
    }
}
