package com.example.zwq.qqfragment.bean;

/**
 * Created by zhuwenqiang on 2016/9/7.
 */
public class ChatInfo {
    String name;
    String content;

    public ChatInfo() {
    }

    public ChatInfo(String name, String content, boolean flag) {
        this.name = name;
        this.content = content;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    boolean flag;
}
