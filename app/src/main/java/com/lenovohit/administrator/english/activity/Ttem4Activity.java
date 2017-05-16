package com.lenovohit.administrator.english.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lenovohit.administrator.english.R;
import com.lenovohit.administrator.english.widget.NiceSpinner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * Created by Administrator on 2017-05-12.
 * 听力测试模块
 */

public class Ttem4Activity extends  BaseActivity {
    @Bind(R.id.ivBack)
    LinearLayout back;
    @Bind(R.id.spinner)
    NiceSpinner spinner;
    @Bind(R.id.lvList)
    ListView lvList;
    private String[] stringArray;
    private int tag=0;
    @Override
    public void initView() {
        setContentView(R.layout.activity_four);
    }

    @Override
    public void initData() {
        List<String>list=new ArrayList<>();
        list.add("四级听力");
        list.add("六级听力");
        list.add("考研听力");
        spinner.attachDataSource(list);
        stringArray = getResources().getStringArray(R.array.siji);
        copyDBToDatabases();
    }

    @Override
    public void initEvent() {
        final MyAdapter adapter=new MyAdapter();
        lvList.setAdapter(adapter);
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TingliDetailActivity.startTingLiDetailActivity(Ttem4Activity.this,0+"",stringArray[position]);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    stringArray=getResources().getStringArray(R.array.siji);
                    adapter.notifyDataSetChanged();
                    tag=0;
                }else if (position==1){
                    stringArray=getResources().getStringArray(R.array.liuji);
                    adapter.notifyDataSetChanged();
                    tag=1;
                }else if (position==2){
                    stringArray=getResources().getStringArray(R.array.kaoyan);
                    adapter.notifyDataSetChanged();
                    tag=2;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    class  MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return stringArray.length;
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
            View view=View.inflate(Ttem4Activity.this,R.layout.item_tingli,null);
            TextView tv1 = (TextView) view.findViewById(R.id.tv1);
            TextView tv2= (TextView) view.findViewById(R.id.ivSound);
            tv1.setText(stringArray[position]);
            if (tag==0){
                tv2.setText("CET4");
            } else if (tag==1){
                tv2.setText("CET6");
            } else if (tag==2){
                tv2.setText("考研");
            }
            return view;
        }
    }
    public static void startTtem4Activity(Context context){
        context.startActivity(new Intent(context,Ttem4Activity.class));
    }
    /**
     * assets目录下的db转移到databases
     */
    public void copyDBToDatabases() {
        try {
             final String DB_PATH = "/data/data/com.lenovohit.administrator.english/databases/";
             final String DB_NAME = "english.db";
            String outFileName = DB_PATH+DB_NAME;
            File file = new File(DB_PATH);
            if (!file.mkdirs()) {
                file.mkdirs();
            }
            File dataFile = new File(outFileName);
            if (dataFile.exists()) {
                dataFile.delete();
            }
            InputStream myInput;
            myInput = getApplicationContext().getAssets().open(DB_NAME);
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
