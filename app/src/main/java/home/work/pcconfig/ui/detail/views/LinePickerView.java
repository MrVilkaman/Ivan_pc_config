package home.work.pcconfig.ui.detail.views;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import home.work.pcconfig.R;

public class LinePickerView extends FrameLayout {

    private TextView titleView;
    private AppCompatAutoCompleteTextView valueView;

    public LinePickerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LinePickerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.one_line_pickertext_view, this, true);
        titleView = findViewById(R.id.one_line_title);
        valueView = findViewById(R.id.one_line_value);

        final String xmlns = "http://schemas.android.com/apk/res/android";
        int hint = attrs.getAttributeResourceValue(xmlns, "hint", -1);
        if (hint != -1) {
            titleView.setText(hint);
        }
        valueView.setFocusable(false);
        valueView.setFocusableInTouchMode(false);
        valueView.setCursorVisible(false);
        valueView.setOnClickListener(v -> ((AppCompatAutoCompleteTextView) v).showDropDown());
    }

    public void bind(int resText) {
        String[] stringArray = getResources().getStringArray(resText);
        valueView.setAdapter(new ArrayAdapter<>(getContext(), R.layout.order_spinner_item, stringArray));
    }

}
