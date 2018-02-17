package home.work.pcconfig.di;


import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import home.work.pcconfig.data.db.providers.OrdersDp;
import home.work.pcconfig.data.db.providers.OrdersDpImpl;

@Module
interface ProvidersModule {

    @Binds
    @Singleton
    OrdersDp ordersDp(OrdersDpImpl ordersDp);
}
