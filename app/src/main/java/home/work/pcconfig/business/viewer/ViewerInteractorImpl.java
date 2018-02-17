package home.work.pcconfig.business.viewer;


import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import home.work.pcconfig.business.models.OrderItem;
import io.reactivex.Observable;

public class ViewerInteractorImpl implements ViewerInteractor {

    @Inject
    ViewerInteractorImpl() {
    }

    @Override
    public Observable<List<OrderItem>> observeOrders() {
        final OrderItem ordersItem = new OrderItem(1, "Asus Roq", 9, "Nvidia GTX 1080Ti", 32, 1025, true, true);
        final OrderItem ordersItem2 = new OrderItem(1, "Asus Roq", 9, "Nvidia GTX 1080Ti", 32, 1025, true, true);
        return Observable.just(Arrays.asList(ordersItem,ordersItem2));
    }
}
