package com.lenovohit.administrator.english.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.lenovohit.administrator.english.R;
import com.lenovohit.administrator.english.greendao.DaoManager;
import com.lenovohit.administrator.english.greendao.DaoSession;
import com.lenovohit.administrator.english.greendao.Tiku;
import com.lenovohit.administrator.english.greendao.TikuDao;
import com.lenovohit.administrator.english.utils.EventUtil;
import com.lenovohit.administrator.english.utils.JsonParser;
import com.lenovohit.administrator.english.widget.VolumeView;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.lenovohit.administrator.english.utils.FileUtil.getWavFilePath;


/**
 * Created by Administrator on 2017-05-13.
 * 口语测试详情页
 */

public class KouyuDetailActivity extends BaseActivity {
    private static String text;
    private static String content;
    @Bind(R.id.ivBack)
    LinearLayout ivBack;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.btn1)
    Button btn1;
    @Bind(R.id.btn2)
    Button btn2;
    @Bind(R.id.tv1)
    TextView tv1;
    private AlertDialog alertDialog;
    private VolumeView volumeView;
    private SpeechRecognizer mIat;
    private SpeechSynthesizer mTts;

    @Override
    public void initView() {
        setContentView(R.layout.activity_kouyu_detail);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText(text);
    }

    @Override
    public void initData() {
        getDataFromSql();

    }

    @Override
    public void initEvent() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playKD(content);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static void startKouyuDetailActivity(Context context, String text) {
        context.startActivity(new Intent(context, KouyuDetailActivity.class));
        KouyuDetailActivity.text = text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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

    private SynthesizerListener mSynListener = new SynthesizerListener() {
        //会话结束回调接口，没有错误时，error为null
        public void onCompleted(SpeechError error) {
        }

        //缓冲进度回调
        //percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在文本中结束位置，info为附加信息。
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
        }

        //开始播放
        public void onSpeakBegin() {
        }

        //暂停播放
        public void onSpeakPaused() {
        }

        //播放进度回调
        //percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
        }

        //恢复播放回调接口
        public void onSpeakResumed() {
        }

        //会话事件回调接口
        public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
        }
    };

    public void start() {
        mIat = SpeechRecognizer.createRecognizer(this, null);
        mIat.setParameter(SpeechConstant.LANGUAGE, "en_us");
        mIat.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mIat.setParameter(SpeechConstant.VOICE_NAME, "Henry");//设置发音人
        mIat.setParameter(SpeechConstant.SPEED, "20");//设置语速
        mIat.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围0~100
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, getWavFilePath());
        mIat.startListening(recognizerListener);
    }

    private InitListener mInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
        }
    };
    public void getDataFromSql() {
        DaoManager instance = DaoManager.getInstance(this);
        DaoSession session = instance.getSession();
        TikuDao tikuDao = session.getTikuDao();
        int a = (int) (Math.random()*2);
        Tiku tiku = tikuDao.queryBuilder().where(TikuDao.Properties.Tag.eq("0")).build().list().get(a);
        tv1.setText("   "+tiku.getText());
        content=tiku.getText();
    }
    private RecognizerListener recognizerListener = new RecognizerListener() {
        @Override
        public void onVolumeChanged(int i, byte[] bytes) {

        }

        @Override
        public void onBeginOfSpeech() {
            View view=View.inflate(KouyuDetailActivity.this,R.layout.alert_volume,null);
            volumeView = (VolumeView) view.findViewById(R.id.volume);
            volumeView.start();
            AlertDialog.Builder builder = new AlertDialog.Builder(KouyuDetailActivity.this).setCancelable(false).setView(view);
            alertDialog = builder.create();
            alertDialog.show();
            System.out.println("开始识别");
        }

        @Override
        public void onEndOfSpeech() {
            volumeView.stop();
            alertDialog.dismiss();
            System.out.println("识别结束");
            DuyinDetailActivity.startDuyinDetailActivity(KouyuDetailActivity.this,tv1.getText().toString());
        }

        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            String str= JsonParser.parseIatResult(recognizerResult.getResultString());
            System.out.println("识别结果"+str);
            if (!str.equals("."))
            EventUtil.postSticky(str);
        }

        @Override
        public void onError(SpeechError speechError) {
            System.out.println("识别出错");
        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        if (mTts!=null)
            mTts.destroy();
        if (mIat!=null)
            mIat.destroy();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTts!=null)
        mTts.destroy();
        if (mIat!=null)
        mIat.destroy();
    }
}
