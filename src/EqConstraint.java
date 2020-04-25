import java.io.Serializable;

class EqConstraint extends Constraint<String, String> implements Serializable {
    private String varA, varB;

    EqConstraint(String varA, String varB) {
        this.varA = varA;
        this.varB = varB;
    }

    @Override
    public boolean check(Solution<String, String> solution) {
        return solution.getValue(varA).equals(solution.getValue(varB));
    }
}