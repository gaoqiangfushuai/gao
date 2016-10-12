package com.example.zwq.mygiftapp.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zwq.mygiftapp.R;
import com.example.zwq.mygiftapp.bean.Group;
import com.example.zwq.mygiftapp.bean.KaiFuInfo;
import com.example.zwq.mygiftapp.http.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zhuwenqiang on 2016/10/4.
 */
public class MyKaiFuAdapter implements ExpandableListAdapter {
    List<Group> groupList;
    Context context;

    public MyKaiFuAdapter(List<Group> groupList, Context context) {
        this.groupList = groupList;
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
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Group group = groupList.get(groupPosition);
        List<KaiFuInfo.InfoBean> info = group.getList();
        return info.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Group group = groupList.get(groupPosition);
        List<KaiFuInfo.InfoBean> info = group.getList();
        return info.get(childPosition);
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.kaifu_tag_item, null);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tv_tag = (TextView) convertView.findViewById(R.id.tv_kaifu_tag);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        String groupName = groupList.get(groupPosition).getGroupName();
        groupViewHolder.tv_tag.setText(groupName);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ContentViewHolder contentViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.kaifu_content_item, null);
            contentViewHolder = new ContentViewHolder();
            contentViewHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_kaifu_date);
            contentViewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_kaifu_name);
            contentViewHolder.tv_area = (TextView) convertView.findViewById(R.id.tv_kaifu_area);
            contentViewHolder.tv_company = (TextView) convertView.findViewById(R.id.tv_kaifu_company);
            contentViewHolder.img_content = (ImageView) convertView.findViewById(R.id.img_kaifu_content);
            convertView.setTag(contentViewHolder);
        } else {
            contentViewHolder = (ContentViewHolder) convertView.getTag();
        }
        String path = groupList.get(groupPosition).getList().get(childPosition).getIconurl();
        String date = groupList.get(groupPosition).getList().get(childPosition).getLinkurl();
        String area = groupList.get(groupPosition).getList().get(childPosition).getArea();
        String company = groupList.get(groupPosition).getList().get(childPosition).getOperators();
        String name = groupList.get(groupPosition).getList().get(childPosition).getGname();
        Picasso.with(context).load(HttpUtils.BASE_URL + path).into(contentViewHolder.img_content);
        contentViewHolder.tv_date.setText(date);
        contentViewHolder.tv_name.setText(name);
        contentViewHolder.tv_company.setText(company);
        contentViewHolder.tv_area.setText(area);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
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

    class GroupViewHolder {
        TextView tv_tag;
    }

    class ContentViewHolder {
        ImageView img_content;
        TextView tv_date,tv_name,tv_company,tv_area;
    }

}
