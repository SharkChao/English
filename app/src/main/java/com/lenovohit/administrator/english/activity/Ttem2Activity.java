package com.lenovohit.administrator.english.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lenovohit.administrator.english.R;

import butterknife.Bind;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Administrator on 2017-05-12.
 */

public class Ttem2Activity extends  BaseActivity {

    @Bind(R.id.ivBack)
    LinearLayout ivBack;
    @Bind(R.id.ivList)
    ListView lvList;
    private JCVideoPlayerStandard player;

    @Override
    public void initView() {
        setContentView(R.layout.activity_two);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final String urls[]=new String[]{"http://mvideo.spriteapp.cn/video/2016/0901/d62d49a2-703f-11e6-b778-90b11c479401_wpc.mp4","http://mvideo.spriteapp.cn/video/2016/0901/d62d49a2-703f-11e6-b778-90b11c479401_wpc.mp4","http://mvideo.spriteapp.cn/video/2016/0901/d62d49a2-703f-11e6-b778-90b11c479401_wpc.mp4","http://mvideo.spriteapp.cn/video/2016/0901/d62d49a2-703f-11e6-b778-90b11c479401_wpc.mp4","http://mvideo.spriteapp.cn/video/2016/0901/d62d49a2-703f-11e6-b778-90b11c479401_wpc.mp4","http://mvideo.spriteapp.cn/video/2016/0901/d62d49a2-703f-11e6-b778-90b11c479401_wpc.mp4"};
        lvList.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return urls.length;
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
                View view=View.inflate(Ttem2Activity.this,R.layout.item_joke_video_layout,null);
                player = (JCVideoPlayerStandard) view.findViewById(R.id.video);
                player.setUp(urls[position], JCVideoPlayer.SCREEN_LAYOUT_LIST, "英语视频");
                return view;
            }
        });
    }
    public static void startTtem2Activity(Context context){
        context.startActivity(new Intent(context,Ttem2Activity.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player.isShown()){
            player.dismissVolumDialog();
            player.dismissProgressDialog();
            player.release();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player!=null)
        player.release();
    }
}
