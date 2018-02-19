package home.work.pcconfig.data.db.providers;


import java.util.List;

import home.work.pcconfig.business.models.OrderItem;
import io.reactivex.Completable;
import io.reactivex.Observable;

public interface OrdersDp {

    Completable saveOrder(OrderItem orderItem);

    Observable<List<OrderItem>> observeOrders();

    Completable deleteOrderById(int id);
}
