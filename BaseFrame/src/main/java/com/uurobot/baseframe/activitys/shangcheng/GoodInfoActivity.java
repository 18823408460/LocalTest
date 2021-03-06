package com.uurobot.baseframe.activitys.shangcheng;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.uurobot.baseframe.R;
import com.uurobot.baseframe.activitys.BaseActivity;
import com.uurobot.baseframe.adapter.HomeFragmentAdapter;
import com.uurobot.baseframe.bean.shangcheng.GoodsBean;
import com.uurobot.baseframe.utils.CartStorage;

import java.util.List;

/**
 * Created by WEI on 2018/6/1.
 */
// 火狐 ：  android -layout_finder

/**
 * 数据传递--- 通过intent -- bean 序列化
 */
public class GoodInfoActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = GoodInfoActivity.class.getSimpleName();
    private ImageButton imageButtonMore;
    private WebView webView;
    private ImageButton imageButtonBack;
    private LinearLayout relativeLayout;
    private Button btn_addCar;
    private TextView textViewCar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData();
        setContentView(R.layout.activity_goodinfos);
        imageButtonMore = findViewById(R.id.img_btn_goodinfo_activity);
        imageButtonBack = findViewById(R.id.image_btn_back);
        btn_addCar = findViewById(R.id.btn_add_goods_car);
        relativeLayout = findViewById(R.id.layout_head_more_googinfo_activity);
        textViewCar = findViewById(R.id.tv_car);
        textViewCar.setOnClickListener(this);
        imageButtonMore.setOnClickListener(this);
        imageButtonBack.setOnClickListener(this);
        btn_addCar.setOnClickListener(this);
        initWeView();
    }
    private void initWeView() {
        webView = findViewById(R.id.webview_goodinfo_activity);
        //        webView.loadUrl("www.baidu.com");
        webView.loadUrl("http://www.baidu.com");
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == imageButtonMore) {
            if (relativeLayout.isShown()) {
                relativeLayout.setVisibility(View.GONE);
            } else {
                relativeLayout.setVisibility(View.VISIBLE);
            }
        } else if (v == imageButtonBack) {
            finish();
        } else if (v == btn_addCar) {
            Toast.makeText(this, "add car", Toast.LENGTH_SHORT).show();
            CartStorage.getCartStorage().addData(goodsBean);
        } else if (v == textViewCar) {
            startActivity(new Intent(this, GoodCarActivity.class));
        }
    }

    private GoodsBean goodsBean;

    public void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra(HomeFragmentAdapter.GOODSINFO);
            goodsBean = JSON.parseObject(stringExtra, GoodsBean.class);
            Log.e(TAG, "goodsBean: " + goodsBean.toString());
        }
    }
}
