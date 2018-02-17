package home.work.pcconfig.ui.global;


import android.app.Activity;
import android.app.Application;

import com.github.mrvilkaman.di.modules.AppModule;
import com.github.mrvilkaman.presentationlayer.app.CleanBaseSettings;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import home.work.pcconfig.BuildConfig;
import home.work.pcconfig.di.DaggerAppComponent;

public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        final CleanBaseSettings.Builder builder = CleanBaseSettings.changeSettings().setAll(BuildConfig.DEBUG);
        CleanBaseSettings.save(builder);

        DaggerAppComponent
                .builder()
                .setAppModule(new AppModule(this))
                .build()
                .inject(this);

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
