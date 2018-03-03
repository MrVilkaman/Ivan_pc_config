package home.work.pcconfig.data.db;


import android.database.sqlite.SQLiteOpenHelper;

import com.github.mrvilkaman.domainlayer.providers.SchedulersProvider;
import com.pushtorefresh.storio3.sqlite.impl.DefaultStorIOSQLite;


public class StorIOSQLitePorvider {


	public static DefaultStorIOSQLite provide(SQLiteOpenHelper someSQLiteOpenHelper, SchedulersProvider provider) {
		return DefaultStorIOSQLite.builder()
				.sqliteOpenHelper(someSQLiteOpenHelper)
//				.addTypeMapping(OrderItem.class,
//						SQLiteTypeMapping.<OrderItem>builder()
//								.putResolver(new OrderItemPutResolver())
//								.getResolver(new OrderItemGetResolver())
//								.deleteResolver(new OrderItemDeleteResolver())
//								.build())
                .defaultRxScheduler(provider.io())
				.build();
	}
}
