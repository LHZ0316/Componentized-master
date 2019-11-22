package com.xiaodou.common.adapter;

import android.provider.MediaStore;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class ItemSectionEntity extends SectionEntity<MediaStore.Video> {
    private boolean isMore;
    public ItemSectionEntity(boolean isHeader, String header, boolean isMroe) {
        super(isHeader, header);
        this.isMore = isMroe;
    }

    public ItemSectionEntity(MediaStore.Video t) {
        super(t);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean mroe) {
        isMore = mroe;
    }
}
