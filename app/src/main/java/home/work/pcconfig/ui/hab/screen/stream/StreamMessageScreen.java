package home.work.pcconfig.ui.hab.screen.stream;


import com.github.mrvilkaman.presentationlayer.fragments.core.BaseFragment;

import home.work.pcconfig.R;

public class StreamMessageScreen extends BaseFragment<StreamMessagePresenter> implements StreamMessageView {
    @Override
    protected int getLayoutId() {
        return R.layout.screen_stream_list;
    }
}
