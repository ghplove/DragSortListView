package com.lr.ghp.dragsortlistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.lr.ghp.dragsortlistview.R;
import com.lr.ghp.dragsortlistview.modle.ListItem;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ghp on 15/11/10.
 */
public class EditListAdapter extends BaseAdapter {
    private Context context;
    private List<ListItem> listItems;
    private LayoutInflater inflater;

    public EditListAdapter(Context context, List<ListItem> listItems) {
        this.context = context;
        this.listItems = listItems;
        this.inflater=LayoutInflater.from(context);
    }

    public void updateAdapter(List<ListItem> listItems){
        this.listItems=listItems;
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
        final ListItem listItem= (ListItem) getItem(position);
        final ViewHolder holder;

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.edit_item_layout, parent, false);
            holder = new ViewHolder(convertView);
            holder.deleteBtn.setCheckMarkDrawable(R.drawable.abc_btn_check_material);
            convertView.setTag(holder);
        }
        holder.itemContent.setText(listItem.getItemContent());
        return convertView;
    }
    static class ViewHolder{
        @InjectView(R.id.deleteBtn)CheckedTextView deleteBtn;
        @InjectView(R.id.itemContent) TextView itemContent;

        public ViewHolder(View view) {
            ButterKnife.inject(this,view);
        }
    }
    public List<ListItem> getItems() {
        return listItems;
    }

    public void remove(int arg0) {//删除指定位置的item
        listItems.remove(arg0);
        this.notifyDataSetChanged();//不要忘记更改适配器对象的数据源
    }

    public void insert(ListItem item, int arg0) {//在指定位置插入item
        listItems.add(arg0, item);
        this.notifyDataSetChanged();
    }
    public interface AddAllCheckedListener{
        void onComplete(boolean b);

    }
}
