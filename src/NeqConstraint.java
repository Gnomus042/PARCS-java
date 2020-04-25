import java.io.Serializable;

class NeqConstraint extends Constraint<String, String> implements Serializable {
    private String varA, varB;

    NeqConstraint(String varA, String varB) {
        this.varA = varA;
        this.varB = varB;
    }

    @Override
    public boolean check(Solution<String, String> solution) {
        return !solution.getValue(varA).equals(solution.getValue(varB));
    }
}