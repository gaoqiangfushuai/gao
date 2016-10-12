package com.example.zwq.mygiftapp.bean;

import java.util.List;

/**
 * Created by zhuwenqiang on 2016/10/5.
 */
public class Group {
    String groupName;
    List<KaiFuInfo.InfoBean> list;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<KaiFuInfo.InfoBean> getList() {
        return list;
    }

    public void setList(List<KaiFuInfo.InfoBean> list) {
        this.list = list;
    }
}
