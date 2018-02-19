package home.work.pcconfig.ui.viewer;


import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.github.mrvilkaman.di.PerActivity;
import com.github.mrvilkaman.presentationlayer.fragments.core.BaseVH;
import com.github.mrvilkaman.presentationlayer.fragments.core.ItemListener;
import com.github.mrvilkaman.presentationlayer.fragments.core.MySimpleBaseAdapter;
import com.github.mrvilkaman.presentationlayer.utils.ui.UIUtils;

import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import home.work.pcconfig.R;
import home.work.pcconfig.business.models.OrderItem;

@PerActivity
public class OrdersAdapter extends MySimpleBaseAdapter<OrderItem, OrdersAdapter.OrdersVH>
        implements GetSelectionHelper {

    private final static int NO_SELECTION = -1;
    private int currentSelection = NO_SELECTION;
    private SelectionHelper selectionHelper;

    @Inject
    public OrdersAdapter() {
        selectionHelper = new SelectionHelper() {
            @Override
            public boolean isSelected(int pos) {
                return pos == currentSelection;
            }

            @Override
            public void setSelected(int pos) {
                if (isSelected(pos)) {
                    currentSelection = NO_SELECTION;
                } else if (currentSelection != NO_SELECTION) {
                    int oldSelection = currentSelection;
                    currentSelection = pos;
                    notifyItemChanged(oldSelection);
                } else {
                    currentSelection = pos;
                }
                notifyItemChanged(pos);
            }
        };
    }

    @Override
    protected OrdersVH getHolder(@NonNull View view) {

        return new OrdersVH(view, selectionHelper);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_order_list_layout;
    }

    @Override
    @Nullable
    public OrderItem getSelected() {
        if (currentSelection != NO_SELECTION) {
            return getItem(currentSelection);
        } else {
            return null;
        }
    }

    @Override
    public void resetSelection() {
        int oldSelection = currentSelection;
        currentSelection = NO_SELECTION;
        notifyItemChanged(oldSelection);
    }

    private interface SelectionHelper {
        void setSelected(int pos);

        boolean isSelected(int pos);
    }

    public static class OrdersVH extends BaseVH<OrderItem> {

        private final SelectionHelper selectionHelper;
        @BindView(R.id.order_number) TextView number;
        @BindView(R.id.order_main_info) TextView mainInfo;
        @BindView(R.id.order_gaming_info) TextView gamingInfo;

        OrdersVH(View view, SelectionHelper selectionHelper) {
            super(view);
            this.selectionHelper = selectionHelper;
            ButterKnife.bind(this, view);
        }

        @Override
        public void setListeners(@NonNull View view,
                                 ItemListener<OrderItem> onClick,
                                 ItemListener<OrderItem> onLongClick) {
            view.setOnClickListener(view1 -> {
                selectionHelper.setSelected(getAdapterPosition());
            });
        }

        @Override
        public void bind(@NonNull OrderItem item, int position, Set<String> payloads) {
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

            if (selectionHelper.isSelected(position)) {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.md_btn_selected));
            } else {
                itemView.setBackground(null);
            }
        }
    }
}
