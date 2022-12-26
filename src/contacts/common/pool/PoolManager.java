package contacts.common.pool;

import java.util.*;
import java.util.stream.Stream;

public class PoolManager {
    private final Map<Class<?>, Pool> poolMap = new HashMap<>();

    private final Map<Class<?>, Object> poolMap2 = new HashMap<>();

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

    /**
     * Store a value in the pool manager
     * @param value the value itself
     * @return true if value was stored successfully
     * @param <T> type of data stored in the pools
     */
    public <T extends Keyed> boolean putValue(T value) {
        if (!poolMap.containsKey(value.getClass())) {
            return false;
        }
        poolMap.get(value.getClass()).put(value);
        return true;
    }

    public <T extends Keyed> boolean remove(T value) {
        if (!poolMap.containsKey(value.getClass())) {
            return false;
        }
        poolMap.get(value.getClass()).remove(value.getKey());
        return true;
    }

    //TODO: this does not benefit from the pattern?
    // probably victim to type erasure
    // https://stackoverflow.com/questions/2390662/java-how-do-i-get-a-class-literal-from-a-generic-type
    // but we can repack via streams from Keyed to the specific subtype of keyed (do type cast for each individual item)
    // is that not a bit inefficient?
    //TODO: optimize using streams? -> do not provide a list, but a stream of elements
    //      but we need to somehow index these items to display them properly for selection?
    /**
     * Get all elements from pools
     * @return
     */
    public <T extends Keyed> List<T> getAll(Class<T> type) {
        final var listNew = poolMap.values().stream().flatMap(pool -> pool.getAll().stream().map(type::cast)).toList();
        final var repacked = new ArrayList<T>(listNew);
        //TODO: this sorting is just a workaround to pass tests - we do not really need ordering!
        repacked.sort((keyed, t1) -> {
            if (keyed.getKey().equals(t1.getKey())) {
                return 0;
            } else {
                return Integer.parseInt(keyed.getKey()) < Integer.parseInt(t1.getKey()) ? 1 : -1;
            }
        });
        return listNew;
    }

    /**
     * Gets a pool by type as a stream.
     * @param type the pool to stream from
     * @return stream of items
     * @param <T> type of the items
     */
    public <T extends Keyed> Stream<T> getPoolAsStream(Class<T> type) {
        if (!poolMap.containsKey(type)) {
            return Stream.empty();
        }
        return poolMap.get(type).getAll().stream().map(type::cast);
    }

    /**
     * Get total number of elements in all pools
     * @return
     */
    public int getSize() {
        return poolMap.values().stream().reduce(0, (integer, pool) -> integer + pool.getSize(), Integer::sum);
    }
}
