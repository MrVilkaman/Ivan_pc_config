package home.work.pcconfig.ui.hab.screen.stream;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.github.mrvilkaman.presentationlayer.fragments.core.BaseFragment;
import com.github.mrvilkaman.presentationlayer.utils.ui.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import home.work.pcconfig.R;

public class StreamMessageScreen extends BaseFragment<StreamMessagePresenter> implements StreamMessageView {

    @BindView(R.id.phonenumber) EditText phoneEdit;
    @BindView(R.id.choose_contact_add) View addContact;

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
        ButterKnife.bind(this,view);
        super.onViewCreated(view, savedInstanceState);


        phoneEdit.addTextChangedListener(new HideButtonTextWhater());
    }

    @Override
    public void bindPhone(String number) {
        phoneEdit.setText(number);
        UIUtils.changeVisibility(addContact,false);
    }

    @OnClick(R.id.choose_contact)
    void onClickChooseContact(){

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        // BoD con't: CONTENT_TYPE instead of CONTENT_ITEM_TYPE
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        startActivityForResult(intent, 1);
    }

    @OnClick(R.id.choose_contact_add)
    void onClickAddContact(){
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

//        intent.putExtra(ContactsContract.Intents.Insert.NAME, UIUtils.asString(phoneEdit));
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, UIUtils.asString(phoneEdit));
//        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, person.email);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (data != null) {
            Uri uri = data.getData();

            if (uri != null) {
                Cursor c = null;
                try {
                    c = getActivity().getContentResolver().query(uri, new String[]{
                                    ContactsContract.CommonDataKinds.Phone.NUMBER,
                                    ContactsContract.CommonDataKinds.Phone.TYPE },
                            null, null, null);

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

    private class HideButtonTextWhater implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            UIUtils.changeVisibility(addContact,s.length() != 0);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
