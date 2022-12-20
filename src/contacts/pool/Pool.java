package contacts.pool;

import java.util.HashMap;
import java.util.Map;

public class Pool<T extends Keyed> {

    private final Map<String, T> pool = new HashMap<>();

    T get(String key) {
        return pool.get(key);
    }

    void put(Class<T> type, T element) {
        pool.put(element.getKey(), element);
    }

}
