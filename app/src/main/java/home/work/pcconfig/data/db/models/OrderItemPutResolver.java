package home.work.pcconfig.data.db.models;


import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.sqlite.operations.put.DefaultPutResolver;
import com.pushtorefresh.storio3.sqlite.queries.InsertQuery;
import com.pushtorefresh.storio3.sqlite.queries.UpdateQuery;

import home.work.pcconfig.business.models.OrderItem;

public class OrderItemPutResolver extends DefaultPutResolver<OrderItem> {
    @NonNull
    @Override
    protected InsertQuery mapToInsertQuery(@NonNull OrderItem object) {
        return InsertQuery.builder().table(OrderTableDb.TABLE).build();
    }

    @NonNull
    @Override
    protected UpdateQuery mapToUpdateQuery(@NonNull OrderItem object) {
        return UpdateQuery.builder()
                .table(OrderTableDb.TABLE)
                .where(OrderTableDb.ID + " = ?")
                .whereArgs(object.getId())
                .build();
    }

    @NonNull
    @Override
    protected ContentValues mapToContentValues(@NonNull OrderItem object) {
        final ContentValues contentValues = new ContentValues();

        contentValues.put(OrderTableDb.COLUME_MOTHER_BOARD, object.getMotherBoard());
        contentValues.put(OrderTableDb.COLUME_CPU_INTER_TYPE, object.getCpuInterType());
        contentValues.put(OrderTableDb.COLUME_GPU, object.getGpu());
        contentValues.put(OrderTableDb.COLUME_RAM_SIZE, object.getRamSize());
        contentValues.put(OrderTableDb.COLUME_STOGARE_SIZE, object.getStogareSize());
        contentValues.put(OrderTableDb.COLUME_SSD, object.isSsd());
        contentValues.put(OrderTableDb.COLUME_GAMING, object.isGaming());

        return contentValues;
    }
}
