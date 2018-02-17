package home.work.pcconfig.business.viewer;


import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import home.work.pcconfig.business.models.OrdersItem;
import io.reactivex.Observable;

public class ViewerInteractorImpl implements ViewerInteractor {

    @Inject
    ViewerInteractorImpl() {
    }

    @Override
    public Observable<List<OrdersItem>> observeOrders() {
        final OrdersItem ordersItem = new OrdersItem(1, "Asus Roq", 9, "Nvidia GTX 1080Ti", 32, 1025, true, true);
        final OrdersItem ordersItem2 = new OrdersItem(1, "Asus Roq", 9, "Nvidia GTX 1080Ti", 32, 1025, true, true);
        return Observable.just(Arrays.asList(ordersItem,ordersItem2));
    }
}
