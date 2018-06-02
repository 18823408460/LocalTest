package com.uurobot.baseframe.adapter.holder;

import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uurobot.baseframe.R;
import com.uurobot.baseframe.adapter.HomeFragmentAdapter;
import com.uurobot.baseframe.bean.shangcheng.ResponseBean;
import com.uurobot.baseframe.utils.Constans;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */

public class SecKillViewHoler extends BaseHolder {
        private static final String TAG = SecKillViewHoler.class.getSimpleName();
        private TextView textView;
        private GridView gridView;
        private LayoutInflater layoutInflater;
        private CountDownTimer countDownTimer;

        public SecKillViewHoler(View itemView, IHolderLisenter iHolderLisenter) {
                super(itemView);
                textView = itemView.findViewById(R.id.tv_secKill_fragment);
                gridView = itemView.findViewById(R.id.gridview_secKill_fragment);
                gridView.setNumColumns(3);
                layoutInflater = LayoutInflater.from(itemView.getContext());
                this.iHolderLisenter = iHolderLisenter;
        }

        public void setData(final ResponseBean.ResultBean.SeckillInfoBean data) {
                Log.e(TAG, "setData: " + data);
                final List<ResponseBean.ResultBean.SeckillInfoBean.ListBean> list = data.getList();
                long diff = Long.parseLong(data.getEnd_time()) - Long.parseLong(data.getStart_time());

                startCountDown(diff);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                iHolderLisenter.onClick(HomeFragmentAdapter.HolderType.Seckill, position);
                        }
                });
                gridView.setAdapter(new BaseAdapter() {
                        @Override
                        public int getCount() {
                                return list.size();
                        }

                        @Override
                        public Object getItem(int position) {
                                return list.get(position);
                        }

                        @Override
                        public long getItemId(int position) {
                                return position;
                        }

                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                                GridHolder gridHolder = null;
                                if (convertView == null) {
                                        convertView = layoutInflater.inflate(R.layout.gridview_seckill_item, null);
                                        gridHolder = new GridHolder();
                                        gridHolder.imageView = convertView.findViewById(R.id.imageview_gridview_seckill);
                                        gridHolder.textViewNormalPrice = convertView.findViewById(R.id.tv_normal_gridview_seckill);
                                        gridHolder.textViewKillPrice = convertView.findViewById(R.id.tv_convert_gridview_seckill);
                                        convertView.setTag(gridHolder);
                                } else {
                                        gridHolder = (GridHolder) convertView.getTag();
                                }
                                Glide.with(convertView.getContext())
                                        .load(Constans.IMAGE_BASE_URL + list.get(position).getFigure())
                                        .into(gridHolder.imageView);
                                gridHolder.textViewNormalPrice.setText(list.get(position).getOrigin_price());
                                gridHolder.textViewKillPrice.setText(list.get(position).getCover_price());
                                return convertView;
                        }
                });
        }

        private void startCountDown(final long diff) {
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                String time = simpleDateFormat.format(diff);
                textView.setText(time);
                countDownTimer = new CountDownTimer(diff, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                                Log.e(TAG, "onTick: " + millisUntilFinished);
                                String time1 = simpleDateFormat.format(millisUntilFinished);
                                textView.setText(time1);
                        }

                        @Override
                        public void onFinish() {

                        }
                };
                countDownTimer.start();
        }

        private class GridHolder {
                public ImageView imageView;
                public TextView textViewNormalPrice;
                public TextView textViewKillPrice;
        }
}
