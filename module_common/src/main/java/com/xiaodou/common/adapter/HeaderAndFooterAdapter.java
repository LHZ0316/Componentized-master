package com.xiaodou.common.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaodou.common.R;

import java.util.List;

public class HeaderAndFooterAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public HeaderAndFooterAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.item_multiple_tv,item);
    }
}
