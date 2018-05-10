package com.unisrobot.firstmodule.contentProvider;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.unisrobot.firstmodule.R;

/**
 * Created by Administrator on 2018/5/10.
 */

/**
 * 每个activity或fragment只拥有一个LoaderManager
 *
 * 学习Loader的时候，只是使用CursorLoader把它当作加载封装在ContentProvider中的数据的一种方式，
 * 但是如果我们学会了如何取定义自己的Loader，那么将不仅仅局限于读取ContentProvider的数据
 */
public class ContentProviderActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
    private Dbhelp dbhelp;
    SQLiteDatabase writableDatabase;
    private String table = "texst_table";
    private static final String TAG = "ContentProviderActivity";
    private Uri uri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uri = Uri.parse("content://com.unisrobot.firstmodule/" + table);
        setContentView(R.layout.first_module_activity_contentprovider);

//        testSql();

        textLoader();
    }

    private void testProvider() {
        Uri uri = Uri.parse("content://com.unisrobot.firstmodule/" + table);
        ContentResolver contentResolver = getContentResolver();
    }

    private void textLoader() {
        getLoaderManager().initLoader(0, null, this);
    }

    private void testSql() {
        dbhelp = new Dbhelp(this, table, null, 1);
        writableDatabase = dbhelp.getWritableDatabase();
    }

    private int count;

    public void insert(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", 1);
        contentValues.put("name", "yinxoawei_" + (count++));
        contentValues.put("sage", 10);
        contentValues.put("ssex", "sex");
//        writableDatabase.insert(table, null, contentValues);

        Uri uri = Uri.parse("content://com.unisrobot.firstmodule/" + table);
        ContentResolver contentResolver = getContentResolver();
        contentResolver.insert(uri, contentValues);

        // 通知 监听这   数据变化
        cursorLoader.forceLoad();
    }


    public void query(View view) {
//        String sql = "select * from "+table;
//        writableDatabase.execSQL(sql)
//        Cursor query = writableDatabase.query(table, new String[]{"id", "name", "sage", "ssex"}, null, null, null, null, null);
        Uri uri = Uri.parse("content://com.unisrobot.firstmodule/" + table);
        ContentResolver contentResolver = getContentResolver();
        Cursor query = contentResolver.query(uri, new String[]{"id", "name", "sage", "ssex"}, null, null, null);
        while (query.moveToNext()) {
            String name = query.getString(query.getColumnIndex("name"));
            Log.e(TAG, "query: name===" + name);
        }
        query.close();
    }
    CursorLoader cursorLoader ;
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.e(TAG, "onCreateLoader: " + Thread.currentThread().getName()); // onCreateLoader: main
        cursorLoader = new CursorLoader(getApplicationContext(), uri, null, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) { //  onLoadFinished: main
        Log.e(TAG, "onLoadFinished: "+Thread.currentThread().getName());
        if (data != null){
            Log.e(TAG, "onLoadFinished:  data..." );
            while (data.moveToNext()) {
                String name = data.getString(data.getColumnIndex("name"));
                Log.e(TAG, "onLoadFinished: name===" + name);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.e(TAG, "onLoaderReset: ");
    }
}
