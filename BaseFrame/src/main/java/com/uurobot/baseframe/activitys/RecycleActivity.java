package com.uurobot.baseframe.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.adapter.ITRecyleViewAdapter;
import com.uurobot.baseframe.adapter.RecycleViewItemDivide;
import com.uurobot.baseframe.adapter.TTRecyleViewAdapter;
import com.uurobot.baseframe.adapter.base.BaseAdapter;
import com.uurobot.baseframe.bean.ITBean;
import com.uurobot.baseframe.bean.TTBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/5/25.
 */

public class RecycleActivity extends BaseActivity {
    public static final String TYPE = "type";
    public static String[] RecycleType = {"IT", "TT"};
    private RecyclerView recyclerView;
    private BaseAdapter recycleViewAdapter;
    private ItemTouchHelper mItemTouchHelper;
    List<TTBean> data = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        parseIntent();
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycleview_recycle_activity);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new RecycleViewItemDivide(4));
        recyclerView.setAdapter(recycleViewAdapter);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void parseIntent() {
        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            /**
             * 是否处理滑动事件 以及拖拽和滑动的方向 如果是列表类型的RecyclerView的只存在UP和DOWN，如果是网格类RecyclerView则还应该多有LEFT和RIGHT
             * @param recyclerView
             * @param viewHolder
             * @return
             */
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                } else {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    final int swipeFlags = 0;
//                    final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                    return makeMovementFlags(dragFlags, swipeFlags);
                }
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //得到当拖拽的viewHolder的Position
                int fromPosition = viewHolder.getAdapterPosition();
                //拿到当前拖拽到的item的viewHolder
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(data, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(data, i, i - 1);
                    }
                }
                recycleViewAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });

        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(TYPE);
        if (RecycleType[0].equals(stringExtra)) {
            List<ITBean> data = new ArrayList<>();
            data.add(new ITBean(R.drawable.sensor_bright, "hello"));
            data.add(new ITBean(R.drawable.sensor_bright, "hello"));
            data.add(new ITBean(R.drawable.sensor_touch, "hello"));
            data.add(new ITBean(R.drawable.sensor_touch, "hello"));
            recycleViewAdapter = new ITRecyleViewAdapter(data);
        } else {

            data.add(new TTBean("hello1", "world1"));
            data.add(new TTBean("hello2", "world2"));
            data.add(new TTBean("hello3", "world3"));
            data.add(new TTBean("hello4", "world4"));
            data.add(new TTBean("hello5", "world5"));
            recycleViewAdapter = new TTRecyleViewAdapter(data);
            recycleViewAdapter.setItemOnClick(new BaseAdapter.ItemOnClick() {
                @Override
                public void onLongClick(RecyclerView.ViewHolder holder) {
                    Toast.makeText(RecycleActivity.this, "holder=" + holder.getLayoutPosition(), Toast.LENGTH_SHORT).show();
                    if (holder.getLayoutPosition() != 0 && holder.getLayoutPosition() != 1) {
                        mItemTouchHelper.startDrag(holder);
                    }
                }
            });
        }
    }
}
