package home.work.pcconfig.ui.detail;


import com.github.mrvilkaman.presentationlayer.activities.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import home.work.pcconfig.R;
import home.work.pcconfig.ui.detail.views.LinePickerView;

public class EditOrderActivity extends BaseActivity<EditPresenter> implements EditView {

    @BindView(R.id.order_cpu) LinePickerView cpuPickerView;
    @BindView(R.id.order_ram) LinePickerView ramPickerView;

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

    @OnClick(R.id.order_action_btn)
    void onClick(){
        presenter.doAction();
    }
}
