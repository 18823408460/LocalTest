package com.uurobot.baseframe.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.adapter.base.BaseAdapter;
import com.uurobot.baseframe.adapter.holder.TTHolder;
import com.uurobot.baseframe.bean.TTBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/25.
 */
// 每个Item = Textview + Textiview

public class TTRecyleViewAdapter extends BaseAdapter<TTBean> {

    public TTRecyleViewAdapter(List<TTBean> stringList) {
        this.stringList = stringList;
    }


    @Override
    protected RecyclerView.ViewHolder loadView(ViewGroup parent) {
        View inflate = layoutInflater.inflate(R.layout.recycleview_item_tt, parent, false);
        return new TTHolder(inflate);
    }

    @Override
    protected void setViewData(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TTHolder) {
            ((TTHolder) holder).contentTextView.setText(stringList.get(position).getContent());
            ((TTHolder) holder).titleTextView.setText(stringList.get(position).getTitle());
            ((TTHolder) holder).rootVIew.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (itemOnClick != null) {
                        itemOnClick.onLongClick(holder);
                    }
                    return true;
                }
            });
        }
    }


}
