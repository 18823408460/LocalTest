package com.unisrobot.firstmodule.contentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by WEI on 2018/5/10.
 */

public class AppContentProvider extends ContentProvider {
    private static final String TAG = "AppContentProvider";
    private Dbhelp dbhelp;
    private String table = "texst_table";
    private String authorith = "com.unisrobot.firstmodule";
    private UriMatcher uriMatcher;

    @Override
    public boolean onCreate() {
        Log.e(TAG, "onCreate: ");
        dbhelp = new Dbhelp(getContext(), table, null, 1);
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(authorith, table, 1);
        uriMatcher.addURI(authorith, table, 2);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        int code = uriMatcher.match(uri);
        Log.e(TAG, "query: code ==" + code);
        switch (code) {
            case 1:
                cursor = dbhelp.getWritableDatabase().query(table, new String[]{"id", "name", "sage", "ssex"}, null, null, null, null, null);

                break;
            case 2:
                cursor = dbhelp.getWritableDatabase().query(table, new String[]{"id", "name", "sage", "ssex"}, null, null, null, null, null);
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int code = uriMatcher.match(uri);
        String type = null;
        switch (code) {
            case 1:
                type = "vnd.android.cursor.dir/" + table;// dir代表多行数据
                break;
            case 2:
                type = "vnd.android.cursor.item/" + table; // item单行
                break;
        }
        return type;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int match = uriMatcher.match(uri);
        Log.e(TAG, "insert: " + match);
        Log.e(TAG, "insert: value====" + values);
        long insert = 0;
        insert = dbhelp.getWritableDatabase().insert(table, null, values);
        return ContentUris.withAppendedId(uri, insert);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
