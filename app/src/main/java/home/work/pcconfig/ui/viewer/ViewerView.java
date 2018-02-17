package home.work.pcconfig.ui.viewer;


import com.github.mrvilkaman.presentationlayer.fragments.core.BaseView;

import java.util.List;

import home.work.pcconfig.business.models.OrdersItem;

public interface ViewerView extends BaseView {
    void showEmptyState();

    void bindOrders(List<OrdersItem> ordersItems);
}
