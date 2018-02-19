package home.work.pcconfig.ui.viewer;


import com.github.mrvilkaman.presentationlayer.fragments.core.BasePresenter;
import com.github.mrvilkaman.presentationlayer.fragments.core.ItemListener;
import com.github.mrvilkaman.presentationlayer.subscriber.ViewSubscriber;

import java.util.List;

import javax.inject.Inject;

import home.work.pcconfig.R;
import home.work.pcconfig.business.models.OrderItem;
import home.work.pcconfig.business.viewer.ViewerInteractor;
import home.work.pcconfig.ui.global.ScreenKeys;
import ru.terrakok.cicerone.Router;

class ViewerPresenter extends BasePresenter<ViewerView> {

    private ViewerInteractor viewerInteractor;
    private Router router;
    private final GetSelectionHelper selectionHelper;


    @Inject
    ViewerPresenter(ViewerInteractor viewerInteractor, Router router, GetSelectionHelper selectionHelper) {
        this.viewerInteractor = viewerInteractor;
        this.router = router;
        this.selectionHelper = selectionHelper;
    }

    @Override
    public void onViewAttached() {
       subscribeUI(viewerInteractor.observeOrders(), new OrdersSubscriber());
    }

    void deleteRow() {
        checkSelectionAndGetId(R.string.error_no_selection, id -> {
            selectionHelper.resetSelection();
            viewerInteractor.deleteOrderById(id);
        });
    }


    void addRow() {
        router.navigateTo(ScreenKeys.KEY_NEW_ORDER);
    }

    void editRow() {
        checkSelectionAndGetId(R.string.error_no_selection,id -> {
            selectionHelper.resetSelection();
            router.navigateTo(ScreenKeys.KEY_UPDATE_ORDER,id);
        });
    }

    private void checkSelectionAndGetId(int errorId, ItemListener<Integer> idCallback) {
        final OrderItem selected = selectionHelper.getSelected();
        if (selected == null) {
            uiResolver().showToast(errorId);
        }else {
            idCallback.click(selected.getId());
        }
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
