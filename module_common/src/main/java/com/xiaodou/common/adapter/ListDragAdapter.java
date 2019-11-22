package com.xiaodou.common.adapter;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaodou.common.R;

import java.util.List;

/**
 *  可以拖拽的adapter
 * */
public class ListDragAdapter extends BaseItemDraggableAdapter<String, BaseViewHolder> {
    public ListDragAdapter(List<String> data) {
        super(R.layout.item_img_text_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_multiple_tv,item);
    }
}
