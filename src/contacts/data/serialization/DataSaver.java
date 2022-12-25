package contacts.data.serialization;

import contacts.data.entities.Organization;
import contacts.data.entities.Person;
import contacts.common.pool.Keyed;
import contacts.common.pool.PoolManager;

import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class DataSaver {

    private static final Map<Class<? extends Keyed>, String> saveFileMapping = new HashMap<>();
    private static final String PERSON_DATA_FILE = "contacts_person.dat";
    private static final String ORGANIZATION_DATA_FILE = "contacts_organization.dat";

    private final PoolManager poolManager;

    public DataSaver(PoolManager poolManager) {
        this.poolManager = poolManager;
        saveFileMapping.put(Person.class, PERSON_DATA_FILE);
        saveFileMapping.put(Organization.class, ORGANIZATION_DATA_FILE);
    }

    public void saveData() throws IOException {
        for (var job : saveFileMapping.entrySet()) {
            final var path = Paths.get(job.getValue());
            try (
                    final var fileOutputStream = new FileOutputStream(path.toFile());
                    final var bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    final var objectOutputStream = new ObjectOutputStream(bufferedOutputStream)) {
                poolManager.getPoolAsStream(job.getKey()).forEach(
                        item -> {
                            try {
                                objectOutputStream.writeObject(item);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                // write eos marker
                objectOutputStream.writeObject(null);
            }
        }
    }

    public void loadData() throws IOException {
        for (var job : saveFileMapping.entrySet()) {
            final var path = Paths.get(job.getValue());
            try (
                    final var fileInputStream = new FileInputStream(path.toFile());
                    final var bufferedInputStream = new BufferedInputStream(fileInputStream);
                    final var objectInputStream = new ObjectInputStream(bufferedInputStream)) {
                var obj = objectInputStream.readObject();
                while (obj != null) {
                    poolManager.putValue(job.getKey().cast(obj));
                    obj = objectInputStream.readObject();
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
