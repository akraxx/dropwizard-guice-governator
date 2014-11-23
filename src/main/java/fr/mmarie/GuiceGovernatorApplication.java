package fr.mmarie;

import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import fr.mmarie.core.database.DatabaseModule;
import fr.mmarie.core.guice.GovernatorInjectorFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by Maximilien on 23/11/2014.
 */
public class GuiceGovernatorApplication extends Application<GuiceGovernatorConfiguration> {

    @Override
    public void initialize(Bootstrap<GuiceGovernatorConfiguration> bootstrap) {
        GuiceBundle<GuiceGovernatorConfiguration> guiceBundle = GuiceBundle.<GuiceGovernatorConfiguration>newBuilder()
                .addModule(new DatabaseModule())
                .setInjectorFactory(new GovernatorInjectorFactory())
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(GuiceGovernatorConfiguration.class)
                .build(Stage.PRODUCTION);

        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(GuiceGovernatorConfiguration configuration, Environment environment) throws Exception {

    }

    public static void main(String[] args) throws Exception {
        new GuiceGovernatorApplication().run(new  String[] {"server", "properties.yml"});
    }
}
