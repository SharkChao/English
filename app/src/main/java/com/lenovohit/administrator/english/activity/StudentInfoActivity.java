package com.lenovohit.administrator.english.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lenovohit.administrator.english.R;
import com.lenovohit.administrator.english.utils.SpUtil;
import com.lenovohit.administrator.english.utils.StringUtil;
import com.lenovohit.administrator.english.widget.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;


/**
 * Created by Administrator on 2017/4/12.
 * 学生信息主界面
 */

public class StudentInfoActivity extends BaseActivity {
    @Bind(R.id.ivBack)
    ImageView imageView;
    @Bind(R.id.ivPic)
    CircleImageView head;
    @Bind(R.id.tvNickname)
    TextView tvNickName;
    @Bind(R.id.tvQian)
    TextView tvQian;
    @Bind(R.id.tvAccount)
    TextView tvAccount;
    @Bind(R.id.tvSex)
    TextView tvSex;
    @Bind(R.id.tvZhuan)
    TextView tvZhuan;
    @Bind(R.id.rigBtn)
    ImageButton rigBtn;
    @Bind(R.id.addFriend)
    Button addFridend;
    @Bind(R.id.senMsg)
    Button sendMsg;
    //state用来标识是自己的个人信息，还是查询的同学的个人信息  0标识他本身，1表示查询到的
    static String picUrl, nickname, qianming, account, sex, zhuanye, banji, state, age, email;
    @Bind(R.id.tvHomeTitle)
    TextView tvHomeTitle;
    @Bind(R.id.rl_top)
    RelativeLayout rlTop;
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.tvEmail)
    TextView tvEmail;
    @Bind(R.id.pullScrollview)
    LinearLayout pullScrollview;
    @Bind(R.id.tvCollent)
    TextView tvCollent;

    @Override
    public void initView() {
        setContentView(R.layout.activity_studentinfo);
    }

    @Override
    public void initData() {

        nickname = (String) SpUtil.getParam(this, "nickname", "这家伙没有昵称");
        qianming = (String) SpUtil.getParam(this, "qianming", "这家伙很懒，还没有设置个人签名");

        account = (String) SpUtil.getParam(this, "account", "查无此人");
        sex = (String) SpUtil.getParam(this, "sex", "这家伙是外星人");
        zhuanye = (String) SpUtil.getParam(this, "age", "暂无信息");
        email = (String) SpUtil.getParam(this, "email", "暂无邮箱");

        tvNickName.setText(StringUtil.isStrEmpty(nickname) ? "这家伙没有昵称" : nickname);
        tvQian.setText(StringUtil.isStrEmpty(qianming) ? "这家伙很懒，还没有设置个人签名" : qianming);
        tvAccount.setText(StringUtil.isStrEmpty(account) ? "查无此人" : account);
        tvSex.setText(StringUtil.isStrEmpty(sex) ? "这家伙是外星人" : sex);
        tvZhuan.setText(StringUtil.isStrEmpty(zhuanye) ? "暂无信息" : zhuanye);
        tvEmail.setText(StringUtil.isStrEmpty(email) ? "暂无邮箱" : email);

        if (!StringUtil.isStrEmpty(sex) && sex.equals("男")) {
            imageView.setImageResource(R.mipmap.back4);
        }
        if (state.equals("0")) {
            //本人信息
            rigBtn.setVisibility(View.VISIBLE);
            sendMsg.setVisibility(View.GONE);
            addFridend.setVisibility(View.GONE);
        } else if (state.equals("1")) {
            //查询到的信息
            rigBtn.setVisibility(View.GONE);
            sendMsg.setVisibility(View.VISIBLE);
            addFridend.setVisibility(View.VISIBLE);
        }
        if (account.equals(BmobUser.getCurrentUser().getUsername())) {
            //本人信息
            rigBtn.setVisibility(View.VISIBLE);
            sendMsg.setVisibility(View.GONE);
            addFridend.setVisibility(View.GONE);
        }
    }

    @Override
    public void initEvent() {
        rigBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivityForResult(new Intent(StudentInfoActivity.this, StudentInfoSetActivity.class),0);
                startActivityForResult(new Intent(StudentInfoActivity.this, StudentInfoSetActivity.class), 0);
            }
        });
        tvCollent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollentActivity.startCollentActivity(StudentInfoActivity.this);
            }
        });
    }

    public static void startStudentInfoActivity(Context context) {
        context.startActivity(new Intent(context, StudentInfoActivity.class));
        state = "0";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            email = data.getStringExtra("email");
            qianming = data.getStringExtra("qianming");
            age = data.getStringExtra("age");
            sex = data.getStringExtra("sex");
            tvEmail.setText(StringUtil.isStrEmpty(email) ? "暂未设置邮箱" : email);
            tvSex.setText(sex);
            tvZhuan.setText(age);
            tvQian.setText(qianming);
            tvNickName.setText(BmobUser.getCurrentUser().getUsername());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
