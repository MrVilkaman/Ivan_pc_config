package home.work.pcconfig.di;


import com.github.mrvilkaman.di.modules.AppModule;
import com.github.mrvilkaman.di.modules.CoreProvidersModule;
import com.github.mrvilkaman.di.modules.DevModule;
import com.github.mrvilkaman.di.modules.EventBusModule;

import javax.inject.Singleton;

import dagger.Component;
import home.work.pcconfig.ui.global.App;

@Component(modules = {
        AppGlobalModule.class,
        AppModule.class,
        DevModule.class,
        EventBusModule.class,
        CoreProvidersModule.class,
        ProvidersModule.class,
        DbModule.class,
        NavigationModule.class
})

@Singleton
public interface AppComponent {

    void inject(App app);

    @Component.Builder
    interface Builder {
//        @BindsInstance
        Builder setAppModule(AppModule appModule);
        AppComponent build();
    }
}
