package home.work.pcconfig.business.orders;


import javax.inject.Inject;

import home.work.pcconfig.business.models.OrderItem;
import home.work.pcconfig.data.db.providers.OrdersDp;
import home.work.pcconfig.di.SomeId;
import io.reactivex.Completable;
import io.reactivex.Maybe;

public class OrderInteractorImpl implements OrderInteractor {

    private final int currentId;
    private final OrdersDp ordersDp;

    @Inject
    public OrderInteractorImpl(@SomeId int currentId, OrdersDp ordersDp) {
        this.currentId = currentId;
        this.ordersDp = ordersDp;
    }

    @Override
    public Completable createOrUpdateOrder(OrderItem orderItem) {
        if (currentId != -1) {
            orderItem.setId(currentId);
        }
        return ordersDp.saveOrder(orderItem);
    }

    @Override
    public Maybe<OrderItem> loadOrderItem() {
        if (currentId == -1) {
            return Maybe.empty();
        }
        return ordersDp.getOrderById(currentId);
    }
}
