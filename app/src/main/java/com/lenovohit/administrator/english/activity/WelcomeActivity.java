package com.lenovohit.administrator.english.activity;

import android.view.Window;
import android.view.WindowManager;

import com.lenovohit.administrator.english.R;
import com.lenovohit.administrator.english.domain.BmobUser;


/**
 * Created by Administrator on 2017-04-28.
 */

public class WelcomeActivity extends BaseActivity {
    @Override
    public void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welecome_layout);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    LoginActivity.startLoginActivity(WelcomeActivity.this,new BmobUser());
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
