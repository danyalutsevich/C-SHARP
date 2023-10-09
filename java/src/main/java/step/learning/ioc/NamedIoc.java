package step.learning.ioc;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import step.learning.ioc.services.hash.HashService;
import step.learning.ioc.services.random.RandomService;

public class NamedIoc {

    private final HashService digestService;

    private final HashService signatureService;

    private final RandomService randomService;

    @Inject
    public NamedIoc(
            @Named("Digest-Hash")
            HashService digestService,
            @Named("Signature-Hash")
            HashService signatureService,
            @Named("DigestOptimized-Hash")
            HashService digestOptimizedService,
            RandomService randomService
    ) {
        this.digestService = digestService;
        this.signatureService = signatureService;
        this.randomService = randomService;
    }

    public void run() {
        System.out.println("NamedIoc");
        System.out.println("Digest " + digestService.hash("NamedIoc"));
        System.out.println("Signature " + signatureService.hash("NamedIoc"));
        System.out.println("Signature hashCode " + signatureService.hashCode());
        System.out.println("Digest hashCode " + digestService.hashCode());
        System.out.println("Random hex: " + randomService.randomHex(12));
    }


}
