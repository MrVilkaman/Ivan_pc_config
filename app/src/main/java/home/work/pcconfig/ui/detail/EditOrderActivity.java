package home.work.pcconfig.ui.detail;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.CheckBox;

import com.github.mrvilkaman.presentationlayer.activities.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import home.work.pcconfig.R;
import home.work.pcconfig.business.models.OrderItem;
import home.work.pcconfig.ui.detail.views.LineEditView;
import home.work.pcconfig.ui.detail.views.LinePickerView;

public class EditOrderActivity extends BaseActivity<EditPresenter> implements EditView {

    public static final String KEY_ID = "KEY_ID";

    @BindView(R.id.order_cpu) LinePickerView cpuPickerView;
    @BindView(R.id.order_ram) LinePickerView ramPickerView;
    @BindView(R.id.order_matherboard) LineEditView matherboardView;
    @BindView(R.id.order_gpu) LineEditView gpuView;
    @BindView(R.id.order_storage_size) LineEditView storageSizeView;
    @BindView(R.id.order_storage) CheckBox storageView;
    @BindView(R.id.order_gaming) CheckBox gamingView;

    @BindView(R.id.order_action_btn) Button updateButtonView;

    public static Intent newIntent(Context context) {
        return new Intent(context, EditOrderActivity.class);
    }

    public static Intent updateIntent(Context context,int id) {
        final Intent intent = new Intent(context, EditOrderActivity.class);
        intent.putExtra(KEY_ID,id);
        return intent;
    }

    @Override
    protected int getActivityLayoutResourceID() {
        return R.layout.activity_edit_screen;
    }

    @Override
    protected void afterOnCreate() {
        ButterKnife.bind(this);

        cpuPickerView.bind(R.array.intel_cpus);
        ramPickerView.bind(R.array.rams);
    }

    @Override
    @Inject
    public void setPresenter(@Nullable EditPresenter presenter) {
        super.setPresenter(presenter);
    }

    @OnClick(R.id.order_action_btn)
    void onClick() {
        getPresenter().doAction(cpuPickerView.getValue(),
                ramPickerView.getValue(),
                matherboardView.getValue(),
                gpuView.getValue(),
                storageSizeView.getValue(),
                storageView.isChecked(),
                gamingView.isChecked());
    }

    @Override
    public void showUpdateState() {
        updateButtonView.setText(R.string.order_update_btn);
    }

    @Override
    public void fillFields(OrderItem orderItem) {
        storageView.setChecked(orderItem.isSsd());
        gamingView.setChecked(orderItem.isGaming());

        cpuPickerView.setValue(String.valueOf(orderItem.getCpuInterType()));
        ramPickerView.setValue(String.valueOf(orderItem.getRamSize()));
        matherboardView.setValue(orderItem.getMotherBoard());
        gpuView.setValue(orderItem.getGpu());
        storageSizeView.setValue(String.valueOf(orderItem.getStogareSize()));
    }
}
