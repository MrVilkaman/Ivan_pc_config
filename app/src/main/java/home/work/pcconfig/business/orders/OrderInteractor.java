package home.work.pcconfig.business.orders;


import home.work.pcconfig.business.models.OrderItem;
import io.reactivex.Completable;

public interface OrderInteractor {
    Completable createOrUpdateOreder(OrderItem orderItem);
}
