import java.io.Serializable;

public class Constraint<T, V> implements Serializable {
    boolean check(Solution<T, V> solution) {
        return true;
    }
}
