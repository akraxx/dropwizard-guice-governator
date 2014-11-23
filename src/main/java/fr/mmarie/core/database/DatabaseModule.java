package fr.mmarie.core.database;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import fr.mmarie.keepalive.KeepAliveDao;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Maximilien on 23/11/2014.
 */
@Slf4j
public class DatabaseModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DbiWrapper.class).to(DbiWrapperImpl.class);
    }

    @Provides
    public KeepAliveDao providesKeepAliveDao(DbiWrapper dbiWrapper) {
        log.info("Provides keepalive dao");
        return dbiWrapper.get().onDemand(KeepAliveDao.class);
    }
}
