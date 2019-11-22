package com.xiaodou.common.adapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

/**
 *  多部分的adapter
 * */
public class ListSectionAdapter extends BaseSectionQuickAdapter<SectionEntity, BaseViewHolder> {

    public ListSectionAdapter(int layoutResId, int sectionHeadResId, List<SectionEntity> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionEntity item) {
//        helper.setText(R.id.header, item.header);
//        helper.setVisible(R.id.more, item.isMore());
//        helper.addOnClickListener(R.id.more);//添加点击事件
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionEntity item) {
//        Video video = (Video) item.t;
//        switch (helper.getLayoutPosition() % 2) {
//            case 0:
//                helper.setImageResource(R.id.item_multiple_img, R.mipmap.ic4);
//                break;
//            case 1:
//                helper.setImageResource(R.id.item_multiple_img, R.mipmap.ic4);
//                break;
//
//        }
//        helper.setText(R.id.tv, video.getName());
    }
}
