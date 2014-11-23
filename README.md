dropwizard-guice-governator
===========================

Use governator with dropwizard-guice to avoid : "The dropwizard environment has not yet been set."

Common error with guice on dropwizard :

com.hubspot.dropwizard.guice.GuiceBundle: Exception occurred when creating Guice Injector - exiting
! com.google.inject.CreationException: Guice creation errors:
! 
! 1) The dropwizard environment has not yet been set. This is likely caused by trying to access the dropwizard environment during the bootstrap phase.

It happens when you have a guice singleton on a class which injects the dropwizard configuration or environment.

To avoid this you can :
- Set the guice stage to "DEVELOPMENT", but everything will be loaded in lazy mode
- Use wrapper like this : https://github.com/HubSpot/dropwizard-guice/issues/19#issuecomment-40389232
- Or use governator, to load only some singletons in lazy mode : https://github.com/Netflix/governator/wiki/Lazy-Singleton

Using governator
=========

To configure governator with the extension https://github.com/HubSpot/dropwizard-guice, you have to :

* Use the last version of this extension, this one allows you to set your own injector.
* Use the last version of governator

```xml
        <dependency>
            <groupId>com.hubspot.dropwizard</groupId>
            <artifactId>dropwizard-guice</artifactId>
            <version>${dropwizard-guice.version}</version>
        </dependency>

        <dependency>
            <groupId>com.netflix.governator</groupId>
            <artifactId>governator</artifactId>
            <version>${governator.version}</version>
        </dependency>

        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>${hibernate-validator.version}</version>
        </dependency>
```

Notice that you have to have a dependency on the hibernate-validator package

Now Implements the injector as follow

```java
public class GovernatorInjectorFactory implements InjectorFactory
{
    @Override
    public Injector create( final Stage stage, final List<Module> modules )
    {
        return LifecycleInjector.builder().inStage( stage ).withModules( modules ).build()
            .createInjector();
    }
}
```

and then set the InjectorFactory when initializing the GuiceBundle:

```java
	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {

		GuiceBundle<HelloWorldConfiguration> guiceBundle = GuiceBundle.<HelloWorldConfiguration>newBuilder()
				.addModule(new HelloWorldModule())
				.enableAutoConfig(getClass().getPackage().getName())
				.setConfigClass(HelloWorldConfiguration.class)
				.setInjectorFactory( new GovernatorInjectorFactory() )
				.build();

		bootstrap.addBundle(guiceBundle);
	}
```

--> From the [dropwizard-guice documentation](https://github.com/HubSpot/dropwizard-guice)
