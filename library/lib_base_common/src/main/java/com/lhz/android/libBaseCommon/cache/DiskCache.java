package com.lhz.android.libBaseCommon.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by jack on 2018/3/10.
 */

public class DiskCache {

    private static DiskCache instance;
    private final int maxSize = 50;//单位M
    private DiskLruCache diskLruCache;
    private Context context;

    private DiskCache(Context context) {
        this.context = context;
        if (diskLruCache == null) {
            try {
                File directory = CacheUtils.getCacheDirectory(context);
                int appVersion = CacheUtils.getAppVersion(context);
                diskLruCache = DiskLruCache.open(directory, appVersion, 1, maxSize * 1024 * 1024);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static DiskCache getInstance(Context context) {
        if (instance == null) {
            synchronized (DiskCache.class) {
                if (instance == null) {
                    instance = new DiskCache(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    /**
     * 保存Object对象，Object要实现Serializable
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        try {
            key = CacheUtils.toMd5Key(key);
            DiskLruCache.Editor editor = diskLruCache.edit(key);
            if (editor != null) {
                OutputStream os = editor.newOutputStream(0);
                if (CacheUtils.writeObject(os, value)) {
                    editor.commit();
                } else {
                    editor.abort();
                }
                diskLruCache.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存Bitmap
     *
     * @param key
     * @param bitmap
     */
    public void putBitmap(String key, Bitmap bitmap) {
        put(key, CacheUtils.bitmap2Bytes(bitmap));
    }

    /**
     * 保存Drawable
     *
     * @param key
     * @param value
     */
    public void putDrawable(String key, Drawable value) {
        putBitmap(key, CacheUtils.drawable2Bitmap(value));
    }

    /**
     * 根据key获取保存对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T get(String key) {
        try {
            key = CacheUtils.toMd5Key(key);
            DiskLruCache.Snapshot snapshot = diskLruCache.get(key);

            if (snapshot != null) {
                InputStream inputStream = snapshot.getInputStream(0);
                return (T) CacheUtils.readObject(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 新增集合中的某条数据
     *
     * @param key  集合的key值
     * @param bean 更新的position位置
     * @return
     */
    public <T> ArrayList<T> itemAddList(String key, T bean) throws Exception {
        try {
            ArrayList<T> arrayList = get(key);
            if (arrayList != null && arrayList.size() > 0) {
                arrayList.add(bean);
                put(key, arrayList);
                return get(key);
            } else {
                throw new Exception("增加集合数据对象失败：" + "list is empty");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("增加集合数据对象失败：" + e.getMessage());
        }
    }

    /**
     * 删除集合中的某条数据
     *
     * @param key      集合的key值
     * @param position 更新的position位置
     * @return
     */
    public <T> ArrayList<T> itemDeleteList(String key, int position) throws Exception {
        try {
            ArrayList<T> arrayList = get(key);
            if (arrayList != null && arrayList.size() > 0) {
                arrayList.remove(position);
                put(key, arrayList);
                return get(key);
            } else {
                throw new Exception("删除集合数据对象失败：" + "list is empty");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("删除集合数据对象失败：" + e.getMessage());
        }
    }

    /**
     * 查询获取集合中的某条数据
     *
     * @param key      集合的key值
     * @param position 更新的position位置
     * @return
     */
    public <T> T itemGetList(String key, int position) throws Exception {
        try {
            ArrayList<T> arrayList = get(key);
            if (arrayList != null && arrayList.size() > 0) {
                return arrayList.get(position);
            } else {
                throw new Exception("查询集合数据对象失败：" + "list is empty");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("查询集合数据对象失败：" + e.getMessage());
        }
    }

    /**
     * 更新集合中的某条数据
     *
     * @param key  集合的key
     * @param user 更新的对象
     * @return
     */
    public void updateListItem(String key, CacheBean user) throws Exception {

        try {
            ArrayList<CacheBean> oldList = get(key);
            if (oldList != null && user != null) {
                for (int i = 0; i < oldList.size(); i++) {
                    if (oldList.get(i).getId().equals(user.getId())) {
                        oldList.remove(i);
                        oldList.add(i, user);
                    }
                }
                put(key, oldList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("更新集合数据对象失败：" + e.getMessage());
        }
    }

    /**
     * 获取Bitmap
     *
     * @param key
     * @return
     */
    public Bitmap getBitmap(String key) {
        byte[] bytes = (byte[]) get(key);
        if (bytes == null) return null;
        return CacheUtils.bytes2Bitmap(bytes);
    }

    /**
     * 获取Drawable
     *
     * @param key
     * @return
     */
    public Drawable getDrawable(String key) {
        byte[] bytes = (byte[]) get(key);
        if (bytes == null) {
            return null;
        }
        return CacheUtils.bitmap2Drawable(context, CacheUtils.bytes2Bitmap(bytes));
    }

    public long size() {
        return diskLruCache.size();
    }

    public void setMaxSize(int maxSize) {
        diskLruCache.setMaxSize(maxSize * 1024 * 1024);
    }

    public File getDirectory() {
        return diskLruCache.getDirectory();
    }

    public long getMaxSize() {
        return diskLruCache.getMaxSize();
    }

    /**
     * 从网络获取最新数据的时候才应该调用remove()方法来移除缓存
     */
    public boolean remove(String key) {
        try {
            key = CacheUtils.toMd5Key(key);
            return diskLruCache.remove(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 用于将所有的缓存数据全部删除
     */
    public void delete() {
        try {
            diskLruCache.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用于将内存中的操作记录同步到日志文件
     * onPause()方法中去调用一次flush()
     */
    public void flush() {
        try {
            diskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用于将DiskLruCache关闭掉，是和open()方法对应的一个方法
     * onDestroy()方法中去调用close()方法
     */
    public void close() {
        try {
            diskLruCache.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isClosed() {
        return diskLruCache.isClosed();
    }

}
