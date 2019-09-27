package com.lhz.android.libBaseCommon.cache;

import java.io.Serializable;

/**
 * 项目名称：MvpFrame
 * 创建人：Administrator
 * 创建时间：2019/9/27 11:21
 * 类描述：
 */
public class CacheBean implements Serializable{
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
}
