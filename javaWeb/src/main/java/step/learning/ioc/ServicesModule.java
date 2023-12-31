package step.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import step.learning.services.auth.AuthService;
import step.learning.services.auth.JwtAuth;
import step.learning.services.db.DBProvider;
import step.learning.services.db.PlanetDBProvider;
import step.learning.services.formParse.FormParse;
import step.learning.services.formParse.FormParseService;
import step.learning.services.hash.*;
import step.learning.services.random.RandomService;
import step.learning.services.random.RandomServiceV1;

public class ServicesModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(HashService.class).annotatedWith(Names.named("Digest-hash")).to(Md5HashService.class);
        bind(HashService.class).annotatedWith(Names.named("Signature-hash")).to(Sha1HashService.class);
        bind(FormParse.class).to(FormParseService.class);
        bind(DBProvider.class).to(PlanetDBProvider.class);
        bind(JwtAuth.class).to(AuthService.class);
    }

    private RandomService randomService;

    @Provides
    private RandomService injectRandomService() {
        if (randomService == null) {
            randomService = new RandomServiceV1();
            randomService.seed("init");
        }
        return randomService;
    }

}
