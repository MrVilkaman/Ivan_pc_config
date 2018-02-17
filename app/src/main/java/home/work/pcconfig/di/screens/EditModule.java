package home.work.pcconfig.di.screens;


import android.support.v7.app.AppCompatActivity;

import com.github.mrvilkaman.di.PerActivity;

import dagger.Module;
import dagger.Provides;
import home.work.pcconfig.ui.detail.EditOrderActivity;

@Module
public class EditModule {

    @Provides
    @PerActivity
    AppCompatActivity provideViewerActivity(EditOrderActivity activity){
        return activity;
    }

}
