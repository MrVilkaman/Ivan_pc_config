package home.work.pcconfig.di.screens;


import android.support.v7.app.AppCompatActivity;

import com.github.mrvilkaman.di.PerActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import home.work.pcconfig.business.orders.OrderInteractor;
import home.work.pcconfig.business.orders.OrderInteractorImpl;
import home.work.pcconfig.ui.detail.EditOrderActivity;

@Module
public class EditOrderModule {

    @Provides
    @PerActivity
    AppCompatActivity provideViewerActivity(EditOrderActivity activity){
        return activity;
    }

    @Module
    public interface Binder{

        @Binds
        OrderInteractor interacor(OrderInteractorImpl interactor);
    }
}
