package com.example.zwq.qqfragment.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zwq.qqfragment.R;
import com.example.zwq.qqfragment.bean.Children;
import com.example.zwq.qqfragment.bean.Group;

import java.util.List;

/**
 * Created by zhuwenqiang on 2016/9/6.
 */
public class MyAdapter implements ExpandableListAdapter{
    List<Group> list;
    Context context;
    public MyAdapter(List<Group> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getList().size();
    }

    @Override
    public Group getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Children getChild(int groupPosition, int childPosition) {
        Group group = list.get(groupPosition);
        Children children = group.getList().get(childPosition);
        return children;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.group,null);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.textView = (TextView) convertView.findViewById(R.id.tv_group);
            convertView.setTag(groupViewHolder);
        }else{
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.textView.setText(list.get(groupPosition).getGroupName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.child,null);
            childViewHolder = new ChildViewHolder();
            childViewHolder.textView2 = (TextView) convertView.findViewById(R.id.tv_child);
            convertView.setTag(childViewHolder);
        }else{
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.textView2.setText(list.get(groupPosition).getList().get(childPosition).getChildName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
    class GroupViewHolder{
        TextView textView;
    }
    class ChildViewHolder{
        TextView textView2;
        ImageView imageView;
    }
}
