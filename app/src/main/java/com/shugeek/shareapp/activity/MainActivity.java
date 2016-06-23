package com.shugeek.shareapp.activity;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.shugeek.shareapp.R;
import com.shugeek.shareapp.adapter.ContactAdapter;
import com.shugeek.shareapp.database.MyDatabaseHelper;

public class MainActivity extends TabActivity {
    private Context mContext;
    private static long EXIT_TIME = 0;
    private TabHost mTabHost;
    private MyDatabaseHelper databaseHelper;

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
        databaseHelper = new MyDatabaseHelper(this, "Share.db", null, 3, mContext);
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
}
