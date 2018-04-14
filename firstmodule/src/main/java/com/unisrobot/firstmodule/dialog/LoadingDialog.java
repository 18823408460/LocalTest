package com.unisrobot.firstmodule.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.unisrobot.firstmodule.R;
import com.unisrobot.firstmodule.utils.ScreenUtil;

/**
 * Created by WEI on 2018/4/14.
 */

public class LoadingDialog extends Dialog {
    private Context context;
    private TextView textView;

    public LoadingDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        initData();
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        initData();
    }

    private void initData() {
        setContentView(R.layout.first_module_dialog_loading);
        textView = findViewById(R.id.first_module_dialog_loading_tv);
        setCanceledOnTouchOutside(false);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = ScreenUtil.getwindth(context) / 2;
        window.setAttributes(attributes);
    }

    public void show(String msg) {
        textView.setText(msg);
        show();
    }
}
