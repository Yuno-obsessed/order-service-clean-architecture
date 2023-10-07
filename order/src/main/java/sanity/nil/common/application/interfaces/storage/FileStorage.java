package sanity.nil.common.application.interfaces.storage;

import java.io.FileInputStream;
import java.io.InputStream;

public interface FileStorage {

    void saveFile(FileInputStream file, String name, String objType);

    InputStream getFile(String name, String objType);
}
