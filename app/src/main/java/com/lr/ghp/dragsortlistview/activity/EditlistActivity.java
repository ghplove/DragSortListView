package com.lr.ghp.dragsortlistview.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Toast;

import com.lr.ghp.dragsortlistview.R;
import com.lr.ghp.dragsortlistview.adapter.EditListAdapter;
import com.lr.ghp.dragsortlistview.app.SortListViewApplication;
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
    @InjectView(R.id.allDeleteBtn)CheckBox allDeleteBtn;
    private List<ListItem> listItems=new ArrayList<ListItem>();
    private List<ListItem> saveListItems=new ArrayList<ListItem>();//最后保存的顺序list
    private List<ListItem> deleteListItems=new ArrayList<ListItem>();//本地删除的list
    private int deleteNum;
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
    @OnClick(R.id.allDeleteBtn)void allDelete(){
        //TODO:全选
        setListCheckStatus(allDeleteBtn.isChecked());
    }
    @OnClick(R.id.saveEditBtn)void saveEdit(){
        //TODO:完成编辑
        saveItem();
    }
    @OnClick(R.id.ensureDeleteBtn)void ensureDelete(){
        //TODO:删除选中   本地操作删除
        if (listItems != null) {
            for (int i = 0; i < listItems.size(); i++) {
                if (editListView.isItemChecked(i)) {
                    deleteListItems.add(listItems.get(i));
                }
            }
        }

        deleteNum = deleteListItems.size();
        if (deleteNum > 0) {
            //从显示列表中移除
            for (int i = 0; i < deleteNum; i++) {
                listItems.remove(deleteListItems.get(i));
            }
            editListView.clearChoices();
            editListAdapter.updateAdapter(listItems);
            allDeleteBtn.setChecked(false);
        } else {
            Toast.makeText(this, getString(R.string.choose_delete_item), Toast.LENGTH_SHORT).show();
        }
    }
    private void initView(){
        editListAdapter=new EditListAdapter(this,listItems);
        editListView.setAdapter(editListAdapter);
        editListView.setDropListener(onDrop);
//        editListView.setRemoveListener(onRemove);//因为是多选删除，所以注释掉了
        editListView.setDragEnabled(true);//设置是否可拖动。
        editListView.clearChoices();
        editListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (getIsAllChecked()) {
                    allDeleteBtn.setChecked(true);
                } else {
                    allDeleteBtn.setChecked(false);
                }
            }
        });
    }
    //监听器在手机拖动停下的时候触发
    private DragSortListView.DropListener onDrop =
            new DragSortListView.DropListener() {
                @Override
                public void drop(int from, int to) {//from to 分别表示 被拖动控件原位置 和目标位置
                    if (from != to) {
                        ListItem item = (ListItem)editListAdapter.getItem(from);//得到listview的适配器
                        editListAdapter.remove(from);//在适配器中”原位置“的数据。
                        editListAdapter.insert(item, to);//在目标位置中插入被拖动的控件。
                        editListView.moveCheckState(from,to);
                    }
                }
            };
    //删除监听器，点击左边差号就触发。删除item操作。
    private DragSortListView.RemoveListener onRemove =
            new DragSortListView.RemoveListener() {
                @Override
                public void remove(int which) {
                    editListAdapter.remove(which);
                }
            };
    private boolean getIsAllChecked(){
        if (listItems!=null) {
            for (int i = 0; i < listItems.size(); i++) {
                if(!editListView.isItemChecked(i)){
                    return false;
                }else{
                    continue;
                }
            }
            return true;
        }
        return true;
    }
    private void setListCheckStatus(boolean check){
        if (listItems!=null) {
            for(int i=0;i<listItems.size();i++){
                if(check){
//                            allDeleteBtn.setText("取消全选");
                    if(!editListView.isItemChecked(i)){
                        editListView.setItemChecked(i,true);
                    }
                }else{
//                            allDeleteBtn.setText("全选");
                    if(editListView.isItemChecked(i)){
                        editListView.setItemChecked(i,false);
                    }
                }
            }
        }
        allDeleteBtn.setChecked(check);
    }
    private void saveItem(){
        SortListViewApplication app= (SortListViewApplication) getApplicationContext();
        app.setLastListItems(listItems);
        Toast.makeText(this, getString(R.string.save_list_ok), Toast.LENGTH_SHORT).show();
        this.finish();
    }

    public void remove(int which) {
        editListAdapter.remove(which);
        editListView.removeCheckState(which);
    }
}
