package home.work.pcconfig.di;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteOpenHelper;

import com.github.mrvilkaman.domainlayer.providers.SchedulersProvider;
import com.pushtorefresh.storio3.sqlite.StorIOSQLite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import home.work.pcconfig.data.db.MySQLiteOpenHelper;
import home.work.pcconfig.data.db.StorIOSQLitePorvider;

@Module
public class DbModule {

    @Provides
    @Singleton
    public StorIOSQLite provideStorIOSQLite(SQLiteOpenHelper someSQLiteOpenHelper,
                                            SchedulersProvider provider) {
        return StorIOSQLitePorvider.provide(someSQLiteOpenHelper, provider);
    }

    @Provides
    @Singleton
    public SQLiteOpenHelper provideSQLiteOpenHelper(Context context) {
        return new MySQLiteOpenHelper(context);
    }


    @Provides
    @Singleton
    public SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("qwer",Context.MODE_PRIVATE);
    }
}