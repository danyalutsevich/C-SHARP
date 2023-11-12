package step.learning.services.formParse;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class FormParseService implements FormParse {
    private static final int MAX_FILE_SIZE = 10 * 1024 * 1024;
    private static final int MAX_FORM_SIZE = 20 * 1024 * 1024;
    private final ServletFileUpload fileUpload;

    @Inject
    public FormParseService() {
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        fileItemFactory.setSizeThreshold(this.MAX_FILE_SIZE);
        fileItemFactory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        fileUpload = new ServletFileUpload(fileItemFactory);
        fileUpload.setFileSizeMax(this.MAX_FILE_SIZE);
        fileUpload.setSizeMax(this.MAX_FORM_SIZE);
    }

    @Override
    public FormParseResult parse(HttpServletRequest req) {
        final Map<String, String> fields = new HashMap<>();
        final Map<String, FileItem> files = new HashMap<>();
        final HttpServletRequest request = req;

        boolean isMultipart = request.getHeader("Content-Type") != null && request.getHeader("Content-Type").startsWith("multipart/form-data");
        String charset = (String) request.getAttribute("charset");

        if (charset == null) {
            charset = StandardCharsets.UTF_8.name();
        }

        if (isMultipart) {
            try {
                for (FileItem item : fileUpload.parseRequest(request)) {
                    if (item.isFormField()) {
                        fields.put(item.getFieldName(), item.getString(charset));
                    } else {
                        files.put(item.getFieldName(), item);
                    }
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else {
            for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
                fields.put(entry.getKey(), entry.getValue()[0]);
            }
        }

        return new FormParseResult() {
            @Override
            public Map<String, String> getFields() {
                return fields;
            }

            @Override
            public Map<String, FileItem> getFiles() {
                return files;
            }

            @Override
            public HttpServletRequest getRequest() {
                return request;
            }
        };
    }


}
