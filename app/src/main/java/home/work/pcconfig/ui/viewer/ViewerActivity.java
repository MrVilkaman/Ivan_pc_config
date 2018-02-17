package home.work.pcconfig.ui.viewer;


import android.support.v7.widget.RecyclerView;

import com.github.mrvilkaman.presentationlayer.activities.BaseActivity;
import com.github.mrvilkaman.presentationlayer.fragments.core.MySimpleAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import home.work.pcconfig.R;

public class ViewerActivity extends BaseActivity<ViewerPresenter> implements ViewerView {

    @BindView(R.id.recyclerview) RecyclerView recyclerView;

    @Override
    protected int getActivityLayoutResourceID() {
        return R.layout.activity_viewer_screen;
    }

    @Override
    protected void afterOnCreate() {
        ButterKnife.bind(this);

        final MySimpleAdapter<String> adapter = new MySimpleAdapter<>();
        final List<String> ts = new ArrayList<>();
        ts.add("Row 1");
        ts.add("Row 2");
        ts.add("Row 3");
        ts.add("Row 4");
        adapter.setItems(ts);
        recyclerView.setAdapter(adapter);
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
