package contacts.common.pool;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Pool {

    private final Map<String, Keyed> pool = new HashMap<>();

    Keyed get(String key) {
        return pool.get(key);
    }

    void put(Keyed element) {
        pool.put(element.getKey(), element);
    }

    Collection<Keyed> getAll() {
        return pool.values();
    }

    int getSize() {
        return pool.size();
    }

    void remove(String key) {
        pool.remove(key);
    }
}
