package com.uurobot.baseframe.activitys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.dialog.alert.AlertItem;
import com.uurobot.baseframe.dialog.alert.OnDismissListener;
import com.uurobot.baseframe.dialog.alert.OnItemClickListener;
import com.uurobot.baseframe.dialog.alert.OnShowListener;
import com.uurobot.baseframe.dialog.alert.TitleAlertItem;
import com.uurobot.baseframe.dialog.alert.UIActionList;
import com.uurobot.baseframe.dialog.alert.UIActionSheet;
import com.uurobot.baseframe.dialog.alert.UIAlertView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/8.
 */

public class TestDialogActivity extends Activity {
        List<AlertItem> actions = new ArrayList<>();

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_test_dialog);
                initData();
                initView();
        }

        private void initView() {
                findViewById(R.id.showdialog).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View pView) {
                                new UIAlertView(TestDialogActivity.this)
                                        .setTitle(new TitleAlertItem("Ttile", R.drawable.ic_launcher, TitleAlertItem.Align.left))
                                        .setMessage(new AlertItem("这个地方是放提示的！"))
                                        .setOk(new AlertItem("OK", Color.RED))
                                        .setCancelable(true)
                                        .setCanceledOnTouchOutside(true)
                                        .setOnShowListener(new OnShowListener() {
                                                @Override
                                                public void onShow() {
                                                        Toast.makeText(TestDialogActivity.this, "UIAlertView 显示", Toast.LENGTH_SHORT).show();
                                                }
                                        })
                                        .setOnItemClickListener(new OnItemClickListener() {
                                                @Override
                                                public void onItemClick(View pView, int position) {
                                                        Toast.makeText(TestDialogActivity.this, "UIAlertView 点击了>" + position, Toast.LENGTH_SHORT).show();
                                                }
                                        })
                                        .build()
                                        .show();
                                ;
                        }
                });
                findViewById(R.id.showvalert).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View pView) {
                                new UIActionList(TestDialogActivity.this)
                                        .setTitle(new TitleAlertItem("Ttile", R.drawable.ic_launcher, TitleAlertItem.Align.left))
                                        .setMessage(new AlertItem("msg"))
                                        .setActions(actions)
                                        .setCancelable(true)
                                        .setCanceledOnTouchOutside(true)
                                        .setOnShowListener(new OnShowListener() {
                                                @Override
                                                public void onShow() {
                                                        Log.e("----------->", "----->show");
                                                }
                                        })
                                        .setOnDismissListener(new OnDismissListener() {
                                                @Override
                                                public void onAlertDismiss() {
                                                        Toast.makeText(TestDialogActivity.this, "UIActionList Dismiss 了", Toast.LENGTH_SHORT).show();
                                                }
                                        })
                                        .build()
                                        .show();
                                ;
                        }
                });
                findViewById(R.id.showActionSheet).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View pView) {

                                new UIActionSheet(TestDialogActivity.this)
                                        .setTitle(new TitleAlertItem("", R.drawable.ic_launcher, TitleAlertItem.Align.left))
                                        .setMessage(new AlertItem("这是一个提示语"))
                                        .setActions(actions)
                                        .setCancelable(true)
                                        .setOnShowListener(new OnShowListener() {
                                                @Override
                                                public void onShow() {
                                                        Log.e("----------->", "----->show");
                                                }
                                        })
                                        .setOnItemClickListener(new OnItemClickListener() {
                                                @Override
                                                public void onItemClick(View pView, int position) {
                                                        Toast.makeText(TestDialogActivity.this, "UIActionSheet 点击了>" + position, Toast.LENGTH_SHORT).show();
                                                }
                                        })
                                        .setOnDismissListener(new OnDismissListener() {
                                                @Override
                                                public void onAlertDismiss() {
                                                        Log.e("----------->", "----->Dismiss");
                                                }
                                        })
                                        .build()
                                        .show();
                        }
                });


                final View decorView = getWindow().getDecorView();
                final FrameLayout frameLayoutWindow = decorView.findViewById(android.R.id.content);
                final View layoutParent = View.inflate(TestDialogActivity.this, R.layout.layout_ui_alert_root, null);
                final FrameLayout frameLayoutRoot = layoutParent.findViewById(R.id.alert_root);
                FrameLayout frameLayoutContent = layoutParent.findViewById(R.id.alert_content);

                View view = View.inflate(TestDialogActivity.this, R.layout.layout_ui_alert, null);
                frameLayoutContent.addView(view);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.gravity = Gravity.CENTER;
                layoutParams.leftMargin = 20;
                layoutParams.rightMargin = 20;
                frameLayoutContent.setLayoutParams(layoutParams);

                // 这么简单的做，它是接受不到 触摸事件的，， 可以直接在   mainActivity布局中做
                findViewById(R.id.showMyDialog).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                if (!isShow) {
                                        frameLayoutWindow.addView(frameLayoutRoot);
                                } else {
                                        frameLayoutWindow.removeView(frameLayoutRoot);
                                }
                                isShow = !isShow;
                        }
                });

                findViewById(R.id.showSysDialog).setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onClick(View v) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(TestDialogActivity.this);
                                builder.setTitle("title");
                                builder.setMessage("msg");
                                builder.setNegativeButton("confirm", null);
                                builder.setPositiveButton("cancel", null);
                                builder.show();
                        }
                });


                findViewById(R.id.showSysDialog2).setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onClick(View v) {
                                Dialog dialog = new Dialog(TestDialogActivity.this, R.style.fragmentDialog);
                                LinearLayout relativeLayout = (LinearLayout) View.inflate(TestDialogActivity.this, R.layout.layout_ui_alert, null);
                                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                layoutParams1.gravity = Gravity.BOTTOM;
                                layoutParams1.leftMargin = 30;
                                layoutParams1.rightMargin = 30;
                                relativeLayout.setLayoutParams(layoutParams1);
                                dialog.setContentView(relativeLayout);
                                dialog.show();
                        }
                });
        }

        boolean isShow = false;

        private void initData() {
                actions.add(new AlertItem("Action1", Color.RED));
                actions.add(new AlertItem("Action2", Color.BLUE));
                actions.add(new AlertItem("Action3", Color.BLACK));
                actions.add(new AlertItem("Action4", Color.GRAY));
                actions.add(new AlertItem("Action5"));
                actions.add(new AlertItem("Action6"));
                actions.add(new AlertItem("Action7"));
                actions.add(new AlertItem("Action8"));
        }
}
