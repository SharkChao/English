package com.lenovohit.administrator.english.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017-05-13.
 * 题库实体类
 */

@Entity
public class Tiku {
    //tag判断 是  四级，六级，考研
    public String tag;
    public String a;
    public String b;
    public String c;
    public String d;
    //判断正确的选项
    public String yes;
   public String text;
    public String question;
    @Generated(hash = 1444543666)
    public Tiku(String tag, String a, String b, String c, String d, String yes,
            String text, String question) {
        this.tag = tag;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.yes = yes;
        this.text = text;
        this.question = question;
    }
    @Generated(hash = 614492220)
    public Tiku() {
    }
    public String getTag() {
        return this.tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getA() {
        return this.a;
    }
    public void setA(String a) {
        this.a = a;
    }
    public String getB() {
        return this.b;
    }
    public void setB(String b) {
        this.b = b;
    }
    public String getC() {
        return this.c;
    }
    public void setC(String c) {
        this.c = c;
    }
    public String getD() {
        return this.d;
    }
    public void setD(String d) {
        this.d = d;
    }
    public String getYes() {
        return this.yes;
    }
    public void setYes(String yes) {
        this.yes = yes;
    }
    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getQuestion() {
        return this.question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
}
