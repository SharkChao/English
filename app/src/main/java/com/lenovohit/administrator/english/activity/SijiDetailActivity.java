package com.lenovohit.administrator.english.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.lenovohit.administrator.english.R;
import com.lenovohit.administrator.english.domain.ChinaMessage;

import butterknife.Bind;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017-05-12.
 * 单词详情页
 */

public class SijiDetailActivity extends  BaseActivity {

    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.ivBack)
    LinearLayout back;
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv11)
    TextView tv11;
    @Bind(R.id.tv2)
    TextView tv2;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.etText)
    TextView etText;
    @Bind(R.id.ivSound)
    ImageView ivSound;
    String string;
    @Bind(R.id.btnFind)
    Button btnFind;
    static String s;

    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what==1){
                Gson gson=new Gson();
                String dd = string;
                ChinaMessage chinaMessage = gson.fromJson(string, ChinaMessage.class);
                tvName.setText("英 ["+chinaMessage.getBasic().getUsphonetic()+"]");
                tv1.setText(chinaMessage.getBasic().getExplains().get(0));
                tv11.setText(chinaMessage.getBasic().getExplains().get(chinaMessage.getBasic().getExplains().size()-1));
                if (chinaMessage.getWeb().size()>=2)
                    tv2.setText(chinaMessage.getWeb().get(chinaMessage.getWeb().size()-1).getKey()+"  "+chinaMessage.getWeb().get(chinaMessage.getWeb().size()-1).getValue().get(0)+"\n"+chinaMessage.getWeb().get(chinaMessage.getWeb().size()-2).getKey()+"  "+chinaMessage.getWeb().get(chinaMessage.getWeb().size()-2).getValue().get(0));
                else if (chinaMessage.getWeb().size()>0&&chinaMessage.getWeb().size()<2){
                    tv2.setText(chinaMessage.getWeb().get(chinaMessage.getWeb().size()-1).getKey()+"  "+chinaMessage.getWeb().get(chinaMessage.getWeb().size()-1).getValue().get(0)+"\n");
                }
            }
            return true;
        }
    });
    private SpeechSynthesizer mTts;

    @Override
    public void initView() {
        setContentView(R.layout.activiyty_siji_detail);
        tvTitle= (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("单词详情");
    }

    @Override
    public void initData() {
    }

    @Override
    public void initEvent() {
        etText.setText(s);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client=new OkHttpClient();
                        Request request=new Request.Builder().url("http://fanyi.youdao.com/openapi.do?keyfrom=dadwada&key=1429076174&type=data&doctype=json&version=1.1&q="+s).build();
                        try {
                            Response response = client.newCall(request).execute();
                            if (response.isSuccessful()){
                                string = response.body().string();
                                System.out.println(string);
                                handler.sendEmptyMessage(1);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
        ivSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playKD(etText.getText().toString());
            }
        });
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
    public static void startSijiDetailActivity(Context context,String s){
        SijiDetailActivity.s=s;
        context.startActivity(new Intent(context,SijiDetailActivity.class));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTts!=null)
        mTts.destroy();
    }
}
