package home.work.pcconfig.data.db.models;


import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.sqlite.operations.delete.DefaultDeleteResolver;
import com.pushtorefresh.storio3.sqlite.queries.DeleteQuery;

import home.work.pcconfig.business.models.OrderItem;

public class OrderItemDeleteResolver extends DefaultDeleteResolver<OrderItem> {
    @NonNull
    @Override
    protected DeleteQuery mapToDeleteQuery(@NonNull OrderItem object) {
        return DeleteQuery.builder()
                .table(OrderTableDb.TABLE)
                .where(OrderTableDb.ID+" = ?")
                .whereArgs(object.getId())
                .build();
    }
}
