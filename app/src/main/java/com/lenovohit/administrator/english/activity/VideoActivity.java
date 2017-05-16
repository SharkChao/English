package com.lenovohit.administrator.english.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovohit.administrator.english.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017-05-13.
 */

public class VideoActivity extends BaseActivity {
    @Bind(R.id.ivBack)
    LinearLayout back;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.btn_danci)
    Button btnDanci;
    @Bind(R.id.btn_tingli)
    Button btnTingli;
    @Bind(R.id.btn_kouyu)
    Button btnKouyu;
    @Bind(R.id.btn_yuedu)
    Button btnYuedu;
    @Bind(R.id.btn_duanwen)
    Button btnDuanwen;
    @Bind(R.id.btn_xiaoshuo)
    Button btnXiaoshuo;
    @Bind(R.id.btn_siji)
    Button btnSiji;
    @Bind(R.id.btn_liuji)
    Button btnLiuji;
    @Bind(R.id.btn_kaoyan)
    Button btnKaoyan;
    @Bind(R.id.btn_hangye)
    Button btnHangye;
    @Bind(R.id.btn_zhichang)
    Button btnZhichang;
    @Bind(R.id.btn_shangwu)
    Button btnShangwu;

    @Override
    public void initView() {
        setContentView(R.layout.fragment_video);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static void startVideoActivity(Context context) {
        context.startActivity(new Intent(context, VideoActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    public void click(View view){
        Ttem2Activity.startTtem2Activity(this);
    }
}
