package com.unisrobot.firstmodule.cameraview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.LruCache;

import java.lang.ref.SoftReference;

/**
 * Created by WEI on 2018/5/6.
 */

public class BitmapLruCache {
    private static volatile BitmapLruCache bitmapLruCache;
    private LruCache<String, Bitmap> lruCache;
    // 两种方式的区别：
    private LruCache<String, SoftReference<Bitmap>> lruCacheSoft;

    public static BitmapLruCache getBitmapLruCache() {
        if (bitmapLruCache == null) {
            synchronized (BitmapLruCache.class) {
                if (bitmapLruCache == null) {
                    bitmapLruCache = new BitmapLruCache();
                }
            }
        }
        return bitmapLruCache;
    }

    private BitmapLruCache() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        int cachMem = (int) (maxMemory / 8);
        lruCache = new LruCache<String, Bitmap>(cachMem) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
//                return value.getRowBytes() * value.getHeight() / 1024;
                return value.getByteCount();
            }

            //Lru算法的原理是把近期最少使用的数据给移除掉，当然前提是当前数据的量大于设定的最大值。
            //LruCache 没有真正的释放内存，只是从 Map中移除掉数据，真正释放内存还是要用户手动释放
            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                super.entryRemoved(evicted, key, oldValue, newValue);
                Log.e(TAG, "entryRemoved: "+key );
            }
        };
    }

    public void putBitmap(int resId, Context context) {
        Bitmap bitmap = lruCache.get(String.valueOf(resId));
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
            lruCache.put(String.valueOf(resId), bitmap);
        } else {
            lruCache.put(String.valueOf(resId), bitmap);
        }
    }

    private static final String TAG = "BitmapLruCache";
    public Bitmap getBitmap(int resId, Context context) {
        String s = String.valueOf(resId);
        Bitmap bitmap = lruCache.get(s);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
            lruCache.put(String.valueOf(resId), bitmap);
            Log.e(TAG, "getBitmap: from decode" );
        }else {
            Log.e(TAG, "getBitmap: from cache" );
        }
        return bitmap;
    }
}
