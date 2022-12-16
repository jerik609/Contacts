package contacts.pool;

import java.util.Collection;
import java.util.HashMap;

public class Pool<T extends Poolable> {

    private final HashMap<String, T> pool;

    public Pool() {
        pool = new HashMap<>();
    }

    public T lookup(String key) {
        return pool.get(key);
    }

    public Collection<T> getAll() {
        return pool.values();
    }

    public void insert(T item) {
        if (item == null) {
            System.out.println("Could not add null value");
        } else {
            pool.put(item.getKey(), item);
        }
    }

}
