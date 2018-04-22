package com.unisrobot.firstmodule.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unisrobot.firstmodule.R;


/**
 * Created by WEI on 2018/4/22.
 */

/**
 * 组合view
 */
public class CenterSearchView extends LinearLayout implements TextWatcher,View.OnClickListener{
    private EditText editText ;
    private Button button ;
    public CenterSearchView(Context context) {
        this(context,null);
    }

    public CenterSearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public CenterSearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.first_module_center_searchview,this,true);
        button = findViewById(R.id.search_delete);
        button.setOnClickListener(this);
        editText  = findViewById(R.id.search_edittext);
        editText.addTextChangedListener(this);
        button.setVisibility(View.GONE);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String s1 = editText.getText().toString();
        if (TextUtils.isEmpty(s1)){
            button.setVisibility(View.GONE);
        }else {
            button.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        editText.setText("");
    }
}
