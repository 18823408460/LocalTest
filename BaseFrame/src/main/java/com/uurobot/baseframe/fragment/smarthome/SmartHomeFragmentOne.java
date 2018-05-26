package com.uurobot.baseframe.fragment.smarthome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.base.BaseFragment;
import com.uurobot.baseframe.utils.DataUtils;
import com.uurobot.baseframe.utils.EAnimType;
import com.uurobot.baseframe.view.AnimImageView;
import com.uurobot.baseframe.view.ImageTextView;

/**
 * Created by Administrator on 2018/5/23.
 */

public class SmartHomeFragmentOne extends BaseFragment implements View.OnClickListener {
    private AnimImageView imageView;
    private ImageTextView imageTextViewDengguang, imageTextViewShidu, imageTextViewWendu, imageTextViewGanying;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragmet_smarthome_one, container, false);
        Button button2 = inflate.findViewById(R.id.btn_switch);
        button2.setOnClickListener(this);
        Button button3 = inflate.findViewById(R.id.btn_setShidu);
        button3.setOnClickListener(this);
        Button button4 = inflate.findViewById(R.id.btn_setWendu);
        button4.setOnClickListener(this);
        Button button5 = inflate.findViewById(R.id.btn_setDengguang);
        button5.setOnClickListener(this);
        Button button6 = inflate.findViewById(R.id.btn_setGanying);
        button6.setOnClickListener(this);
        imageTextViewDengguang = inflate.findViewById(R.id.imagetextview_dengguang);
        imageTextViewShidu = inflate.findViewById(R.id.imagetextview_shidu);
        imageTextViewWendu = inflate.findViewById(R.id.imagetextview_wendu);
        imageTextViewGanying = inflate.findViewById(R.id.imagetextview_gangying);
        imageView = inflate.findViewById(R.id.imageview_dialog);
        imageView.startAnim();
        return inflate;
    }

    private EAnimType[] eAnimTypes = {EAnimType.OpenDoor, EAnimType.OpenLamp, EAnimType.OpenAirCondition, EAnimType.OpenTv};
    private int index;

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_switch:
                imageView.updateAnim(eAnimTypes[index++ % (eAnimTypes.length)]);
                break;
            case R.id.btn_setDengguang:
                updateDengGunag(DataUtils.floatTranslate((float) Math.random()));
                break;
            case R.id.btn_setShidu:
                updateShidu(DataUtils.floatTranslate((float) Math.random()));
                break;
            case R.id.btn_setWendu:
                updateWendu(DataUtils.floatTranslate((float) Math.random()));
                break;
            case R.id.btn_setGanying:
                updateGanying(DataUtils.floatTranslate((float) Math.random()));
                break;
        }
    }

    private void updateDengGunag(float data) {
        String lux = String.format("%s%s", String.valueOf(data), "lux");
        imageTextViewDengguang.updateDynaimcText(lux);
    }

    private void updateShidu(float data) {
        String lux = String.format("%s%s", String.valueOf(data), "");
        imageTextViewShidu.updateDynaimcText(lux);
    }

    private void updateWendu(float data) {
        String lux = String.format("%s%s", String.valueOf(data), "°C");
        imageTextViewWendu.updateDynaimcText(lux);
    }

    private void updateGanying(float data) {
        String lux = String.format("%s%s", String.valueOf(data), "");
        imageTextViewGanying.updateDynaimcText(lux);
    }

    @Override
    public void onPause() {
        super.onPause();
        imageView.stopAnim();
    }

    @Override
    public void onResume() {
        super.onResume();
        imageView.startAnim();
    }
}
