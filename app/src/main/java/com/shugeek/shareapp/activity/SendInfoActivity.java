package com.shugeek.shareapp.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shugeek.shareapp.R;
import com.shugeek.shareapp.database.MyDatabaseHelper;

/**
 * Created by Administrator on 2016/6/22.
 */
public class SendInfoActivity extends Activity {
    private MyDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_info);
        initIndex();

    }

    /**
     * 左上箭头按钮点击回到分享圈页面
     */
    private void initIndex() {
        LinearLayout backArrow = (LinearLayout) findViewById(R.id.send_info_arrow_left);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //结束当前的界面
                finish();
            }
        });

        Button send_info_button = (Button) findViewById(R.id.send_info_button);
        send_info_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper = new MyDatabaseHelper(SendInfoActivity.this, "Share.db", null, 1, SendInfoActivity.this);
                SQLiteDatabase addData = databaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                /*values.put("nickName","张三");
                values.put("signature","张三的签名");
                addData.insert("User",null,values);*/

                values.clear();
                TextView text = (TextView) findViewById(R.id.send_info_text);
                values.put("shareText", text.getText().toString());
                addData.insert("Share", null, values);

                //结束当前的界面
                finish();
            }
        });

    }


}
