package com.usvb.mobile.andriod.mhaneef.myp.data;

import java.sql.Timestamp;

/**
 * Created by mhaneef on 11/20/17.
 * Class is for holding the settings data
 */

public class Settings {
    private Long ID;
    private String settingName;
    private String settingValue;
    private Timestamp modifiedTime;

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
}
