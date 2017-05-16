package com.lenovohit.administrator.english.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovohit.administrator.english.R;
import com.lenovohit.administrator.english.domain.BmobUser;
import com.lenovohit.administrator.english.utils.SpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;


/**
 * Created by Administrator on 2017/4/12.
 * 学生信息设置页面
 */

public class StudentInfoSetActivity extends BaseActivity {

    @Bind(R.id.tvHomeTitle)
    TextView tvHomeTitle;
    @Bind(R.id.rl_top)
    RelativeLayout rlTop;
    @Bind(R.id.tvAge)
    EditText tvAge;
    @Bind(R.id.btn0)
    RadioButton btn0;
    @Bind(R.id.btn1)
    RadioButton btn1;
    @Bind(R.id.rgSex)
    RadioGroup rgSex;
    @Bind(R.id.tvHappy)
    EditText tvHappy;
    @Bind(R.id.tvCommit)
    Button tvCommit;
    @Bind(R.id.tvEmail)
    EditText tvEmail;
    @Bind(R.id.tvUpdate)
    TextView tvUpdate;
    private BmobUser user;
    static String picUrl, nickname, qianming, account, sex, zhuanye, banji, state, age, email;
    @Override
    public void initView() {
        setContentView(R.layout.activity_studentinfoset);
    }

    @Override
    public void initData() {
        nickname = (String) SpUtil.getParam(this,"nickname","这家伙没有昵称");
        qianming = (String) SpUtil.getParam(this,"qianming","这家伙很懒，还没有设置个人签名");

        account = (String) SpUtil.getParam(this,"account","查无此人");
        sex = (String) SpUtil.getParam(this,"sex","这家伙是外星人");
        zhuanye = (String) SpUtil.getParam(this,"age","暂无信息");
        email = (String) SpUtil.getParam(this,"email","这家伙没有昵称");

        tvAge.setText(zhuanye);
        tvHappy.setText(qianming);
        tvEmail.setText(email);
        if (sex.equals("男")){
            rgSex.check(R.id.btn0);
        }else {
            rgSex.check(R.id.btn1);
        }
    }

    @Override
    public void initEvent() {
        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = tvEmail.getText().toString();
                final String age = tvAge.getText().toString();
                final String happy = tvHappy.getText().toString();
                rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        if (checkedId == R.id.btn0) {
                            sex = "男";
                        } else {
                            sex = "女";
                        }
                    }
                });
                int checkedRadioButtonId = rgSex.getCheckedRadioButtonId();
                if (checkedRadioButtonId == R.id.btn0) {
                    sex = "男";
                } else {
                    sex = "女";
                }
                SpUtil.setParam(StudentInfoSetActivity.this, "nickname", cn.bmob.v3.BmobUser.getCurrentUser().getUsername());
                SpUtil.setParam(StudentInfoSetActivity.this, "qianming", happy);
                SpUtil.setParam(StudentInfoSetActivity.this, "account", cn.bmob.v3.BmobUser.getCurrentUser().getUsername());
                SpUtil.setParam(StudentInfoSetActivity.this, "sex", sex);
                SpUtil.setParam(StudentInfoSetActivity.this, "age", age);
                SpUtil.setParam(StudentInfoSetActivity.this, "email", email);

                BmobQuery query = new BmobQuery("_User");
                query.addWhereEqualTo("username", cn.bmob.v3.BmobUser.getCurrentUser().getUsername());
                query.findObjectsByTable(new QueryListener<JSONArray>() {
                    @Override
                    public void done(JSONArray jsonArray, BmobException e) {
                        if (e == null) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String objectId = jsonObject.optString("objectId");
                                BmobUser user = new BmobUser();
                                user.setAge(age);
                                user.setSex(sex);
                                user.setNickname(happy);
                                user.setEmail(email);
                                BmobUser user1 = MyAppLication.getUser();
                                user1.setAge(age);
                                user1.setSex(sex);
                                user1.setNickname(happy);
                                user1.setEmail(email);
                                MyAppLication.setUser(user1);
                                user.update(objectId, new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if (e == null) {
                                            Toast.makeText(StudentInfoSetActivity.this, "数据更新成功", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
                Intent intent = new Intent();
                intent.putExtra("email", email);
                intent.putExtra("age", age);
                intent.putExtra("sex", sex);
                intent.putExtra("qianming", happy);
                setResult(1, intent);
                finish();
            }
        });
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view=View.inflate(StudentInfoSetActivity.this,R.layout.alert_password,null);
                new AlertDialog.Builder(StudentInfoSetActivity.this).setCancelable(false).setTitle("更改密码").setView(view).setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText= (EditText) view.findViewById(R.id.etText);
                        final String password = editText.getText().toString();
                        BmobQuery query = new BmobQuery("_User");
                        query.addWhereEqualTo("username", cn.bmob.v3.BmobUser.getCurrentUser().getUsername());
                        query.findObjectsByTable(new QueryListener<JSONArray>() {
                            @Override
                            public void done(JSONArray jsonArray, BmobException e) {
                                if (e == null) {
                                    try {
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        String objectId = jsonObject.optString("objectId");
                                        BmobUser user = new BmobUser();
                                        user.setPassword(password);
                                        user.update(objectId, new UpdateListener() {
                                            @Override
                                            public void done(BmobException e) {
                                                if (e == null) {
                                                    Toast.makeText(StudentInfoSetActivity.this, "修改密码成功！", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });
    }


    public static void startStudentInfoSetActivity(Context context) {
        context.startActivity(new Intent(context, StudentInfoSetActivity.class));
    }
}
