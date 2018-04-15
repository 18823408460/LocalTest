package com.unisrobot.firstmodule.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
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

/**
 * DialogFragment  == fragment， 然后里面包含了一个 dialog、
 */
public class LoadingDialogFragment extends DialogFragment {
    private Context context;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.first_module_dialog_loading, null);
        textView = inflate.findViewById(R.id.first_module_dialog_loading_tv);
        return inflate;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}
