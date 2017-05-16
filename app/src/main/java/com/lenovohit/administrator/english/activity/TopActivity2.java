package com.lenovohit.administrator.english.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.lenovohit.administrator.english.R;

import butterknife.Bind;

/**
 * Created by Administrator on 2017-05-11.
 * 听力测试详情页
 */

public class TopActivity2 extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.ivBack)
    LinearLayout back;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.iv1)
    ImageView iv1;
    @Bind(R.id.iv2)
    ImageView iv2;
    @Bind(R.id.iv3)
    ImageView iv3;
    @Bind(R.id.iv4)
    ImageView iv4;
    @Bind(R.id.ivSound)
    ImageView ivSound;
    private SpeechSynthesizer mTts;


    @Override
    public void initView() {
        setContentView(R.layout.activiyty_top2);
        tvTitle= (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("每日一听");
    }

    @Override
    public void initData() {
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);
    }

    @Override
    public void initEvent() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] stringArray = getResources().getStringArray(R.array.top2);
                playKD(stringArray[0]);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv1:
                iv1.setBackgroundResource(R.mipmap.no);
                break;
            case  R.id.iv2:
                iv2.setBackgroundResource(R.mipmap.yes);
                break;
            case R.id.iv3:
                iv3.setBackgroundResource(R.mipmap.no);
                break;
            case R.id.iv4:
                iv4.setBackgroundResource(R.mipmap.no);
                break;
        }
    }
    public void playKD(String s) {
        //1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传InitListener
        mTts = SpeechSynthesizer.createSynthesizer(this, null);
//2.合成参数设置，详见《科大讯飞MSC API手册(Android)》SpeechSynthesizer 类
        mTts.setParameter(SpeechConstant.VOICE_NAME, "Henry");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "20");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
//设置合成音频保存位置（可自定义保存位置），保存在“./sdcard/iflytek.pcm”
//保存在SD卡需要在AndroidManifest.xml添加写SD卡权限
//如果不需要保存合成音频，注释该行代码
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
//3.开始合成
        mTts.startSpeaking(s, mSynListener);
//合成监听器
    }
    private SynthesizerListener mSynListener = new SynthesizerListener(){
        //会话结束回调接口，没有错误时，error为null
        public void onCompleted(SpeechError error) {}
        //缓冲进度回调
        //percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在文本中结束位置，info为附加信息。
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {}
        //开始播放
        public void onSpeakBegin() {}
        //暂停播放
        public void onSpeakPaused() {}
        //播放进度回调
        //percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.
        public void onSpeakProgress(int percent, int beginPos, int endPos) {}
        //恢复播放回调接口
        public void onSpeakResumed() {}
        //会话事件回调接口
        public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {}
    };
    public static void startTopActivity2(Context context){
        context.startActivity(new Intent(context,TopActivity2.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSynListener.onSpeakPaused();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTts!=null)
            mTts.destroy();
    }
}
