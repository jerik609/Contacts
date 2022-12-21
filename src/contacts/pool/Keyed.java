package contacts.pool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public abstract class Keyed {

    protected LocalDateTime createdTime;
    protected LocalDateTime updatedTime;

    protected Keyed() {
        createdTime = LocalDateTime.now();
        updatedTime = createdTime;
    }

    protected abstract String getKeyInternal();

    protected String getKey() {
        return this.getClass() + "-" + getKeyInternal();
    }

    public abstract String shortDesc();

    @Override
    public String toString() {
        return "Time created: " + createdTime.truncatedTo(ChronoUnit.MINUTES) + "\n" +
                "Time last edit: " + updatedTime.truncatedTo(ChronoUnit.MINUTES) + "\n";
    }
}
