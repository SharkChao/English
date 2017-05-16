package com.lenovohit.administrator.english.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bannerlayout.Interface.OnBannerClickListener;
import com.bannerlayout.model.BannerModel;
import com.bannerlayout.widget.BannerLayout;
import com.lenovohit.administrator.english.R;
import com.lenovohit.administrator.english.domain.BmobUser;
import com.lenovohit.administrator.english.widget.Alert;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;



public class MainActivity extends BaseActivity {

    @Bind(R.id.banner)
    BannerLayout bannerLayout;
    @Bind(R.id.gridview)
    GridView gridView;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {
        setBannerLayout();
    }

    @Override
    public void initEvent() {
        final int []res=new int[]{R.mipmap.grid1,R.mipmap.grid7,R.mipmap.grid2,R.mipmap.grid3,R.mipmap.grid4,R.mipmap.grid5,R.mipmap.grid6};
        final String []s=new String[]{"词句翻译","单词库","在线视频","在线题库","听力测试","口语测试","个人资料"};
        gridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 7;
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
                View view =View.inflate(MainActivity.this,R.layout.grid_item,null);
                ImageView ivPic= (ImageView) view.findViewById(R.id.ivImg);
                TextView tvTitle= (TextView) view.findViewById(R.id.tvTitle);
                ivPic.setImageResource(res[position]);
                tvTitle.setText(s[position]);
                return view;
            }
        });
        final BmobUser user = MyAppLication.getUser();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Ttem1Activity.startTtem1Activity(MainActivity.this);
                        break;
                    case 1:
                        Ttem7Activity.startTtem7Activity(MainActivity.this);
                        break;
                    case 2:
                        VideoActivity.startVideoActivity(MainActivity.this);
                        break;
                    case 3:
//                        TikuActivity.startTikuActivity(MainActivity.this,"0","2016年12月大学英语四级真题听力");
                        Ttem3Activity.startTtem3Activity(MainActivity.this);
                        break;
                    case 4:
                        Ttem4Activity.startTtem4Activity(MainActivity.this);
                        break;
                    case 5:
                        Ttem5Activity.startTtem5Activity(MainActivity.this);
                        break;
                    case 6:
                        StudentInfoActivity.startStudentInfoActivity(MainActivity.this);
                        break;
                }
            }
        });
    }

    public static void startManinActivity(Context context){
        context.startActivity(new Intent(context,MainActivity.class));
    }
    public void setBannerLayout() {
        //截取前三项，放入bannderLayout中
        List<BannerModel> models = new ArrayList<>();
            models.add(new BannerModel("http://bmob-cdn-11017.b0.upaiyun.com/2017/05/11/b4f3d2193db44a1c8f7b797e60a7e200.jpg", "每日一句"));
            models.add(new BannerModel("http://bmob-cdn-11017.b0.upaiyun.com/2017/05/11/0190063444fb415f8eeecbea1273aebb.jpg", "每日一听"));
        bannerLayout
                .initListResources(models)
                .initTips(true,true,true)
                .start(true);
        bannerLayout.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void onBannerClick(View view, int position, Object model) {
                if (position==0){
                    TopActivity1.startTopActivity1(MainActivity.this);
                }else {
                    TopActivity2.startTopActivity2(MainActivity.this);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        new Alert(this).builder().setCancelable(false).setTitle("退出").setMsg("您确定要退出吗？").setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }
}
