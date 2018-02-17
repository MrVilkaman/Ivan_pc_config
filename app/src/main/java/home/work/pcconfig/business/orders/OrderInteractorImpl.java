package home.work.pcconfig.business.orders;


import javax.inject.Inject;

import home.work.pcconfig.business.models.OrderItem;
import home.work.pcconfig.data.db.providers.OrdersDp;
import io.reactivex.Completable;

public class OrderInteractorImpl implements OrderInteractor {

    private final OrdersDp ordersDp;

    @Inject
    public OrderInteractorImpl(OrdersDp ordersDp) {
        this.ordersDp = ordersDp;
    }

    @Override
    public Completable createOrUpdateOreder(OrderItem orderItem) {
        return ordersDp.saveOrder(orderItem);
    }
}
