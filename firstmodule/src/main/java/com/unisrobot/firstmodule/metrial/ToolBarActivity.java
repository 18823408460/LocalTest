package com.unisrobot.firstmodule.metrial;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.unisrobot.firstmodule.R;

import org.w3c.dom.Text;

/**
 * Created by WEI on 2018/4/15.
 */

/**
 * 1. Toolbar是为了替换 Actionbar，ActionBar使用起来不怎么灵活，而 Toolbar可以自由往里面添加控件
 */
public class ToolBarActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar mToolbar;
    Toast mToast;
    PopupWindow mPopupWindow;
    private TextView textView ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_module_activity_toolbar);
        textView = findViewById(R.id.tv);
        registerForContextMenu(textView);

        mToast = Toast.makeText(this, "", Toast.LENGTH_LONG);
        mToast.setGravity(Gravity.CENTER, 0, 0);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Title");
        mToolbar.setTitleTextColor(Color.YELLOW);
        mToolbar.setSubtitle("SubTitle");
        mToolbar.setSubtitleTextColor(Color.parseColor("#80ff0000"));

        //侧边栏的按钮
        mToolbar.setNavigationIcon(R.mipmap.first_module_ic_launcher);
        // 取代原本的 Actionbar
        setSupportActionBar(mToolbar);

        //设置NavigationIcon的点击事件,需要放在setSupportActionBar之后设置才会生效,
        //因为setSupportActionBar里面也会setNavigationOnClickListener
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToast.setText("click NavigationIcon");
                mToast.show();
            }
        });

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.action_edit:
                        mToast.setText("click edit");
                        break;
                    case R.id.action_share:
                        mToast.setText("click share");
                        break;
                    case R.id.action_overflow:
                        popUpMyOverflow();
                        return true;
                }
                mToast.show();
                return true;
            }
        });

        mToolbar.findViewById(R.id.btn_diy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToast.setText("点击自定义按钮");
                mToast.show();
            }
        });

    }

    /**
     * 弹出自定义的popWindow
     */
    public void popUpMyOverflow() {
        //获取状态栏高度
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        //状态栏高度+toolbar的高度
        int yOffset = frame.top + mToolbar.getHeight();
        if (null == mPopupWindow) {
            //初始化PopupWindow的布局
            View popView = getLayoutInflater().inflate(R.layout.first_module_popwindow, null);
            //popView即popupWindow的布局，ture设置focusAble.
            mPopupWindow = new PopupWindow(popView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, true);
            //必须设置BackgroundDrawable后setOutsideTouchable(true)才会有效
            mPopupWindow.setBackgroundDrawable(new ColorDrawable());
            //点击外部关闭。
            mPopupWindow.setOutsideTouchable(true);
            //设置一个动画。
            mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
            //设置Gravity，让它显示在右上角。
            mPopupWindow.showAtLocation(mToolbar, Gravity.RIGHT | Gravity.TOP, 0, yOffset);
            //设置item的点击监听
            popView.findViewById(R.id.ll_item1).setOnClickListener(this);
            popView.findViewById(R.id.ll_item2).setOnClickListener(this);
            popView.findViewById(R.id.ll_item3).setOnClickListener(this);
        } else {
            mPopupWindow.showAtLocation(mToolbar, Gravity.RIGHT | Gravity.TOP, 0, yOffset);
        }

    }

    // 这里不会调用
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        mToast.setText(itemId);
        mToast.show();
        return super.onOptionsItemSelected(item);
    }

    ////如果有Menu,创建完后,系统会自动添加到ToolBar上,只执行一次，创建后将不再被创建
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.first_module_menu, menu);
        return true;
    }

    // 这个和 方面的区别？？？？

    /**
     *  上下文菜单 它必须关联在一个view 上，并且在view 长按是会弹出,每次创建就被执行
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.first_module_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        mToast.setText(item.getTitle());
        mToast.show();
        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_item1:
                mToast.setText("哈哈");
                break;
            case R.id.ll_item2:
                mToast.setText("呵呵");
                break;
            case R.id.ll_item3:
                mToast.setText("嘻嘻");
                break;
        }
        //点击PopWindow的item后,关闭此PopWindow
        if (null != mPopupWindow && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
        mToast.show();
    }
}
