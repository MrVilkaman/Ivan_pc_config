package home.work.pcconfig.ui.hab;


import android.support.v4.app.Fragment;

import com.github.mrvilkaman.presentationlayer.activities.BaseActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class HabActivity extends BaseActivity implements HasSupportFragmentInjector {

    @Inject DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    protected void afterOnCreate() {
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}
