package com.xiaodou.common.adapter;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;


/**
 * Created by luoxw on 2016/8/10.
 */
public class Level0Item extends AbstractExpandableItem<Level1Item> implements MultiItemEntity {
    public String title;
    public String subTitle;

    public Level0Item(String title, String subTitle) {
        this.subTitle = subTitle;
        this.title = title;
    }

    // 用于区分条目类型
    @Override
    public int getItemType() {
        return ListExpandableAdapter.TYPE_LEVEL_0;
    }

    // 用于区分list中的等级
    @Override
    public int getLevel() {
        return 0;
    }
}
