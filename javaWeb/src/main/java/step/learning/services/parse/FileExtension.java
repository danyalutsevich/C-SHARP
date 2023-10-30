package step.learning.services.parse;

public class FileExtension {

    public static String getFileExtension(String contentType) {

        if (contentType.equals("image/jpeg")) {
            return ".jpg";
        } else if (contentType.equals("image/gif")) {
            return ".gif";
        } else if (contentType.equals("image/png")) {
            return ".png";
        }
        return null;
    }

}
