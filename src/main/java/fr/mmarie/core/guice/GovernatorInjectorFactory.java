package fr.mmarie.core.guice;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.InjectorFactory;
import com.netflix.governator.guice.LifecycleInjector;

import java.util.List;

/**
 * Created by Maximilien on 23/11/2014.
 */
public class GovernatorInjectorFactory implements InjectorFactory {
    @Override
    public Injector create(Stage stage, List<Module> modules) {
        return LifecycleInjector.builder()
                .inStage(stage)
                .withModules(modules)
                .usingBasePackages("fr.mmarie")
                .build()
                .createInjector(modules);
    }
}
