package home.work.pcconfig.data.db.providers;


import home.work.pcconfig.business.models.OrderItem;
import io.reactivex.Completable;

public interface OrdersDp {

    Completable saveOrder(OrderItem orderItem);
}
