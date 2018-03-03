package home.work.pcconfig.di;

import com.github.mrvilkaman.di.PerActivity;
import com.github.mrvilkaman.di.modules.activity.CommonActivityModule;
import com.github.mrvilkaman.di.modules.activity.DrawerEmptyModule;
import com.github.mrvilkaman.di.modules.activity.ThrowableModule;
import com.github.mrvilkaman.di.modules.activity.ToolbarEmptyModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import home.work.pcconfig.di.screens.CommonScreenModule;
import home.work.pcconfig.di.screens.HubModule;
import home.work.pcconfig.ui.hab.HabActivity;

@Module(includes = AndroidSupportInjectionModule.class)
interface AppGlobalModule {

    @PerActivity
    @ContributesAndroidInjector(modules = {
            CommonActivityModule.class,
            ThrowableModule.class,
            ToolbarEmptyModule.class,
            DrawerEmptyModule.class,
            HubModule.class,
            CommonScreenModule.class
    }
    )
    HabActivity habActivity();
}