package com.example.zwq.qqfragment.bean;

import java.util.List;

/**
 * Created by zhuwenqiang on 2016/9/6.
 */
public class Group {
    String groupName;
    List<Children> list;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Children> getList() {
        return list;
    }

    public void setList(List<Children> list) {
        this.list = list;
    }
}
