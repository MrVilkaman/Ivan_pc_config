package home.work.pcconfig.ui.viewer;

import android.support.annotation.Nullable;

import home.work.pcconfig.business.models.OrderItem;

public interface GetSelectionHelper {
    @Nullable OrderItem getSelected();

    void resetSelection();
}