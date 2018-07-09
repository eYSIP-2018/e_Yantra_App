package com.example.rajneesh.googlesignin;

import com.google.gson.annotations.SerializedName;

public class CalenderResponse {
 @SerializedName("date")
String dat;
 @SerializedName("description")
    String desc;
 @SerializedName("initiative")
    String init;

    public CalenderResponse(String dat, String desc, String init) {
        this.dat = dat;
        this.desc = desc;
        this.init = init;
    }

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getInit() {
        return init;
    }

    public void setInit(String init) {
        this.init = init;
    }
}
