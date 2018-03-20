package home.work.pcconfig.ui.hab.screen.stream;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mrvilkaman.presentationlayer.fragments.core.BaseFragment;
import com.github.mrvilkaman.presentationlayer.photoulits.PhotoHelper;
import com.github.mrvilkaman.presentationlayer.photoulits.PhotoHelperImpl;
import com.github.mrvilkaman.presentationlayer.resolution.ImageLoader;
import com.github.mrvilkaman.presentationlayer.utils.StorageUtils;
import com.github.mrvilkaman.presentationlayer.utils.ui.UIUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import home.work.pcconfig.R;
import home.work.pcconfig.business.SmsItem;

import static com.github.mrvilkaman.presentationlayer.photoulits.PhotoHelperImpl.SELECT_PICTURE_REQUEST_CODE;

public class StreamMessageScreen extends BaseFragment<StreamMessagePresenter> implements StreamMessageView {

    public static final int REQUEST_CODE_CONTACTS = 1;
    private static final String IMAGE_TEMP = "images";
    public static final String QWER_JPG = "/qwer.jpg";
    @BindView(R.id.empty_content) View emptyView;
    @BindView(R.id.phonenumber) EditText phoneEdit;
    @BindView(R.id.choose_contact_add) View addContact;
    @BindView(R.id.chat_input_text) EditText editText;
    @BindView(R.id.chat_input_send) View iconSend;
    @BindView(R.id.chat_input_add) View tempAddSend;
    @BindView(R.id.image_attach) ImageView attach;
    @BindView(R.id.recyclerview) RecyclerView recyclerview;
    @Inject SmsAdapter smsAdapter;
    @Inject PhotoHelper photoHelper;
    @Inject ImageLoader imageLoader;

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        super.onViewCreated(view, savedInstanceState);


        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                onClickSend();
                return true;
            }
            return false;
        });
        editText.addTextChangedListener(new MyTextWatcher());

        phoneEdit.addTextChangedListener(new HideButtonTextWhater());

        recyclerview.setAdapter(smsAdapter);
    }

    @OnClick(R.id.chat_input_send)
    void onClickSend() {
        getPresenter().sendMessage(UIUtils.asString(phoneEdit), UIUtils.asString(editText));
        editText.setText("");
    }


    @OnClick(R.id.chat_input_tamplate)
    void onClickTemplate() {
        getPresenter().onClickTemplate();
    }

    @OnClick(R.id.chat_input_add)
    void onClickAddedTemplate() {
        getPresenter().addToTemplate(UIUtils.asString(editText));
    }

    @Override
    public void bindPhone(String number) {
        phoneEdit.setText(number);
        UIUtils.changeVisibility(addContact, false);
    }

    @Override
    public void bindSms(List<SmsItem> smsItem) {
        smsAdapter.setItems(smsItem);
        UIUtils.changeVisibility(emptyView, smsItem.isEmpty());
    }

    @Override
    public void showTemplateDialog(List<String> strings) {
        new MaterialDialog.Builder(getContext()).items(strings)
                .itemsCallback((dialog, itemView, position, text) -> editText.setText(text))
                .show();
    }

    @Override
    public void attachImage(@Nullable String path) {
        if (path != null) {
            imageLoader.loadFile(path).into(attach);
        }
        UIUtils.changeVisibility(attach, path != null);
        UIUtils.changeVisibility(iconSend, path != null);

    }

    @OnClick(R.id.choose_contact)
    void onClickChooseContact() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        // BoD con't: CONTENT_TYPE instead of CONTENT_ITEM_TYPE
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        startActivityForResult(intent, REQUEST_CODE_CONTACTS);
    }

    @OnClick(R.id.choose_contact_add)
    void onClickAddContact() {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

//        intent.putExtra(ContactsContract.Intents.Insert.NAME, UIUtils.asString(phoneEdit));
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, UIUtils.asString(phoneEdit));
//        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, person.email);
        startActivity(intent);
    }

    @OnClick(R.id.attach_btn)
    void onClickAttachImage() {
        File dir = new File(getPathToTempFile());
        if (!dir.exists()) {
            dir.mkdirs();
        }

        getPresenter().openCamera();
    }

    @Override
    public void openCamera() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_PICTURE_REQUEST_CODE);
    }

    @Override
    public void sendMms(String number, String text, String path) {
        Intent mmsIntent = new Intent(android.content.Intent.ACTION_SEND,Uri.parse("mms://"));
        mmsIntent.putExtra("sms_body", text);
        mmsIntent.putExtra("address", number);
        mmsIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(path)));
        mmsIntent.setType("image/jpeg");
        startActivity(Intent.createChooser(mmsIntent, "Send"));
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (reqCode == SELECT_PICTURE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                final Uri selectedImage = data.getData();

                final String pathToTempFile = getPathToTempFile()+IMAGE_TEMP;
                if (selectedImage.getScheme().equals("file")) {
                    try {
                        PhotoHelperImpl.copy(new File(selectedImage.getEncodedPath()), new File(pathToTempFile));
                        getPresenter().handleImage(pathToTempFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                        getUiResolver().showToast(R.string.cleanbase_simple_text,e.getLocalizedMessage());
                    }
                    return;
                }

                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContext().getContentResolver()
                        .query(selectedImage, filePathColumn, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String filePath = cursor.getString(columnIndex);

                        try {
                            PhotoHelperImpl.copy(new File(filePath), new File(pathToTempFile));
                            getPresenter().handleImage(pathToTempFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                            getUiResolver().showToast(R.string.cleanbase_simple_text,e.getLocalizedMessage());
                        }
                    }
                    cursor.close();
                }
            }

        } else if (REQUEST_CODE_CONTACTS == reqCode && data != null) {
            Uri uri = data.getData();

            if (uri != null) {
                Cursor c = null;
                try {
                    c = getActivity().getContentResolver()
                            .query(uri,
                                    new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.TYPE},
                                    null,
                                    null,
                                    null);

                    if (c != null && c.moveToFirst()) {
                        String number = c.getString(0);
                        int type = c.getInt(1);
                        getPresenter().showSelectedNumber(type, number);
                    }
                } finally {
                    if (c != null) {
                        c.close();
                    }
                }
            }
        }

    }


    private String getPathToTempFile() {
        return new StorageUtils().getStoragePath(getContext()) + File.separator + IMAGE_TEMP + File.separator ;
    }


    private class HideButtonTextWhater implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            UIUtils.changeVisibility(addContact, s.length() != 0);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


    private class MyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            final boolean show = s.length() != 0;
            UIUtils.changeVisibility(iconSend, show);
            UIUtils.changeVisibility(tempAddSend, show);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
