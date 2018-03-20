package home.work.pcconfig.ui.hab.screen.stream;


import android.support.annotation.Nullable;

import com.github.mrvilkaman.presentationlayer.fragments.core.BaseView;

import java.util.List;

import home.work.pcconfig.business.SmsItem;

public interface StreamMessageView extends BaseView {

    void bindPhone(String number);

    void bindSms(List<SmsItem> smsItem);

    void showTemplateDialog(List<String> strings);

    void attachImage(@Nullable String path);

    void openCamera();

    void sendMms(String number, String text, String path);
}