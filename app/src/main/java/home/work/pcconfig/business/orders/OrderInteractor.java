package home.work.pcconfig.business.orders;


import home.work.pcconfig.business.models.OrderItem;
import io.reactivex.Completable;
import io.reactivex.Maybe;

public interface OrderInteractor {
    Maybe<OrderItem> loadOrderItem();

    Completable createOrUpdateOrder(OrderItem orderItem);
}
