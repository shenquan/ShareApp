package com.shugeek.shareapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/6/22.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String CREATE_USER = "create table User ("
            + "userId integer primary key autoincrement,"
            + "nickName text,"
            + "signature text)";
    private static final String CREATE_SHARE = "create table Share ("
            + "shareId integer primary key autoincrement,"
            + "shareText text)";
    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, Context mContext) {
        super(context, name, factory, version);
        this.mContext = mContext;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER);
        sqLiteDatabase.execSQL(CREATE_SHARE);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
