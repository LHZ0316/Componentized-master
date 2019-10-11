package com.xiaodou.common.sql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * 项目名称：MvpFrame
 * 创建人：Administrator
 * 创建时间：2019/10/11 11:55
 * 类描述：
 */
public class DbDemoHelper extends SQLiteDbHelper {

    private DbDemoHelper() {
    }

    private static class Holder {
        private static DbDemoHelper instance = new DbDemoHelper();
    }

    public static DbDemoHelper getInstance() {
        return DbDemoHelper.Holder.instance;
    }

    @Override
    public List<?> checkAll() {
        List<SQLiteDemo>list=new ArrayList<>();
        SQLiteDatabase database = getDateBase();
        Cursor cursor = database.rawQuery("select * from music", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                byte data[] = cursor.getBlob(cursor.getColumnIndex("data"));
                ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(data);
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(arrayInputStream);
                    SQLiteDemo music = (SQLiteDemo) inputStream.readObject();
                    inputStream.close();
                    arrayInputStream.close();
                    list.add(music);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            cursor.close();
        }
        return list;
    }

    @Override
    public <T> void insertModel(T t) {
        SQLiteDemo music= (SQLiteDemo) t;
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream);
            objectOutputStream.writeObject(music);
            objectOutputStream.flush();
            byte data[] = arrayOutputStream.toByteArray();
            objectOutputStream.close();
            arrayOutputStream.close();
            SQLiteDatabase database = getDateBase();
            String sql="insert into music(data) values (?)";
            database.execSQL(sql,new Object[]{ data });
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void delete(Object obj) {
        List<Object> list = (List<Object>) checkAll();
        if (list!=null&&list.size()>0){}
    }


    @Override
    protected void update(Object obj) {

    }
}
