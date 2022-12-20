package contacts.pool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OldPool<T extends Keyed> {

    private final HashMap<String, T> pool;

    public OldPool() {
        pool = new HashMap<>();
    }

    public int getSize() {
        return pool.size();
    }

    public T lookup(String key) {
        return pool.get(key);
    }

    public List<Map.Entry<String, T>> getAll() {
        return pool.entrySet().stream().toList();
    }

    public void insert(T item) {
        if (item == null) {
            System.out.println("Could not add null value");
        } else {
            pool.put(item.getKey(), item);
        }
    }

    public void remove(String key) {
        pool.remove(key);
    }

}
