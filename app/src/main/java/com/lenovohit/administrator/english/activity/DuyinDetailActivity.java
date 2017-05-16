package com.lenovohit.administrator.english.activity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovohit.administrator.english.R;
import com.lenovohit.administrator.english.utils.EventUtil;
import com.lenovohit.administrator.english.utils.FileUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.math.BigDecimal;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bob.similarity.SimilarityBase;
import cn.bob.similarity.SimilarityRate;

/**
 * Created by Administrator on 2017-05-14.
 */

public class DuyinDetailActivity extends BaseActivity {
    private static String text;
    @Bind(R.id.ivBack)
    LinearLayout ivBack;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;
    @Bind(R.id.btn1)
    Button btn1;
    MediaPlayer mPlayer;
    private static String text1;
    @Override
    public void initView() {
        setContentView(R.layout.activity_duyin);
    }

    @Override
    public void initData() {
        EventUtil.register(this);
    }

    @Override
    public void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMp3();
            }
        });
    }

    public static void startDuyinDetailActivity(Context context,String text) {
        context.startActivity(new Intent(context, DuyinDetailActivity.class));
        DuyinDetailActivity.text=text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    public void playMp3(){
        if (mPlayer==null) {
            mPlayer =new MediaPlayer();
            try {
                mPlayer.setDataSource(FileUtil.getWavFilePath());
                mPlayer.prepare();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        }else {
            mPlayer.start();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getData(String s){
        tv1.setText(s);
        SimilarityBase similarityBase = new SimilarityRate();
        double d = similarityBase.sim(s,text);
        tv2.setText("相似度为： "+ new BigDecimal(d*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"%");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayer!=null)
        mPlayer.release();
    }
}
