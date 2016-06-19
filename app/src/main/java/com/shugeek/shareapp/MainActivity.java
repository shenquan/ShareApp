package com.shugeek.shareapp;

import android.app.TabActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = getApplicationContext();

        TabHost tabHost = getTabHost();

        TabHost.TabSpec contactTab1 = tabHost.newTabSpec("tab1")
                .setIndicator("联 系 人")
                .setContent(R.id.share_tab1);
        tabHost.addTab(contactTab1);

        TabHost.TabSpec shareGroupTab2 = tabHost.newTabSpec("tab2")
                .setIndicator("分 享 圈")
                .setContent(R.id.share_tab2);
        tabHost.addTab(shareGroupTab2);

        ListView contactListView = (ListView) findViewById(R.id.share_contact_list);
        ContactAdapter contactAdapter = new ContactAdapter(mContext);
        contactListView.setAdapter(contactAdapter);

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }
}
