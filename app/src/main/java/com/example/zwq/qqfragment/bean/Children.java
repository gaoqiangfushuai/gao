package com.example.zwq.qqfragment.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhuwenqiang on 2016/9/6.
 */
public class Children implements Parcelable {
    String childName;

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public Children() {
    }

    public static final Creator<Children> CREATOR = new Creator<Children>() {
        @Override
        public Children createFromParcel(Parcel in) {
            String childName = in.readString();
            Children children = new Children();
            children.setChildName(childName);
            return children;
        }

        @Override
        public Children[] newArray(int size) {
            return new Children[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(childName);
    }
}
