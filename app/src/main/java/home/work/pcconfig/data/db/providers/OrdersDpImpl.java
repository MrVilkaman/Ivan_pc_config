package home.work.pcconfig.data.db.providers;


import com.pushtorefresh.storio3.sqlite.StorIOSQLite;

import javax.inject.Inject;

import home.work.pcconfig.business.models.OrderItem;
import io.reactivex.Completable;

public class OrdersDpImpl implements OrdersDp {

    private StorIOSQLite db;

    @Inject
    public OrdersDpImpl(StorIOSQLite db) {
        this.db = db;
    }

    @Override
    public Completable saveOrder(OrderItem orderItem) {
        return db.put().object(orderItem)
                .prepare()
                .asRxCompletable();
    }
}
