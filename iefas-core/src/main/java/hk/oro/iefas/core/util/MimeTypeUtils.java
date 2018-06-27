package hk.oro.iefas.core.util;

import java.util.HashMap;
import java.util.Map;

public class MimeTypeUtils {

    public static enum FileExtension {
        CSV, PDF;
    }

    private static final Map<String, String> MIME_TYPE_MAPPING = new HashMap<>();
    static {
        MIME_TYPE_MAPPING.put(FileExtension.CSV.name(), "text/comma-separated-values");
        MIME_TYPE_MAPPING.put(FileExtension.PDF.name(), "application/pdf");
    }

    public static String getMimeType(String extension) {
        if (CommonUtils.isEmpty(extension)) {
            throw new RuntimeException("extension must be not null");
        }
        return MIME_TYPE_MAPPING.get(extension.toUpperCase());
    }
}
