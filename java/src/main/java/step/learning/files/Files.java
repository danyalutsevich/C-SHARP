package step.learning.files;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Files {

    public void dir() {
        File dir = new File(".");

        if (dir.exists()) {
            System.out.println("Path " + dir + " exists. Absolute path: " + dir.getAbsolutePath() + "IsFile: " + dir.isFile());
        } else {
            System.out.println("Path " + dir + " not exists. Absolute path: " + dir.getAbsolutePath() + "IsFile: " + dir.isFile());
        }

        String uploadPath = "./upload";

        File upload = new File(uploadPath);

        if (upload.exists()) {
            System.out.println(uploadPath + " exists " + (upload.isDirectory() ? "and it is a directory" : "and it is not a directory"));

            if (upload.isDirectory()) {

            } else {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.print("Delete object? y/n ");
                    String next = scanner.next();
                    if (next.equals("y")) {
                        break;
                    }
                }

                upload.delete();
            }


        }

        upload.mkdir();
        System.out.println("Directory created");


        String logPath = "./log";
        File log = new File(logPath);

        if (log.exists()) {
            System.out.println(logPath + " exists " + (log.isDirectory() ? "and it is a directory" : "and it is not a directory"));

            if (log.isDirectory()) {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.print("Delete object? y/n ");
                    String next = scanner.next();
                    if (next.equals("y")) {
                        log.delete();
                        try {
                            log.createNewFile();
                            System.out.println("File created");
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;
                    } else if (next.equals("n")) {
                        break;
                    }
                }

            }


        }


    }

    public void file() {
        String filename = "";

        try (OutputStream writer = new FileOutputStream(filename, false)) {

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
