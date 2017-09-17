package rikkei.musicplayer.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import rikkei.musicplayer.model.Song;

/**
 * Created by DUC on 12/09/2017.
 */

public class DataHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =1;

    private static final String DATABASE_NAME ="songDb";

    private static final String TABLE_NOTE ="songTable";

    private static final String KEY_ID ="id";

    private static final String KEY_NAME ="name";

    private static final String KEY_ARTIST ="artist";

    public DataHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DataHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + TABLE_NOTE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT," + KEY_ARTIST +");";
        db.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        onCreate(db);
    }

    public void addSong(Song song){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,song.getName());
        values.put(KEY_ARTIST,song.getArtist());
        db.insert(TABLE_NOTE,null,values);
        db.close();
    }

    public Song getSong(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NOTE, new String[] { KEY_ID, KEY_NAME,KEY_ARTIST}, KEY_ID+" = ?",new String[] { String.valueOf(id) }, null, null,null);
        if (cursor!=null)
            cursor.moveToFirst();
        Song song = new Song (Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))),cursor.getString(cursor.getColumnIndex(KEY_NAME)), cursor.getString(cursor.getColumnIndex(KEY_ARTIST)));
        cursor.close();
        return song;
    }

    public ArrayList<Song> getAllSong() {
        ArrayList<Song> songs = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NOTE +" ORDER BY "+KEY_NAME+" DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Song song = new Song();
                song.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                song.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                song.setArtist(cursor.getString(cursor.getColumnIndex(KEY_ARTIST)));
                songs.add(song);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return songs;
    }

    public int updateSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, song.getName());
        values.put(KEY_ARTIST, song.getArtist());
        return db.update(TABLE_NOTE, values, KEY_ID + " = ?",new String[] { String.valueOf(song.getId()) });
    }

    public void deleteNote(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTE, KEY_ID + " = ?",new String[]{String.valueOf(song.getId())});
        db.close();
    }

    public int getSongsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NOTE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }
}
