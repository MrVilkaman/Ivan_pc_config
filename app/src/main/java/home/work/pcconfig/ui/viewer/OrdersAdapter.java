package home.work.pcconfig.ui.viewer;


import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.github.mrvilkaman.presentationlayer.fragments.core.BaseVH;
import com.github.mrvilkaman.presentationlayer.fragments.core.MySimpleBaseAdapter;
import com.github.mrvilkaman.presentationlayer.utils.ui.UIUtils;

import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
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

        @BindView(R.id.order_number) TextView number;
        @BindView(R.id.order_main_info) TextView mainInfo;
        @BindView(R.id.order_gaming_info) TextView gamingInfo;

        OrdersVH(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void bind(@NonNull OrdersItem item, int position, Set<String> payloads) {
            final Resources resources = itemView.getResources();
            final StringBuilder builder = new StringBuilder();
            number.setText(resources.getString(R.string.number_format, item.getId()));

            String storageType = resources.getString(item.isSsd() ? R.string.storage_ssd : R.string.storage_hhd);

            builder.append(resources.getString(R.string.cpu_intel_core_i_format, item.getCpuInterType()))
                    .append("\n")
                    .append(resources.getString(R.string.motherboard_format, item.getMotherBoard()))
                    .append("\n")
                    .append(resources.getString(R.string.gpu_format, item.getGpu()))
                    .append("\n")
                    .append(resources.getString(R.string.ram_format, item.getRamSize()))
                    .append("\n")
                    .append(resources.getString(R.string.storage_format, storageType, item.getStogareSize()));

            UIUtils.changeVisibility(gamingInfo, item.isGaming());

            mainInfo.setText(builder.toString());
        }
    }
}
