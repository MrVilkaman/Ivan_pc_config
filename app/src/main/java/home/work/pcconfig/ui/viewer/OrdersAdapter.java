package home.work.pcconfig.ui.viewer;


import android.support.annotation.NonNull;
import android.view.View;

import com.github.mrvilkaman.presentationlayer.fragments.core.BaseVH;
import com.github.mrvilkaman.presentationlayer.fragments.core.MySimpleBaseAdapter;

import java.util.Set;

import javax.inject.Inject;

import butterknife.ButterKnife;
import home.work.pcconfig.R;
import home.work.pcconfig.business.models.OrdersItem;

class OrdersAdapter extends MySimpleBaseAdapter<OrdersItem, OrdersAdapter.OrdersVH> {

    @Inject
    public OrdersAdapter() {
    }

    @Override
    protected OrdersVH getHolder(@NonNull View view) {
        return new OrdersVH(view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_order_list_layout;
    }

    public static class OrdersVH extends BaseVH<OrdersItem> {

        public OrdersVH(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void bind(@NonNull OrdersItem item, int position, Set<String> payloads) {

        }
    }
}
