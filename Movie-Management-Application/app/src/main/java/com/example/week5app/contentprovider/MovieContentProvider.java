package com.example.week5app.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;


public class MovieContentProvider extends ContentProvider {
    public static final String Content_AUTHORITY = "fit2081.Andrew";
    public static final Uri Content_Uri = Uri.parse("content://"+ Content_AUTHORITY);
    MovieDataBase db;
    public MovieContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");

    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
       Long newId = db.getOpenHelper().getWritableDatabase().insert(MovieDataBaseAttributes.MovieAttri,0,values);
       return ContentUris.withAppendedId(Content_Uri,newId);

    }

    @Override
    public boolean onCreate() {
        db = MovieDataBase.getDataBase(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();

        builder.setTables(MovieDataBaseAttributes.MovieAttri);
        String Query = builder.buildQuery(projection,selection,null,null,null,null);
        Cursor cursor = db.getOpenHelper().getReadableDatabase().query(Query);

        return (cursor);

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}