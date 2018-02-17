package home.work.pcconfig.di;


import com.github.mrvilkaman.di.modules.AppModule;
import com.github.mrvilkaman.di.modules.CoreProvidersModule;
import com.github.mrvilkaman.di.modules.DevModule;
import com.github.mrvilkaman.di.modules.EventBusModule;

import javax.inject.Singleton;

import dagger.Component;
import home.work.pcconfig.ui.global.App;

@Component(modules = {
        AppModule.class,
        DevModule.class,
        EventBusModule.class,
        CoreProvidersModule.class,
        AppGlobalModule.class,
        NavigationModule.class
})

@Singleton
public interface AppComponent {

    void inject(App app);

    @Component.Builder interface Builder {
        Builder setAppModule(AppModule appModule);
        public AppComponent build();
    }
}