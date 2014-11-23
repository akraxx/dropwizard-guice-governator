package fr.mmarie.core.database;

import com.google.inject.Inject;
import com.netflix.governator.guice.lazy.LazySingleton;
import fr.mmarie.GuiceGovernatorConfiguration;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.skife.jdbi.v2.DBI;

/**
 * Created by Maximilien on 23/11/2014.
 */
@LazySingleton
@Slf4j
public class DbiWrapperImpl implements DbiWrapper {

    private final DBI dbi;

    @Inject
    public DbiWrapperImpl(GuiceGovernatorConfiguration configuration, Environment environment) throws ClassNotFoundException {
        log.info("Instantiate '{}'", getClass().getName());
        dbi = new DBIFactory().build(environment, configuration.getDatabase(), "db-h2");
    }

    @Override
    public DBI get() {
        log.info("Get dbi");
        return dbi;
    }
}
