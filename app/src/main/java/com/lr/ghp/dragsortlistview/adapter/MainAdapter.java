package com.lr.ghp.dragsortlistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lr.ghp.dragsortlistview.R;
import com.lr.ghp.dragsortlistview.modle.ListItem;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ghp on 15/11/10.
 */
public class MainAdapter extends BaseAdapter{
    private List<ListItem> listItems;
    private Context context;
    private LayoutInflater inflater;

    public MainAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
        this.inflater=LayoutInflater.from(context);
    }

    public void updateListView(List<ListItem> listItems){
        this.listItems = listItems;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItem listItem= (ListItem) getItem(position);
        ViewHolder viewHolder;
        if(convertView == null){
            convertView=inflater.inflate(R.layout.list_item_layout,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.content.setText(listItem.getItemContent());
        return convertView;
    }

    static class ViewHolder{
        @InjectView(R.id.content)TextView content;

        public ViewHolder(View view) {
            ButterKnife.inject(this,view);
        }
    }
}
