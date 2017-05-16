package com.lenovohit.administrator.english.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017-04-28.
 */

public abstract  class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        ButterKnife.bind(this);
        initData();
        initEvent();
    }
    public abstract  void initView();
    public abstract  void initData();
    public abstract  void initEvent();
}
