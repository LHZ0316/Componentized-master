package com.xiaodou.common.adapter;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */

public class ItemNormalEntity {

    public static final int SINGLE_TEXT = 1;
    public static final int SINGLE_IMG = 2;
    public static final int TEXT_IMG = 3;

    public int type;
    public String content;

    public ItemNormalEntity(int type) {
        this.type = type;
    }

    public ItemNormalEntity(int type, String content) {
        this.type = type;
        this.content = content;
    }
}
