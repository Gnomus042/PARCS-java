import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Constraints<T, V> implements Serializable {
    private Collection<Constraint<T, V>> constraints;

    public Constraints() {
        constraints = new ArrayList<>();
    }

    public void addConstraint(Constraint<T, V> constraint) {
        constraints.add(constraint);
    }

    public boolean check(Solution<T, V> solution) {
        for (Constraint<T, V> c : constraints) {
            if (!c.check(solution)) {
                return false;
            }
        }
        return true;
    }
}
