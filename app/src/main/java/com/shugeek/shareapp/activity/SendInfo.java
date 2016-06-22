package com.shugeek.shareapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.shugeek.shareapp.R;

/**
 * Created by Administrator on 2016/6/22.
 */
public class SendInfo extends Activity {
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
    }


}
