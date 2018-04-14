package com.unisrobot.firstmodule.fragmentD;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unisrobot.firstmodule.R;
import com.unisrobot.firstmodule.dialog.LoadingDialog;

/**
 * Created by WEI on 2018/4/14.
 */

public class DynamicFragment extends Fragment {
    private static final String TAG = "DynamicFragment";
    private Context context;
    /**
     * rootView是否初始化标志，防止回调函数在rootView为空的时候触发
     */
    private boolean hasCreateView;

    /**
     * 当前Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发
     */
    private boolean isFragmentVisible;

    /**
     * onCreateView()里返回的view，修饰为protected,所以子类继承该类时，在onCreateView里必须对该变量进行初始化
     */
    protected View rootView;

    public static Fragment newInstance(String showMsg) {
        DynamicFragment dynamicFragment = new DynamicFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tag", showMsg);
        dynamicFragment.setArguments(bundle);
        return dynamicFragment;
    }
    // Avoid non-default constructors in fragments: use a default constructor plus Fragment#setArguments(Bundle) instead
//    public DynamicFragment(String tag) {
//        this.showMsg = tag;
//    }

    private String tag;

    //每一个Fragment只会调用一次。。。
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        String tag = arguments.getString("tag");
        this.tag = tag;
        context = getContext();
        initVariable();
        Log.e(TAG, "onCreate: ....................." + tag);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " + tag);
    }

    // 这个方法在 onCreate 之前调用。。第一次
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e(TAG, "setUserVisibleHint: " + isVisibleToUser + "    " + tag + "   " + isVisible());
        /**
         * 判断此Fragment是否正在前台显示
         * 通过判断就知道是否要进行数据加载了
         */
        if (isVisibleToUser && isVisible()) {
            // 加载数据
        }
        if (rootView == null) {
            return;
        }
        hasCreateView = true;
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if (isFragmentVisible) {
            onFragmentVisibleChange(false);
            isFragmentVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!hasCreateView && getUserVisibleHint()) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
        }
    }

    private void initVariable() {
        hasCreateView = false;
        isFragmentVisible = false;
    }

    protected void onFragmentVisibleChange(boolean isVisible) {
        Log.e(TAG, "onFragmentVisibleChange -> isVisible: " + isVisible + "   " + tag);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " + tag);
    }


    // show ,hidder, 会调用这个方法，，原本需要在onResume以及onPause方法做的事情就可以迁移到 onHiddenChanged时进行管理
    // 这种方式在第一次显示的不会调用，要切换几次后才会调用。。。。
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        boolean visible = isVisible();
        Log.e(TAG, "onHiddenChanged: " + hidden + "      " + tag + "  visible=" + visible);

        if (hidden) {
            // hidderLoadingDialog();
        } else {
            //showLoadingDialog();
        }
    }

    private LoadingDialog loadingDialog;

    private void showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(context, R.style.first_module_loadingdialog);
        }
        loadingDialog.show(tag);
    }

    private void hidderLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }


    // 限制预加载，会出现滑动过程中卡顿现象。其实Fragment中防止预加载主要是防止数据的预加载，
    // Fragment中的View预加载是有好处的，我们可以通过Fragment中的一个方法来达到预加载View 但是不加载数据
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: " + tag);
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.first_module_fragment_dynamic, container, false);
            TextView textView = rootView.findViewById(R.id.first_module_tv_dynamic);
            textView.setText(tag);
        }
        return rootView;
    }
}
