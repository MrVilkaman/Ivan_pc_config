package home.work.pcconfig.business.viewer;


import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import home.work.pcconfig.business.models.OrdersItem;
import io.reactivex.Observable;

public class ViewerInteractorImpl implements ViewerInteractor {

    @Inject
    public ViewerInteractorImpl() {
    }

    @Override
    public Observable<List<OrdersItem>> observeOrders() {
        return Observable.just(Arrays.asList(new OrdersItem()));
    }
}
