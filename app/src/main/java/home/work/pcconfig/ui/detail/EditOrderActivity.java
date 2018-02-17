package home.work.pcconfig.ui.detail;


import com.github.mrvilkaman.presentationlayer.activities.BaseActivity;

import butterknife.ButterKnife;
import home.work.pcconfig.R;

public class EditOrderActivity extends BaseActivity<EditPresenter> implements EditView {


    @Override
    protected int getActivityLayoutResourceID() {
        return R.layout.activity_edit_screen;
    }

    @Override
    protected void afterOnCreate() {
        ButterKnife.bind(this);
    }
}
