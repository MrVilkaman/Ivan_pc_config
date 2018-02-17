package home.work.pcconfig.ui.viewer;


import com.github.mrvilkaman.presentationlayer.fragments.core.BasePresenter;
import com.github.mrvilkaman.presentationlayer.subscriber.ViewSubscriber;

import java.util.List;

import javax.inject.Inject;

import home.work.pcconfig.business.models.OrderItem;
import home.work.pcconfig.business.viewer.ViewerInteractor;
import home.work.pcconfig.ui.global.ScreenKeys;
import ru.terrakok.cicerone.Router;

class ViewerPresenter extends BasePresenter<ViewerView> {

    private ViewerInteractor viewerInteractor;
    private Router router;


    @Inject
    ViewerPresenter(ViewerInteractor viewerInteractor, Router router) {
        this.viewerInteractor = viewerInteractor;
        this.router = router;
    }

    @Override
    public void onViewAttached() {
       subscribeUI(viewerInteractor.observeOrders(), new OrdersSubscriber());
    }

    void deleteRow() {

    }

    void addRow() {
        router.navigateTo(ScreenKeys.KEY_NEW_ORDER);
    }

    void editRow() {

    }

    private static class OrdersSubscriber extends ViewSubscriber<ViewerView, List<OrderItem>> {

        @Override
        public void onNext(List<OrderItem> ordersItems) {
            if (ordersItems.isEmpty()) {
                view().showEmptyState();
            }else {
                view().bindOrders(ordersItems);
            }
        }
    }

}
