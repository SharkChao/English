package com.lenovohit.administrator.english.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.lenovohit.administrator.english.R;
import com.lenovohit.administrator.english.domain.Word;
import com.lenovohit.administrator.english.utils.XmlUtil;
import com.lenovohit.administrator.english.widget.NiceSpinner;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017-05-12.
 */

public class Ttem7Activity extends  BaseActivity {
    @Bind(R.id.ivBack)
    LinearLayout back;
    @Bind(R.id.spinner)
    NiceSpinner spinner;
    InputStream in;
@Bind(R.id.lvList)
    ListView lvList;
    private List<Word> users;
    private SpeechSynthesizer mTts;

    @Override
    public void initView() {
        setContentView(R.layout.activity_saven);
    }

    @Override
    public void initData() {
        List<String> list=new ArrayList<>();
        list.add("四级单词");
        list.add("六级单词");
        list.add("考研单词");
        spinner.attachDataSource(list);
        try {
            in =getResources().getAssets().open("siji.xml");
//如果说要获取到File对象的话，获取assert文件中的文件
//File file =new File("file:///android_asset/User.xml");
        }  catch (Exception e) {
            e.printStackTrace();
        }
        List <String > fields =new ArrayList<String>();
        List <String > elements =new ArrayList<String>();
        fields.add("word");
        fields.add("trans");
        fields.add("phonetic");
        fields.add("tags");
        elements.add("word");
        elements.add("trans");
        elements.add("phonetic");
        elements.add("tags");
        users = XmlUtil.parse(in, Word.class, fields, elements, "item");
    }

    @Override
    public void initEvent() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lvList.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return users.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                final Word word=users.get(position);
                View view=View.inflate(Ttem7Activity.this,R.layout.item_siji,null);
                ImageView ivSound= (ImageView) view.findViewById(R.id.ivSound);
                TextView tv1= (TextView) view.findViewById(R.id.tv1);
                TextView tv2= (TextView) view.findViewById(R.id.tv2);
                TextView tv3= (TextView) view.findViewById(R.id.tv3);
                TextView tv4= (TextView) view.findViewById(R.id.tv4);
                ivSound.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playKD(word.getWord());
                    }
                });
                tv1.setText(word.getWord());
                tv2.setText(word.getPhonetic());
                tv3.setText(word.getTrans());
                tv4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SijiDetailActivity.startSijiDetailActivity(Ttem7Activity.this,word.getWord());
                    }
                });
                return view;
            }
        });
    }

    public static void startTtem7Activity(Context context){
        context.startActivity(new Intent(context,Ttem7Activity.class));
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTts!=null)
            mTts.destroy();
    }
}
