package app.java.commons.constants;

import java.io.File;

public class FilePathConst {
    public static final String PREPARED_DATASET_PATH = String.join(File.separator, "files", "prepared");
    public static final String RAW_DATASET_PATH = String.join(File.separator, "files", "raw", "json", "countries");
}
