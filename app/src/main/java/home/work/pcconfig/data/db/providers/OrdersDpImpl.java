package home.work.pcconfig.data.db.providers;


import com.pushtorefresh.storio3.sqlite.StorIOSQLite;

import java.util.List;

import javax.inject.Inject;

import home.work.pcconfig.business.models.OrderItem;
import home.work.pcconfig.data.db.models.OrderTableDb;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Observable;

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

    @Override
    public Observable<List<OrderItem>> observeOrders() {
        return db.get().listOfObjects(OrderItem.class)
                .withQuery(OrderTableDb.getAllQuery())
                .prepare()
                .asRxFlowable(BackpressureStrategy.LATEST)
                .toObservable();
    }
}
