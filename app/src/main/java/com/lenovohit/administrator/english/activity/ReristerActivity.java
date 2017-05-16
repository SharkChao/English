package com.lenovohit.administrator.english.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lenovohit.administrator.english.R;
import com.lenovohit.administrator.english.domain.BmobUser;
import com.lenovohit.administrator.english.utils.StringUtil;

import butterknife.Bind;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


/**
 * Created by Administrator on 2017-04-28.
 */

public class ReristerActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.resetpwd_edit_name)
    EditText resetpwdEditName;
    @Bind(R.id.resetpwd_edit_pwd_old)
    EditText resetpwdEditPwdOld;
    @Bind(R.id.register_btn_cancle)
    Button registerBtnCancle;
    @Bind(R.id.resetpwd_edit_pwd_new)
    EditText resetpwdEditPwdNew;
    @Bind(R.id.register_btn_sure)
    Button registerBtnSure;

    @Override
    public void initView() {
        setContentView(R.layout.activity_rerister);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        registerBtnSure.setOnClickListener(this);
        registerBtnCancle.setOnClickListener(this);
    }

    /**
     * 所有的点击事件在这里处理
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_btn_sure:
                btnRegister();
                break;
            case R.id.register_btn_cancle:
                finish();
                break;
        }
    }

    /**
     * 点击注册按钮后的事件
     */
    public void btnRegister(){
        final String account = resetpwdEditName.getText().toString();
        final String password1 = resetpwdEditPwdOld.getText().toString();
        String password2 = resetpwdEditPwdNew.getText().toString();
        if (StringUtil.isStrEmpty(account)){
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
        }else if ((StringUtil.isStrEmpty(password1))){
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
        }else if (StringUtil.isStrEmpty(password2)){
            Toast.makeText(this, "请再次输入密码", Toast.LENGTH_SHORT).show();
        }else if (!StringUtil.isStrEmpty(account)&&!StringUtil.isStrEmpty(password1)&&!StringUtil.isStrEmpty(password2)){
            if (!password1.equals(password2)){
                Toast.makeText(this, "两次输入密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
            }else {
                //与bmob后端云交互，先判断是否有当前账户，没有的话才可注册
                final BmobUser user=new BmobUser();
                user.setUsername(account);
                user.setPassword(password1);
                user.signUp(new SaveListener<BmobUser>() {

                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if (e==null){
                            Toast.makeText(ReristerActivity.this, "注册成功!", Toast.LENGTH_SHORT).show();
                            LoginActivity.startLoginActivity(ReristerActivity.this,user);
                            finish();
                        }else {
                            Toast.makeText(ReristerActivity.this, "注册失败，当前用户已存在", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
    public static void startRegisterActivity(Context context){
        context.startActivity(new Intent(context,ReristerActivity.class));
    }
}
