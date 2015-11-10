package com.lr.ghp.dragsortlistview.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lr.ghp.dragsortlistview.R;
import com.lr.ghp.dragsortlistview.adapter.MainAdapter;
import com.lr.ghp.dragsortlistview.modle.ListItem;
import com.lr.ghp.dragsortlistview.util.DataListMeth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {
    @InjectView(R.id.my_awesome_toolbar)Toolbar toolbar;
    @InjectView(R.id.listView)ListView listView;
    private MainAdapter adapter;
    private List<ListItem> listItems=new ArrayList<ListItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        initView();
        getDataList();
    }

    private void initView(){
        adapter=new MainAdapter(listItems,this);
        listView.setAdapter(adapter);
    }

    private void getDataList(){
        listItems= sortList(DataListMeth.getNewDataList());
        adapter.updateListView(listItems);
    }

    @OnClick(R.id.editBtn) void editClick(){
        if(listItems.size()>0){
            Intent intent=new Intent(this,EditlistActivity.class);
            intent.putExtra("listData", (Serializable) listItems);
            startActivity(intent);
        }else{
            Toast.makeText(this, "没有数据，不能编辑", Toast.LENGTH_SHORT).show();
        }

    }

    private List<ListItem> sortList(List<ListItem> listItems){
        for(int i=listItems.size()-1;i>0;--i){
            for(int j=0;j<i;++j){
                ListItem item1=listItems.get(j);
                ListItem item2=listItems.get(j+1);
                if(item1.getOrder()>item2.getOrder()){
                    listItems.set(j,item2);
                    listItems.set(j+1,item1);
                }
            }
        }
        return listItems;
    }
}
