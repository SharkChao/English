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
 * Created by Administrator on 2017-05-12.
 */

public class Ttem3Activity extends BaseActivity {
    @Bind(R.id.ivBack)
    LinearLayout back;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.btn_jidanci)
    Button btnJidanci;
    @Bind(R.id.btn_yingyutingli)
    Button btnYingyutingli;
    @Bind(R.id.btn_yingyuyufa)
    Button btnYingyuyufa;
    @Bind(R.id.btn_gonggongyingyu)
    Button btnGonggongyingyu;
    @Bind(R.id.btn_kouyibiyi)
    Button btnKouyibiyi;
    @Bind(R.id.btn_jianqiaoyingyu)
    Button btnJianqiaoyingyu;
    @Bind(R.id.btn_siliuji)
    Button btnSiliuji;
    @Bind(R.id.btn_zhuansizhuanba)
    Button btnZhuansizhuanba;
    @Bind(R.id.btn_yasi)
    Button btnYasi;
    @Bind(R.id.btn_tuofu)
    Button btnTuofu;
    @Bind(R.id.btn_gre)
    Button btnGre;
    @Bind(R.id.btn_sat)
    Button btnSat;
    @Bind(R.id.btn_bec)
    Button btnBec;
    @Bind(R.id.btn_zhicheng)
    Button btnZhicheng;
    @Bind(R.id.btn_jinrong)
    Button btnJinrong;

    @Override
    public void initView() {
        setContentView(R.layout.activity_three);
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

    public static void startTtem3Activity(Context context) {
        context.startActivity(new Intent(context, Ttem3Activity.class));
    }

    public void click(View view) {

        switch (view.getId()) {
//		英语学习分类
            case R.id.btn_jidanci:
            {
                String uriString="http://www.51mokao.com/testlist2.html?id=7698&tc=3007698&keyid=&q=&listtype=&testdate=&testtype=&area=";
                Tiku1Activity.startTiku1Activity(this, "0", uriString);
                break;
            }
            case R.id.btn_yingyutingli:
            {
                String uriString="http://www.51mokao.com/testlist2.html?id=308&tc=300308&q=";
                Tiku1Activity.startTiku1Activity(this, "0", uriString);
                break;
            }
            case R.id.btn_yingyuyufa:
            {
                String uriString="http://www.51mokao.com/testlist2.html?id=7851&tc=3007851&q=";
                Tiku1Activity.startTiku1Activity(this, "0", uriString);
                break;
            }
            case R.id.btn_gonggongyingyu:
            {
                String uriString="http://www.51mokao.com/testlist2.html?id=322&tc=300322&q=";
                Tiku1Activity.startTiku1Activity(this, "0", uriString);
                break;
            }
            case R.id.btn_kouyibiyi:
            {
                String uriString="http://www.51mokao.com/testlist2.html?id=7675&tc=3007675&q=";
                Tiku1Activity.startTiku1Activity(this, "0", uriString);
                break;
            }
            case R.id.btn_jianqiaoyingyu:
            {
                String uriString="http://www.51mokao.com/testlist2.html?id=306&tc=300306&q=";
                Tiku1Activity.startTiku1Activity(this, "0", uriString);
                break;
            }

//	英语考试分类
            case R.id.btn_siliuji:
            {
                String uriString="http://www.51mokao.com/testlist2.html?id=310&tc=300310&keyid=&q=&listtype=&testdate=&testtype=&area=";
                Tiku1Activity.startTiku1Activity(this, "0", uriString);
                break;
            }
            case R.id.btn_zhuansizhuanba:
            {
                String uriString="http://www.51mokao.com/testlist2.html?id=310&tc=300310&keyid=&q=&listtype=&testdate=&testtype=&area=";
                Tiku1Activity.startTiku1Activity(this, "0", uriString);
                break;
            }
            case R.id.btn_yasi:
            {
                String uriString="http://www.51mokao.com/testlist2.html?id=318&tc=300318&q=";
                Tiku1Activity.startTiku1Activity(this, "0", uriString);
                break;
            }
            case R.id.btn_tuofu:
            {
                String uriString="http://www.51mokao.com/testlist2.html?id=315&tc=300315&keyid=&q=&listtype=&testdate=&testtype=&area=";
                Tiku1Activity.startTiku1Activity(this, "0", uriString);
                break;
            }
            case R.id.btn_gre:
            {
                String uriString="http://www.51mokao.com/testlist2.html?id=316&tc=300316&keyid=&q=&listtype=&testdate=&testtype=&area=";
                Tiku1Activity.startTiku1Activity(this, "0", uriString);
                break;
            }
            case R.id.btn_sat:
            {
                String uriString="http://www.51mokao.com/testlist2.html?id=313&tc=300313&keyid=&q=&listtype=&testdate=&testtype=&area=";
                Tiku1Activity.startTiku1Activity(this, "0", uriString);
                break;
            }

//	实用英语分类
            case R.id.btn_bec:
            {
                String uriString="http://www.51mokao.com/testlist2.html?id=320&tc=300320&keyid=&q=&listtype=&testdate=&testtype=&area=";
                Tiku1Activity.startTiku1Activity(this, "0", uriString);
                break;
            }
            case R.id.btn_zhicheng:
            {
                String uriString="http://www.51mokao.com/testlist2.html?id=325&tc=300325&q=";
                Tiku1Activity.startTiku1Activity(this, "0", uriString);
                break;
            }
            case R.id.btn_jinrong:
            {
                String uriString="http://www.51mokao.com/testlist2.html?id=7752&tc=3007752&keyid=&q=&listtype=&testdate=&testtype=&area=";
                Tiku1Activity.startTiku1Activity(this, "0", uriString);
                break;
            }
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
