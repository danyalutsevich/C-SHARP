package step.learning.files;

import java.io.File;
import java.sql.Date;

public class Ls {

    public void ls() {

        String folderPath = ".";

        File folder = new File(folderPath);

        System.out.println("Mode    LastModified     Length    Name");
        for (final File fileEntry : folder.listFiles()) {
            System.out.print(fileEntry.canExecute() ? "e" : "-");
            System.out.print(fileEntry.canWrite() ? "w" : "-");
            System.out.print(fileEntry.canRead() ? "r     " : "-     ");
            System.out.print(new Date(fileEntry.lastModified()) + "       ");
            System.out.print(fileEntry.length() + "       ");
            System.out.print(fileEntry.getName());
            System.out.println();
        }
    }

}
