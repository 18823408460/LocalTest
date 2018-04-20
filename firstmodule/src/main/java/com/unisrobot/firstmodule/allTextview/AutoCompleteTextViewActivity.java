package com.unisrobot.firstmodule.allTextview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.unisrobot.firstmodule.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/20.
 */

public class AutoCompleteTextViewActivity extends AppCompatActivity {
        private String[] res = {"beijing1", "beijing2", "beijing3", "shanghai1", "shanghai2", "guangzhou1", "shenzhen"};

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.first_module_activity_taxtview);
                AutoCompleteTextView viewById = findViewById(R.id.first_module_autocompletetextview);

                ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,R.layout.first_module_item_autocomplete,res);
                viewById.setAdapter(stringArrayAdapter);
        }
}
