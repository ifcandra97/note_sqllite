package com.candra.latihanprepareutssqlite.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDB extends SQLiteOpenHelper
{
    private Context context;
    private static final String DB_NAME = "dbNote";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "tbl_note";
    private static final String FIELD_ID = "id";
    private static final String FIELD_JUDUL = "judul";
    private static final String FIELD_DESC = "desc";

    public MyDB(@Nullable Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FIELD_JUDUL + " VARCHAR(100), " + FIELD_DESC + " TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    // Retrieve Note
    public Cursor bacaNote()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ;";
        Cursor c = null;

        if(db != null)
        {
            c = db.rawQuery(query, null);
        }
        return c;
    }
    

    // Tambah Note
    public long tambahNote(String judul, String desc)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(FIELD_JUDUL, judul);
        cv.put(FIELD_DESC, desc);
        long ex = db.insert(TABLE_NAME, null, cv);

        return ex;
    }

    //Ubah data
    public long ubahNote(String id, String judul, String desc)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(FIELD_JUDUL, judul);
        cv.put(FIELD_DESC, desc);
        long ex = db.update(TABLE_NAME, cv, "id = ?", new String[]{id});

        return ex;
    }

    public long hapusNote(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long ex = db.delete(TABLE_NAME, "id = ?", new String[]{id});
        return ex;
    }


}
