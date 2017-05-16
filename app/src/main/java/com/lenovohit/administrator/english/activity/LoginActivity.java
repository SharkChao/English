package com.lenovohit.administrator.english.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lenovohit.administrator.english.R;
import com.lenovohit.administrator.english.domain.BmobUser;
import com.lenovohit.administrator.english.utils.SpUtil;
import com.lenovohit.administrator.english.utils.StringUtil;
import com.lenovohit.administrator.english.widget.CircleImageView;
import com.lenovohit.administrator.english.widget.LoadingDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;


public class LoginActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.login_btn_register)
    Button loginBtnRegister;
    @Bind(R.id.login_btn_login)
    Button loginBtnLogin;
    @Bind(R.id.logo)
    CircleImageView logo;
    @Bind(R.id.login_edit_pwd)
    EditText loginEditPwd;
    @Bind(R.id.login_edit_account)
    EditText loginEditAccount;
    @Bind(R.id.Login_Remember)
    CheckBox LoginRemember;
    @Bind(R.id.login_view)
    RelativeLayout loginView;
    @Bind(R.id.login_success_show)
    TextView loginSuccessShow;
    @Bind(R.id.login_success_view)
    RelativeLayout loginSuccessView;
    private static BmobUser user=new BmobUser();
    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initData() {
        loginEditAccount.setText(StringUtil.isStrEmpty(user.getUsername())?"":user.getUsername());
        boolean isChecked = (boolean) SpUtil.getParam(LoginActivity.this, "isChecked", false);
        String account = (String) SpUtil.getParam(LoginActivity.this, "account", "");
        String  password= (String) SpUtil.getParam(LoginActivity.this,"password","");
        if (isChecked){
            LoginRemember.setChecked(true);
            loginEditAccount.setText(StringUtil.isStrEmpty(account)?"":account);
            loginEditPwd.setText(StringUtil.isStrEmpty(password)?"":password);
        }else {
            loginEditAccount.setText("");
            loginEditPwd.setText("");
        }
        if (!StringUtil.isStrEmpty(loginEditAccount.getText().toString())){
            getPic(loginEditAccount.getText().toString());
        }
    }

    @Override
    public void initEvent() {
        loginBtnLogin.setOnClickListener(this);
        loginBtnRegister.setOnClickListener(this);
        LoginRemember.setOnClickListener(this);
        LoginRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    SpUtil.setParam(LoginActivity.this,"isChecked",true);
                }else {
                    SpUtil.setParam(LoginActivity.this,"isChecked",false);
                }
            }
        });
    }

    /**
     * 所有点击事件在这里处理
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn_login:
                btnLogin();
                break;
            case R.id.login_btn_register:
                btnRegister();
                break;
        }
    }

    /**
     * 点击登录按钮后的点击事件
     */
    public void btnLogin(){
        final String account = loginEditAccount.getText().toString();
        final String password = loginEditPwd.getText().toString();
        if (StringUtil.isStrEmpty(account)){
            Toast.makeText(this, "请先输入账号", Toast.LENGTH_SHORT).show();
        }
        else if (StringUtil.isStrEmpty(password)){
            Toast.makeText(this, "请先输入密码", Toast.LENGTH_SHORT).show();
        }else if (!StringUtil.isStrEmpty(account)&&!StringUtil.isStrEmpty(password)){
            //从bmob后端云进行验证
            final LoadingDialog dialog=new LoadingDialog(this);
            dialog.setMessage("正在登录中...");
            dialog.show();
            final BmobUser user=new BmobUser();
            user.setUsername(account);
            user.setPassword(password);
            user.login(new SaveListener<BmobUser>() {
                @Override
                public void done(BmobUser bmobUser, BmobException e) {

                    if (e==null){
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        SpUtil.setParam(LoginActivity.this,"account",account);
                        SpUtil.setParam(LoginActivity.this,"password",password);
                        user.setAvatar("");
                        user.setEmail("");
                        user.setAge("");
                        MyAppLication.setUser(user);
//                        HomeActivity.startHomeActivity(LoginActivity.this);
                        MainActivity.startManinActivity(LoginActivity.this);
                        LoginActivity.this.finish();
                    }else {
                        Toast.makeText(LoginActivity.this, "登录失败,请先注册", Toast.LENGTH_SHORT).show();
                    }
                    if (dialog.isShowing()){
                        dialog.dismiss();
                    }
                }
            });
        }
    }
    /**
     * 点击注册按钮后的点击事件
     */
    public void btnRegister(){
        ReristerActivity.startRegisterActivity(this);
        finish();
    }
    public static void startLoginActivity(Context context,BmobUser user){
        LoginActivity.user=user;
        context.startActivity(new Intent(context,LoginActivity.class));
    }
    /**
     * 从bmob获取用户头像信息
     */
    public void getPic(String username){
        BmobQuery query=new BmobQuery("_User");
        query.addWhereEqualTo("username", username);
        query.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {
                if (e==null&&jsonArray.length()>0){
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                       String url = (String) jsonObject.opt("avatar");
                        if (!StringUtil.isStrEmpty(url)){
                            Glide.with(LoginActivity.this).load(url).into(logo);
                        }else {
//                            Toast.makeText(LoginActivity.this, "暂未设置头像", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "获取用户信息失败"+e, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
