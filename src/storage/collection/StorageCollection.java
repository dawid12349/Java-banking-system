package storage.collection;

import java.io.File;
import java.io.IOException;

public interface StorageCollection {
    void loadFromFile(File file) throws IOException;
    void saveToFile(File file) throws IOException;
    Integer getFreeID();
}
