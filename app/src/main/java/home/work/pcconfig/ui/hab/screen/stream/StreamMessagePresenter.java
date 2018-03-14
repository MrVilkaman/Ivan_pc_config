package home.work.pcconfig.ui.hab.screen.stream;


import com.github.mrvilkaman.presentationlayer.fragments.core.BasePresenter;

import javax.inject.Inject;

public class StreamMessagePresenter extends BasePresenter<StreamMessageView> {

    @Inject
    public StreamMessagePresenter() {
    }

    public void showSelectedNumber(int type, String number) {
        view().bindPhone(number);
    }
}
