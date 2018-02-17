package home.work.pcconfig.business.orders;


import javax.inject.Inject;

import home.work.pcconfig.business.models.OrderItem;
import io.reactivex.Completable;

public class OrderInteractorImpl implements OrderInteractor {

    @Inject
    public OrderInteractorImpl() {
    }

    @Override
    public Completable createOrUpdateOreder(OrderItem orderItem) {
        return Completable.complete();
    }
}
