package home.work.pcconfig.ui.detail;


import com.github.mrvilkaman.presentationlayer.fragments.core.BaseView;

import home.work.pcconfig.business.models.OrderItem;

public interface EditView extends BaseView {
    void fillFields(OrderItem orderItem);

    void showUpdateState();
}