package home.work.pcconfig.ui.viewer;


import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.mrvilkaman.presentationlayer.activities.BaseActivity;
import com.github.mrvilkaman.presentationlayer.utils.ui.UIUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import home.work.pcconfig.R;
import home.work.pcconfig.business.models.OrderItem;

public class ViewerActivity extends BaseActivity<ViewerPresenter> implements ViewerView {

    @BindView(R.id.recyclerview) RecyclerView recyclerView;
    @BindView(R.id.empty_content) View emptyContent;

    @Inject OrdersAdapter adapter;

    @Override
    protected int getActivityLayoutResourceID() {
        return R.layout.activity_viewer_screen;
    }

    @Override
    protected void afterOnCreate() {
        ButterKnife.bind(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    @Inject
    public void setPresenter(@Nullable ViewerPresenter presenter) {
        super.setPresenter(presenter);
    }

    @Override
    public void bindOrders(List<OrderItem> ordersItems) {
        adapter.setItems(ordersItems);
        UIUtils.changeVisibility(emptyContent, ordersItems.isEmpty());
    }

    @OnClick(R.id.viewer_delete_btn)
    void onclickDelete() {
        getPresenter().deleteRow();
    }

    @OnClick(R.id.viewer_add_btn)
    void onclickAdd() {
        getPresenter().addRow();
    }

    @OnClick(R.id.viewer_edit_btn)
    void onclickEdit() {
        getPresenter().editRow();
    }
}
