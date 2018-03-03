package home.work.pcconfig.di.screens;


import android.support.v7.app.AppCompatActivity;

import com.github.mrvilkaman.di.INeedActivityViewNotify;
import com.github.mrvilkaman.di.PerActivity;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntKey;
import dagger.multibindings.IntoMap;
import home.work.pcconfig.R;
import home.work.pcconfig.ui.hab.MainNavigator;
import ru.terrakok.cicerone.Navigator;

@Module
public class CommonScreenModule {
    @Provides
    @IntoMap
    @IntKey(Integer.MAX_VALUE)
    @PerActivity
    INeedActivityViewNotify iNeedActivityViewNotify(){
        return () -> {};
    }

    @Provides
    @PerActivity
    Navigator provideNavigator(AppCompatActivity activity){
        return new MainNavigator(activity, R.id.content);
    }

}
