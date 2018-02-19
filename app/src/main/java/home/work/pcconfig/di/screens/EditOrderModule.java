package home.work.pcconfig.di.screens;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.github.mrvilkaman.di.PerActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import home.work.pcconfig.business.orders.OrderInteractor;
import home.work.pcconfig.business.orders.OrderInteractorImpl;
import home.work.pcconfig.di.SomeId;
import home.work.pcconfig.ui.detail.EditOrderActivity;

@Module
public class EditOrderModule {

    @Provides
    @PerActivity
    AppCompatActivity provideViewerActivity(EditOrderActivity activity){
        return activity;
    }


    @Provides
    @PerActivity
    @SomeId
    Integer provideId(EditOrderActivity activity){
        final Intent intent = activity.getIntent();
        if (intent != null) {
            return intent.getIntExtra(EditOrderActivity.KEY_ID, -1);
        }else {
            return -1;
        }
    }

    @Module
    public interface Binder{

        @Binds
        OrderInteractor interacor(OrderInteractorImpl interactor);
    }
}
