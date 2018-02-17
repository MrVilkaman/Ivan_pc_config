package home.work.pcconfig.data.db.models;


import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.sqlite.queries.Query;

public class OrderTableDb {

    public static final String TABLE = "OrderTableDb";

    public static final String ID = "id";
    public static final String COLUME_MOTHER_BOARD = "motherBoard";
    public static final String COLUME_CPU_INTER_TYPE = "cpuInterType";
    public static final String COLUME_GPU = "gpu";
    public static final String COLUME_RAM_SIZE = "ramSize";
    public static final String COLUME_STOGARE_SIZE = "stogareSize";
    public static final String COLUME_SSD = "ssd";
    public static final String COLUME_GAMING = "gaming";

    public static Query getAllQuery() {
        return Query.builder().table(TABLE).build();
    }

    //@formatter:off
	@NonNull
	public static String getCreateTableQuery() {
		return "CREATE TABLE " + TABLE + "( "
				+ ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
				+ COLUME_MOTHER_BOARD + " TEXT NOT NULL, "
				+ COLUME_CPU_INTER_TYPE + " INTEGER NOT NULL, "
				+ COLUME_GPU + " TEXT NOT NULL,"
				+ COLUME_RAM_SIZE + " INTEGER NOT NULL, "
				+ COLUME_STOGARE_SIZE + " INTEGER NOT NULL, "
				+ COLUME_SSD + " INTEGER NOT NULL, "
				+ COLUME_GAMING + " INTEGER NOT NULL "
				+ ");";
	}
	//@formatter:on

}
