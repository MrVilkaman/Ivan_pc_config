package home.work.pcconfig.business.viewer;


import java.util.List;

import home.work.pcconfig.business.models.OrdersItem;
import io.reactivex.Observable;

public interface ViewerInteractor {
    Observable<List<OrdersItem>> observeOrders();
}
