package home.work.pcconfig.di.screens;


import android.support.v7.app.AppCompatActivity;

import com.github.mrvilkaman.di.PerActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import home.work.pcconfig.business.viewer.ViewerInteractor;
import home.work.pcconfig.business.viewer.ViewerInteractorImpl;
import home.work.pcconfig.ui.viewer.GetSelectionHelper;
import home.work.pcconfig.ui.viewer.OrdersAdapter;
import home.work.pcconfig.ui.viewer.ViewerActivity;

@Module
public class ViewerModule {


    @Provides
    @PerActivity
    AppCompatActivity provideViewerActivity(ViewerActivity activity){
        return activity;
    }

    @Module
    public interface Binder{

        @Binds
        ViewerInteractor interacor(ViewerInteractorImpl interactor);

        @Binds
        GetSelectionHelper getSelectionHelper(OrdersAdapter adapter);
    }
}
