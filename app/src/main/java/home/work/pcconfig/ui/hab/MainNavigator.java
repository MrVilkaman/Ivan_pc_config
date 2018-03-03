package home.work.pcconfig.ui.hab;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.github.mrvilkaman.presentationlayer.resolution.BaseNavigator;

import home.work.pcconfig.ui.global.ScreenKeys;
import home.work.pcconfig.ui.hab.screen.stream.StreamMessageScreen;
import ru.terrakok.cicerone.commands.Command;

public class MainNavigator extends BaseNavigator {

    public MainNavigator(@Nullable AppCompatActivity activity, @Nullable int containerId) {
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
        return ScreenKeys.KEY_SMS_LIST;
    }

    @Override
    protected Intent createActivityIntent(Context context, String screenKey, Object data) {
        return null;
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        switch (screenKey){
            case ScreenKeys.KEY_SMS_LIST:
                return StreamMessageScreen.newInstance();
        }
        return null;
    }

    @Override
    public void applyCommand(@Nullable Command command) {
        super.applyCommand(command);
    }
}
