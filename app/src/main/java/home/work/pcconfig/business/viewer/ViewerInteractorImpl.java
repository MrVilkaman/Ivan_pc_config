package home.work.pcconfig.business.viewer;


import java.util.List;

import javax.inject.Inject;

import home.work.pcconfig.business.models.OrderItem;
import home.work.pcconfig.data.db.providers.OrdersDp;
import io.reactivex.Observable;

public class ViewerInteractorImpl implements ViewerInteractor {

    private final OrdersDp ordersDp;

    @Inject
    ViewerInteractorImpl(OrdersDp ordersDp) {
        this.ordersDp = ordersDp;
    }

    @Override
    public Observable<List<OrderItem>> observeOrders() {
       return ordersDp.observeOrders();
    }
}
