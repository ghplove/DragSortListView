package com.lr.ghp.dragsortlistview.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.lr.ghp.dragsortlistview.R;
import com.lr.ghp.dragsortlistview.adapter.EditListAdapter;
import com.lr.ghp.dragsortlistview.modle.ListItem;
import com.mobeta.android.dslv.DragSortListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class EditlistActivity extends ActionBarActivity {
    @InjectView(R.id.my_awesome_toolbar)Toolbar toolbar;
    @InjectView(R.id.editListView)DragSortListView editListView;
    private List<ListItem> listItems=new ArrayList<ListItem>();
    private List<ListItem> saveListItems=new ArrayList<ListItem>();//最后保存的顺序list
    private List<ListItem> deleteListItems=new ArrayList<ListItem>();//本地删除的list
    private EditListAdapter editListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editlist);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        listItems= (List<ListItem>) getIntent().getSerializableExtra("listData");
        initView();
    }
    @OnClick(R.id.backBtn) void goBack(){
        this.finish();
    }
    private void initView(){
        editListAdapter=new EditListAdapter(this,listItems);
        editListView.setAdapter(editListAdapter);
    }
}
