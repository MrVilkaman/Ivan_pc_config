package home.work.pcconfig.di.screens;


import android.support.v7.app.AppCompatActivity;

import com.github.mrvilkaman.di.PerActivity;
import com.github.mrvilkaman.di.PerScreen;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import home.work.pcconfig.ui.hab.HabActivity;
import home.work.pcconfig.ui.hab.screen.stream.StreamMessageScreen;

@Module
public interface HubModule {
    @Binds
    @PerActivity
    AppCompatActivity provideViewerActivity(HabActivity habActivity);

    @PerScreen
    @ContributesAndroidInjector
    StreamMessageScreen provideStreamMessageScreen();
}
