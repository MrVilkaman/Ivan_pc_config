package home.work.pcconfig.ui.hab.screen.stream;


import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.github.mrvilkaman.presentationlayer.fragments.core.BaseVH;
import com.github.mrvilkaman.presentationlayer.fragments.core.MySimpleBaseAdapter;

import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import home.work.pcconfig.R;
import home.work.pcconfig.business.SmsItem;

public class SmsAdapter extends MySimpleBaseAdapter<SmsItem,SmsAdapter.SmsVH> {

    @Inject
    public SmsAdapter() {
    }

    @Override
    protected SmsVH getHolder(@NonNull View view) {
        return new SmsVH(view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_sms_bubble;
    }

    public static class SmsVH extends BaseVH<SmsItem> {

        @BindView(R.id.text_phone) TextView phone;
        @BindView(R.id.text_msg) TextView msg;
        public SmsVH(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }

        @Override
        public void bind(@NonNull SmsItem item, int position, Set<String> payloads) {
            phone.setText(item.getPhone());
            msg.setText(item.getText());
        }
    }
}
