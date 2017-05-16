package com.lenovohit.administrator.english.domain;

/**
 * Created by Administrator on 2017-04-28.
 */

public class BmobUser extends cn.bmob.v3.BmobUser{
    //用户头像
    public String  avatar;
    public String sex;
    public String age;
    public String nickname;

    public String getSex() {
        return sex;
    }

    public String getAge() {
        return age;
    }

    public String getNickname() {
        return nickname;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
