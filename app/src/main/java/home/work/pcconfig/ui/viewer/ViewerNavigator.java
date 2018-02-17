package home.work.pcconfig.ui.viewer;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.github.mrvilkaman.presentationlayer.resolution.BaseNavigator;

public class ViewerNavigator extends BaseNavigator {

    public ViewerNavigator(@Nullable AppCompatActivity activity,
                           @Nullable int containerId) {
        super(activity, containerId, null, null);
    }

    @Nullable
    @Override
    protected Fragment getDrawer() {
        return null;
    }

    @Nullable
    @Override
    protected String getMainScreenKey() {
        return null;
    }

    @Override
    protected Intent createActivityIntent(String screenKey, Object data) {
        return null;
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        return null;
    }
}
