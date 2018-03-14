package home.work.pcconfig.ui.hab.screen.stream;


import android.telephony.SmsManager;

import com.github.mrvilkaman.domainlayer.providers.PermissionManager;
import com.github.mrvilkaman.presentationlayer.fragments.core.BasePresenter;
import com.github.mrvilkaman.presentationlayer.subscriber.ViewSubscriber;

import java.util.List;

import javax.inject.Inject;

import home.work.pcconfig.R;
import home.work.pcconfig.business.SmsItem;
import home.work.pcconfig.business.StreamMessageInteractor;

public class StreamMessagePresenter extends BasePresenter<StreamMessageView> {

    private final PermissionManager permissionManager;
    private final StreamMessageInteractor interactor;

    @Inject
    public StreamMessagePresenter(PermissionManager permissionManager, StreamMessageInteractor interactor) {
        this.permissionManager = permissionManager;
        this.interactor = interactor;
    }

    @Override
    public void onViewAttached() {
        super.onViewAttached();

        subscribeUI(interactor.observableSms(), new ViewSubscriber<StreamMessageView, List<SmsItem>>(){
            @Override
            public void onNext(List<SmsItem> smsItem) {
                super.onNext(smsItem);
                view().bindSms(smsItem);
            }
        });
    }

    public void showSelectedNumber(int type, String number) {
        view().bindPhone(number);
    }

    public void sendMessage(String number, String text) {
        if (number.isEmpty()) {
            uiResolver().showToast(R.string.empty_phone_error);
            return;
        }

        subscribeUI(permissionManager.request("android.permission.SEND_SMS"),new ViewSubscriber<StreamMessageView,PermissionManager.AnswerPermission>(){
            @Override
            public void onNext(PermissionManager.AnswerPermission permission) {
                super.onNext(permission);
                switch (permission) {
                    case ALLOWED:
                        try {
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(number, null, text, null, null);
                            uiResolver().showToast(R.string.send_sms_success);
                            interactor.saveSms(number,text);
                        } catch (Exception ex) {
                            uiResolver().showToast(R.string.cleanbase_simple_text, ex.getMessage());
                            ex.printStackTrace();
                        }
                        break;
                    case DENIED:
                    case NOT_ASK:
                            uiResolver().showToast(R.string.send_sms_success_permition_denied);
                        break;
                }

            }
        });



    }
}
