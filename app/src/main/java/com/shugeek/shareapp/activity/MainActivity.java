package com.shugeek.shareapp.activity;

import android.app.TabActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.shugeek.shareapp.R;
import com.shugeek.shareapp.adapter.ContactAdapter;
import com.shugeek.shareapp.database.MyDatabaseHelper;

public class MainActivity extends TabActivity {
    private static boolean firstAddDongtai = true;
    private Context mContext;
    private static long EXIT_TIME = 0;
    private TabHost mTabHost;
    private MyDatabaseHelper databaseHelper;
    private int[] headImage = new int[]{
            R.mipmap.share_person_1,
            R.mipmap.share_person_2,
            R.mipmap.share_person_3,
            R.mipmap.share_person_4,
            R.mipmap.share_person_5,
            R.mipmap.share_person_6,
            R.mipmap.share_person_7,
            R.mipmap.share_person_8,
            R.mipmap.share_person_9,
            R.mipmap.share_person_10,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_main);
        mContext = getApplicationContext();
        mTabHost = getTabHost();
        if (mTabHost != null) {
            initView();//初始化界面
            setShareGroupArrowClick();//设置分享圈左上角的箭头，点击回到主界面
            setCameraClick();//设置点击照相机图片发布事件到朋友圈
            setShuaxin();//点击刷新按钮动态刷新朋友圈动态
            addDongtaiDefault();//添加默认朋友圈动态
        }
    }

    /**
     * 初始化界面
     */
    private void initView() {
        TabHost.TabSpec contactTab1 = mTabHost.newTabSpec("tab1")
                .setIndicator("联 系 人")
                .setContent(R.id.share_tab1);
        mTabHost.addTab(contactTab1);

        TabHost.TabSpec shareGroupTab2 = mTabHost.newTabSpec("tab2")
                .setIndicator("分 享 圈")
                .setContent(R.id.share_tab2);
        mTabHost.addTab(shareGroupTab2);

        ListView contactListView = (ListView) findViewById(R.id.share_contact_list);
        ContactAdapter contactAdapter = new ContactAdapter(mContext);
        contactListView.setAdapter(contactAdapter);
        //创建数据库
        databaseHelper = new MyDatabaseHelper(this, "Share.db", null, 1, mContext);
        databaseHelper.getWritableDatabase();

    }

    /**
     * 设置分享圈左上角的箭头，点击回到主界面
     */
    private void setShareGroupArrowClick() {
        LinearLayout imageView = (LinearLayout) findViewById(R.id.share_group_arrow_left);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTabHost.setCurrentTab(0);
            }
        });
    }

    /**
     * 设置点击刷新图片
     */
    private void setShuaxin() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.share_shuaxin);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "点击了刷新按钮", Toast.LENGTH_LONG).show();
                initShareDongtai();
            }
        });
    }

    /**
     * 刷新朋友圈动态
     */
    private void initShareDongtai() {
        //动态的添加朋友圈动态条目
        LinearLayout share_dongtai = (LinearLayout) findViewById(R.id.share_dongtai);
        share_dongtai.removeAllViews();

        SQLiteDatabase readData = databaseHelper.getWritableDatabase();
        //查询动态表中的数据
        Cursor cursor = readData.query("Share", null, null, null, null, null, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    String dongtai = cursor.getString(cursor.getColumnIndex("shareText"));

                    FrameLayout share_dongtai_item = (FrameLayout) LayoutInflater.from(mContext).inflate(R.layout.share_dongtai_item, null);
                    TextView dongtai_fabu = (TextView) share_dongtai_item.findViewById(R.id.dongtai_fabu);
                    dongtai_fabu.setText(dongtai);

                    share_dongtai.addView(share_dongtai_item, 0);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

    }

    /**
     * 设置点击照相机图片发布事件到朋友圈
     */
    private void setCameraClick() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.share_group_publish);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "韩申权点击发布程序按钮", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, SendInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            //清除动画
            getWindow().getDecorView().clearAnimation();
            if ((System.currentTimeMillis() - EXIT_TIME) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序~", Toast.LENGTH_SHORT).show();
                EXIT_TIME = System.currentTimeMillis();
            } else {
                MainActivity.this.finish();
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    /**
     * 每次启动该activity时刷新该activity的朋友圈动态
     */
    @Override
    protected void onResume() {
        super.onResume();
        initShareDongtai();//进入时刷新朋友圈动态
    }

    /**
     * 第一次启动时添加默认的动态
     */
    private void addDongtaiDefault() {
        if (!firstAddDongtai) {
            SQLiteDatabase addData = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            //动态1
            values.clear();
            values.put("shareText", "真理惟一可靠的标准就是永远自相符合。");
            addData.insert("Share", null, values);
            //动态2
            values.clear();
            values.put("shareText", "土地是以它的肥沃和收获而被估价的；才能也是土地，不过它生产的不是粮食，而是真理。如果只能滋生瞑想和幻想的话，即使再大的才能也只是砂地或盐池，那上面连小草也长不出来的。 —— 别林斯基");
            addData.insert("Share", null, values);
            //动态3
            values.clear();
            values.put("shareText", "我需要三件东西：爱情友谊和图书。然而这三者之间何其相通！炽热的爱情可以充实图书的内容，图书又是人们最忠实的朋友。");
            addData.insert("Share", null, values);


        }
    }

}
