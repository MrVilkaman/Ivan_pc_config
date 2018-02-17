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
import home.work.pcconfig.di.screens.EditModule;
import home.work.pcconfig.di.screens.ViewerModule;
import home.work.pcconfig.ui.detail.EditOrderActivity;
import home.work.pcconfig.ui.viewer.ViewerActivity;

@Module(includes = AndroidSupportInjectionModule.class)
abstract class AppGlobalModule {

    @PerActivity
    @ContributesAndroidInjector(modules = {
            CommonActivityModule.class,
            ThrowableModule.class,
            ToolbarEmptyModule.class,
            DrawerEmptyModule.class,
            CommonScreenModule.class,
            ViewerModule.class,
            ViewerModule.Binder.class}
    )
    abstract ViewerActivity viewerActivityInjector();

    @PerActivity
    @ContributesAndroidInjector(modules = {
            CommonActivityModule.class,
            ThrowableModule.class,
            ToolbarEmptyModule.class,
            DrawerEmptyModule.class,
            CommonScreenModule.class,
            EditModule.class,}
    )
    abstract EditOrderActivity viewerEditOrderActivity();
}