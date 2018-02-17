package home.work.pcconfig.di.screens;


import com.github.mrvilkaman.di.INeedActivityViewNotify;
import com.github.mrvilkaman.di.PerActivity;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntKey;
import dagger.multibindings.IntoMap;
import home.work.pcconfig.R;
import home.work.pcconfig.ui.viewer.ViewerActivity;
import home.work.pcconfig.ui.viewer.ViewerNavigator;
import ru.terrakok.cicerone.Navigator;

@Module
public class ViewerModule {

    @Provides
    @IntoMap
    @IntKey(Integer.MAX_VALUE)
    @PerActivity
    INeedActivityViewNotify iNeedActivityViewNotify(){
        return () -> {};
    }

    @Provides
    @PerActivity
    Navigator provideNavigator(ViewerActivity activity){
        return new ViewerNavigator(activity, R.id.content);
    }
}