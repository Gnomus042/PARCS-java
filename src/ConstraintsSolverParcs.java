import parcs.AM;
import parcs.AMInfo;

@SuppressWarnings("unchecked")
public class ConstraintsSolverParcs implements AM {

    @Override
    public void run(AMInfo info) {

        System.out.println("Started.");

        Domains<String, String> domains = (Domains<String, String>) info.parent.readObject();
        Constraints<String, String> constraints = (Constraints<String, String>) info.parent.readObject();

        ConstraintsSolver<String, String> solver = new ConstraintsSolver<String, String>(domains, constraints);

        System.out.println("Read finished.");

        solver.solve();

        System.out.println("Solved.");

        info.parent.write(solver.getAllSolutions().size());
    }
}
