package home.work.pcconfig.data.db;


import android.database.sqlite.SQLiteOpenHelper;

import com.github.mrvilkaman.domainlayer.providers.SchedulersProvider;
import com.pushtorefresh.storio3.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio3.sqlite.impl.DefaultStorIOSQLite;

import home.work.pcconfig.business.models.OrderItem;
import home.work.pcconfig.data.db.models.OrderItemDeleteResolver;
import home.work.pcconfig.data.db.models.OrderItemGetResolver;
import home.work.pcconfig.data.db.models.OrderItemPutResolver;


public class StorIOSQLitePorvider {


	public static DefaultStorIOSQLite provide(SQLiteOpenHelper someSQLiteOpenHelper, SchedulersProvider provider) {
		return DefaultStorIOSQLite.builder()
				.sqliteOpenHelper(someSQLiteOpenHelper)
				.addTypeMapping(OrderItem.class,
						SQLiteTypeMapping.<OrderItem>builder()
								.putResolver(new OrderItemPutResolver())
								.getResolver(new OrderItemGetResolver())
								.deleteResolver(new OrderItemDeleteResolver())
								.build())
                .defaultRxScheduler(provider.io())
				.build();
	}
}
