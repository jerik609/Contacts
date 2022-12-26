package contacts.common.pool;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.UUID;

public abstract class Keyed {
    private String myKey;
    protected LocalDateTime createdTime;
    protected LocalDateTime updatedTime;

    private static int uniqueKey = 0;

    protected Keyed() {
        myKey = String.valueOf(++uniqueKey);
        //myKey = this.getClass() + "_" + UUID.randomUUID();
        createdTime = LocalDateTime.now();
        updatedTime = createdTime;
    }

    public final String getKey() {
        return myKey;
    }

    protected void copyKeyFrom(Keyed other) {
        this.myKey = other.myKey;
    }

    public abstract Keyed updateFromSelf(Scanner scanner);

    public abstract String shortDesc();

    public abstract String searchableDesc();

    @Override
    public String toString() {
        return "Time created: " + createdTime.truncatedTo(ChronoUnit.MINUTES) + "\n" +
                "Time last edit: " + updatedTime.truncatedTo(ChronoUnit.MINUTES) + "\n";
    }
}
