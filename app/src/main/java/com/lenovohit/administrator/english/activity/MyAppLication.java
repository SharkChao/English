package com.lenovohit.administrator.english.activity;

import android.app.Application;
import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.lenovohit.administrator.english.domain.BmobUser;
import com.orhanobut.logger.LogLevel;

import cn.bmob.v3.Bmob;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.toolsfinal.Logger;

/**
 * Created by Administrator on 2017-04-28.
 */

public class MyAppLication extends Application{
    public static BmobUser user=new BmobUser();
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        //第一：默认初始化
        Bmob.initialize(this, "5ab8ac71c29ab52dbe88f6c4acfe158e");
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=59148bf7");
        //配置主题
//ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .build();
//配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

//配置imageloader
//        ImageLoader imageloader = new GlideImageLoader();
////设置核心配置信息
//        CoreConfig coreConfig = new CoreConfig.Builder(getApplicationContext(), imageloader, theme)
//                .setDebug(BuildConfig.DEBUG)
//                .setFunctionConfig(functionConfig)
//                .build();
//        GalleryFinal.init(coreConfig);
        initLogger();
    }
    private void initLogger(){
        LogLevel logLevel;
        logLevel = LogLevel.FULL;
        Logger.init("tag",true);                 // default PRETTYLOGGER or use just init()
    }

    public static BmobUser getUser() {
        return user;
    }

    public static void setUser(BmobUser user) {
        MyAppLication.user = user;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        MyAppLication.context = context;
    }
}
