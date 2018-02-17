package home.work.pcconfig.data.db.models;


import android.database.Cursor;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.operations.get.DefaultGetResolver;

import home.work.pcconfig.business.models.OrderItem;

public class OrderItemGetResolver extends DefaultGetResolver<OrderItem> {

    @NonNull
    @Override
    public OrderItem mapFromCursor(@NonNull StorIOSQLite storIOSQLite, @NonNull Cursor cursor) {
        return new OrderItem(getInt(cursor, OrderTableDb.ID),
                getString(cursor, OrderTableDb.COLUME_MOTHER_BOARD),
                getInt(cursor, OrderTableDb.COLUME_CPU_INTER_TYPE),
                getString(cursor, OrderTableDb.COLUME_GPU),
                getInt(cursor, OrderTableDb.COLUME_RAM_SIZE),
                getInt(cursor, OrderTableDb.COLUME_STOGARE_SIZE),
                getBool(cursor, OrderTableDb.COLUME_SSD),
                getBool(cursor, OrderTableDb.COLUME_GAMING));
    }

    private boolean getBool(@NonNull Cursor cursor, String fieldName) {
        return getInt(cursor, fieldName) != 0;
    }

    private int getInt(@NonNull Cursor cursor, String fieldName) {
        return cursor.getInt(cursor.getColumnIndex(fieldName));
    }

    private String getString(@NonNull Cursor cursor, String fieldName) {
        return cursor.getString(cursor.getColumnIndex(fieldName));
    }
}
