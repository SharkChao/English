package com.lenovohit.administrator.english.greendao;

import android.content.Context;

import com.lenovohit.administrator.english.utils.MigrationHelper;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Administrator on 2017/3/20.
 */

public class DBHelper extends DaoMaster.OpenHelper {
    public static final String DBNAME = "english.db";

    public DBHelper(Context context) {
        super(context, DBNAME, null);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        MigrationHelper dbhelper = new MigrationHelper();
    }
}