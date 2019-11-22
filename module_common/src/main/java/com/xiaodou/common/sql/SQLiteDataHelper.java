package com.xiaodou.common.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 项目名称：MvpFrame
 * 创建人：Administrator
 * 创建时间：2019/10/11 11:04
 * 注意：
 * 1、mContext必须是有效的
 * 2、实体类必须要序列化
 */
public class SQLiteDataHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "lhz_data.db";//数据库文件名
    private static SQLiteDatabase INSTANCE;
    private Context mContext;

    public SQLiteDatabase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SQLiteDataHelper(mContext).getWritableDatabase();
        }
        return INSTANCE;
    }

    public SQLiteDataHelper(Context context) {
        this(context, DB_NAME, null, 1);
        this.mContext = context;
    }

    public SQLiteDataHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, version);
    }

    //首次创建数据库时调用,一般进行建库建表操作
    // music 是表名
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS music(_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,data BLOB);";
        //创建表
        db.execSQL(createTable);
    }

    //当数据库的版本发生变化的时候会自动执行，禁止人为调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
