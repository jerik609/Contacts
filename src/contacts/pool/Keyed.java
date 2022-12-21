package contacts.pool;

public abstract class Keyed {

    protected abstract String getKeyInternal();

    protected String getKey() {
        return this.getClass() + "-" + getKeyInternal();
    }
}
