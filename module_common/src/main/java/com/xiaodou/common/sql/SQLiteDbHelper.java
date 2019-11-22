package com.xiaodou.common.sql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xiaodou.common.MainApplication;

import java.util.List;


/**
 * 项目名称：MvpFrame
 * 创建人：Administrator
 * 创建时间：2019/10/11 11:54
 * 类描述：
 */
public abstract class SQLiteDbHelper {

    protected SQLiteDataHelper mSQLiteDataHelper;

    private SQLiteDataHelper getObjectDBHelper() {
        if (mSQLiteDataHelper == null) {
            mSQLiteDataHelper = new SQLiteDataHelper(MainApplication.getInstance());
        }
        return mSQLiteDataHelper;
    }

    /**
     * 获取数据库对象
     **/
    protected SQLiteDatabase getDateBase() {
        return getObjectDBHelper().getInstance();
    }

    /**
     * 关闭数据库
     **/
    protected void closeDB() {
        SQLiteDatabase db = getDateBase();
        if (db != null) {
            db.close();
        }
    }

    /**
     * 判断表是否存在
     *
     * @param tableName：表名
     * @return
     */
    protected boolean isTableExist(String tableName) {
        Cursor cursor = getDateBase().rawQuery("select name from sqlite_master where type='table';", null);
        while (cursor.moveToNext()) {
            //遍历出表名
            String name = cursor.getString(0);
            if (name.equals(tableName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查询
     **/
    protected abstract List<?> checkAll();

    /**
     * 添加
     **/
    protected abstract <T> void insertModel(T t);

    /**
     * 删除
     **/
    protected abstract void delete(Object obj);

    /**
     * 更新
     **/
    protected abstract void update(Object obj);

}
