package home.work.pcconfig.ui.detail.views;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import home.work.pcconfig.R;

public class LineEditView extends FrameLayout {

    private TextView titleView;
    private EditText valueView;

    public LineEditView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LineEditView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater mInflater =
                (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.one_line_text_view, this, true);
        titleView = findViewById(R.id.one_line_title);
        valueView = findViewById(R.id.one_line_value);

        final String xmlns="http://schemas.android.com/apk/res/android";
        int hint = attrs.getAttributeResourceValue(xmlns, "hint",-1);
        if (hint != -1) {
            titleView.setText(hint);
        }
    }
}
