package step.learning.ioc;

import step.learning.ioc.services.hash.HashService;
import step.learning.ioc.services.hash.Md5HashService;
import step.learning.ioc.services.hash.Md5OldHashService;

import javax.inject.Inject;

public class Ioc {

    @Inject
    public HashService hashService;

    @Inject
    public Md5HashService md5HashService;

    @Inject
    public Md5OldHashService md5OldHashService;

    public void run() {

        long t1;
        long t2;
        String hash;

//        md5HashService.hash("IOC");
//        md5OldHashService.hash("IOC");

        t1 = System.nanoTime();
        hash = md5HashService.hash("IOC");
        t2 = System.nanoTime();

        System.out.println("new: " + hash + " " + (t2 - t1));

        t1 = System.nanoTime();
        hash = md5OldHashService.hash("IOC");
        t2 = System.nanoTime();


        System.out.println("old: " + hash + " " + (t2 - t1));


    }

}
