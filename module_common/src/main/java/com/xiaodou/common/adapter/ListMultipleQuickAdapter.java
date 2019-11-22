package com.xiaodou.common.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaodou.common.R;

import java.util.List;
/**
 *  多条目的adapter
 * */
public class ListMultipleQuickAdapter extends BaseMultiItemQuickAdapter<ItemMultipleEntity, BaseViewHolder> {

    public ListMultipleQuickAdapter(Context context, List data) {
        super(data);
        addItemType(ItemMultipleEntity.TEXT, R.layout.item_text_view);
        addItemType(ItemMultipleEntity.IMG, R.layout.item_image_view);
        addItemType(ItemMultipleEntity.IMG_TEXT, R.layout.item_img_text_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemMultipleEntity item) {
        switch (helper.getItemViewType()) {
            case ItemMultipleEntity.TEXT:
                helper.setText(R.id.item_multiple_tv, item.getContent());
                break;
            case ItemMultipleEntity.IMG:
//                helper.setImageResource(R.id.item_multiple_img,);
                break;
            case ItemMultipleEntity.IMG_TEXT:
                switch (helper.getLayoutPosition() % 2) {
                    case 0:
                        helper.setImageResource(R.id.item_multiple_img, R.drawable.ic_logo_application);
                        break;
                    case 1:
                        helper.setImageResource(R.id.item_multiple_img, R.drawable.ic_logo_application);
                        break;
                }
                break;
        }
    }
}
