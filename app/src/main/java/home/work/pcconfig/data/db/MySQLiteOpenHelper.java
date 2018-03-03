package home.work.pcconfig.data.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final int INIT = 1;
    private static final int version = INIT;

    public MySQLiteOpenHelper(Context context) {
        super(context, "pcDb", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.beginTransaction();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        switch (oldVersion) {
            case INIT:
        }
    }
}