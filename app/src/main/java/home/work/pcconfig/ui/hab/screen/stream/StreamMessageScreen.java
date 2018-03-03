package home.work.pcconfig.ui.hab.screen.stream;


import android.os.Bundle;

import com.github.mrvilkaman.presentationlayer.fragments.core.BaseFragment;

import home.work.pcconfig.R;

public class StreamMessageScreen extends BaseFragment<StreamMessagePresenter> implements StreamMessageView {

    public static StreamMessageScreen newInstance() {
        Bundle args = new Bundle();
        StreamMessageScreen fragment = new StreamMessageScreen();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.screen_stream_list;
    }
}
