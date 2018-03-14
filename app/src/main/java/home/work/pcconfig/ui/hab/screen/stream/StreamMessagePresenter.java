package home.work.pcconfig.ui.hab.screen.stream;


import android.telephony.SmsManager;

import com.github.mrvilkaman.domainlayer.providers.PermissionManager;
import com.github.mrvilkaman.presentationlayer.fragments.core.BasePresenter;
import com.github.mrvilkaman.presentationlayer.subscriber.ViewSubscriber;

import javax.inject.Inject;

import home.work.pcconfig.R;

public class StreamMessagePresenter extends BasePresenter<StreamMessageView> {

    private final PermissionManager permissionManager;

    @Inject
    public StreamMessagePresenter(PermissionManager permissionManager) {
        this.permissionManager = permissionManager;
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
