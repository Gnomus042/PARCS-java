import java.io.Serializable;
import java.util.ArrayList;

public class ConstraintsSolver<T, V> implements Serializable {

    private Domains<T, V> domains;
    private Constraints<T, V> constraints;
    private ArrayList<Solution<T, V>> solutions;

    public ConstraintsSolver(Domains<T, V> domains, Constraints<T, V> constraints) {
        this.domains = domains;
        this.constraints = constraints;
    }

    public void solveRecursively(int i, ArrayList<T> keys, Solution<T, V> solution) {
        if (i >= keys.size()) {
            if (constraints.check(solution)) {
                solutions.add(solution.copy());
            }
            return;
        }
        for (V value : domains.getDomain(keys.get(i))) {
            solution.setValue(keys.get(i), value);
            solveRecursively(i + 1, keys, solution);
        }
    }

    public void solve() {
        solutions = new ArrayList<>();
        solveRecursively(0, new ArrayList<>(domains.getDomainKeys()), new Solution<>());
    }

    public ArrayList<Solution<T, V>> getAllSolutions() {
        return solutions;
    }

    public Solution<T, V> getAnySolution() {
        return solutions.isEmpty() ? null : solutions.get(0);
    }

    public boolean hasSolutions() {
        return !solutions.isEmpty();
    }

}
