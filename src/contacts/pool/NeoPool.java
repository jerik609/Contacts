package contacts.pool;

import java.util.HashMap;
import java.util.Map;

public class NeoPool {

    private final Map<String, Keyed> pool = new HashMap<>();

    Keyed get(String key) {
        return pool.get(key);
    }

    void put(Keyed element) {
        pool.put(element.getKey(), element);
    }

}
