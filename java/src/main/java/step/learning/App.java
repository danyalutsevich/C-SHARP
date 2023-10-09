package step.learning;

import com.google.inject.Guice;
import com.google.inject.Injector;
import step.learning.armory.ShootingRange;
import step.learning.basic.Basic;
import step.learning.files.Files;
import step.learning.files.Ls;
import step.learning.ioc.ConfigModule;
import step.learning.ioc.Ioc;
import step.learning.ioc.NamedIoc;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ShootingRange shootingRange = new ShootingRange();
        shootingRange.run();

//        Injector injector = Guice.createInjector(new ConfigModule());
//        Ioc ioc = injector.getInstance(Ioc.class);
////        ioc.run();
//
//        NamedIoc namedIoc = injector.getInstance(NamedIoc.class);
//        namedIoc.run();

    }
}
