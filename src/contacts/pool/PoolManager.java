package contacts.pool;

import java.util.*;

public class PoolManager {
    private final Map<Class<?>, Pool> poolMap = new HashMap<>();
    private final Set<Class<?>> managedPools = new HashSet<>();

    /**
     * Adds a pool to the pool manager if not present
     * @param type data type the pool contains
     * @return true if a new pool was added, false if there was already one
     * @param <T> type of data stored in the pool
     */
    public <T extends Keyed> boolean addPool(Class<T> type) {
        managedPools.add(type);
        return poolMap.putIfAbsent(type, new Pool()) == null;
    }

    /**
     * Return a value from the respective pool based on the type
     * @param type data type we expect to get
     * @param key value of the key under which the value is stored
     * @return optional of the value we want to get
     * @param <T> type of data stored in the pool
     */
    public <T extends Keyed> Optional<T> getValue(Class<T> type, String key) {
        if (!poolMap.containsKey(type)) {
            return Optional.empty();
        }
        return Optional.ofNullable(type.cast(poolMap.get(type).get(key)));
    }

    public <T extends Keyed> boolean putValue(T value) {
        if (!poolMap.containsKey(value.getClass())) {
            return false;
        }
        poolMap.get(value.getClass()).put(value);
        return true;

        //return putValue(value.getClass(), value);
    }

    /**
     * Store a value in the pool manager
     * @param type type of the value
     * @param value the value itself
     * @return true if value was stored successfully
     * @param <T> type of data stored in the pools
     */
    private <T extends Keyed> boolean putValue(Class<T> type, Keyed value) {
        if (!poolMap.containsKey(type)) {
            return false;
        }
        poolMap.get(type).put(value);
        return true;
    }

    public List<? super Keyed> getAll() {
        return poolMap.values().stream().flatMap(pool -> pool.getAll().stream()).toList();
    }

    public int getSize() {
        return poolMap.values().stream().reduce(0, (integer, pool) -> integer + pool.getSize(), Integer::sum);
    }
}
