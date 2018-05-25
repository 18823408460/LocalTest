package com.uurobot.baseframe.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.adapter.base.BaseAdapter;
import com.uurobot.baseframe.adapter.holder.ITHolder;
import com.uurobot.baseframe.bean.ITBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/25.
 */
// 每个Item = ImageView + Textiview
public class ITRecyleViewAdapter extends BaseAdapter<ITBean> {

        public ITRecyleViewAdapter(List<ITBean> stringList) {
                this.stringList = stringList;
        }

        @Override
        protected RecyclerView.ViewHolder loadView(ViewGroup parent) {
                View inflate = layoutInflater.inflate(R.layout.recycleview_item_it, parent,false);
                return new ITHolder(inflate);
        }

        @Override
        protected void setViewData(RecyclerView.ViewHolder holder, int position) {
                if (holder instanceof ITHolder) {
                        ((ITHolder) holder).contentTextView.setText(stringList.get(position).getContent());
                        ((ITHolder) holder).titleImageView.setImageResource(stringList.get(position).getResId());
                }
        }
}
