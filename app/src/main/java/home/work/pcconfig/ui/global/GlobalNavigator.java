package home.work.pcconfig.ui.global;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.github.mrvilkaman.presentationlayer.resolution.BaseNavigator;

import home.work.pcconfig.ui.detail.EditOrderActivity;
import ru.terrakok.cicerone.commands.Command;

import static home.work.pcconfig.ui.global.ScreenKeys.KEY_NEW_ORDER;

public class GlobalNavigator extends BaseNavigator {

    public GlobalNavigator(@Nullable AppCompatActivity activity,
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
    protected Intent createActivityIntent(Context context, String screenKey, Object data) {
        switch (screenKey) {
            case KEY_NEW_ORDER:
                return new Intent(context, EditOrderActivity.class);
        }
        return null;
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        return null;
    }

    @Override
    public void applyCommand(@Nullable Command command) {
        super.applyCommand(command);
    }
}
