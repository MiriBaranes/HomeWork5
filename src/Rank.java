import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Rank {
    CUSTOMER(0),
    WORKER(1),
    MANAGER(2),
    DIRECTOR(3);

    private final int value;

    Rank(int rank) {
        this.value = rank;
    }

    public int getValue() {
        return this.value;
    }

    private static final Map<Integer, Rank> reverseLookup = Arrays.stream(Rank.values()).collect(Collectors.toMap(Rank::getValue, Function.identity()));

    public static Rank fromInt(final int id) {
        return reverseLookup.getOrDefault(id, null);
    }
}
