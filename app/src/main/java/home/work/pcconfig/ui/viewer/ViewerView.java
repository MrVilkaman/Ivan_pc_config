package home.work.pcconfig.ui.viewer;


import com.github.mrvilkaman.presentationlayer.fragments.core.BaseView;

import java.util.List;

import home.work.pcconfig.business.models.OrderItem;

public interface ViewerView extends BaseView {
    void bindOrders(List<OrderItem> ordersItems);
}
