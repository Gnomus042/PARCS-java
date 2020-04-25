all: run

clean:
	rm -f out/Main.jar out/ConstraintsSolverParcs.jar

out/Main.jar: out/parcs.jar src/Main.java src/Solution.java src/Domains.java src/Constraints.java src/Constraint.java src/ConstraintsSolver.java src/NeqConstraint.java src/EqConstraint.java
	@javac -cp out/parcs.jar src/Main.java src/Solution.java src/Domains.java src/Constraints.java src/Constraint.java src/ConstraintsSolver.java src/NeqConstraint.java src/EqConstraint.java
	@jar cf out/Main.jar -C src Main.class -C src Solution.class -C src Domains.class -C src Constraints.class -C src Constraint.class -C src ConstraintsSolver.class -C src NeqConstraint.class -C src EqConstraint.class
	@rm -f src/Main.class src/Solution.class src/Domains.class src/Constraints.class src/Constraint.class src/ConstraintsSolver.class src/NeqConstraint.class src/EqConstraint.class

out/ConstraintsSolverParcs.jar: out/parcs.jar src/Solution.java src/Domains.java src/ConstraintsSolverParcs.java src/ConstraintsSolver.java src/Constraints.java src/Constraint.java src/NeqConstraint.java src/EqConstraint.java
	@javac -cp out/parcs.jar src/Solution.java src/Domains.java src/ConstraintsSolverParcs.java src/ConstraintsSolver.java src/Constraints.java src/Constraint.java src/NeqConstraint.java src/EqConstraint.java
	@jar cf out/ConstraintsSolverParcs.jar -C src Solution.class -C src Domains.class -C src ConstraintsSolverParcs.class -C src ConstraintsSolver.class -C src Constraints.class -C src Constraint.class -C src NeqConstraint.class -C src EqConstraint.class
	@rm -f src/Solution.class src/Domains.class src/ConstraintsSolverParcs.class src/ConstraintsSolver.class src/Constraints.class src/Constraint.class src/NeqConstraint.class src/EqConstraint.class

build: out/Main.jar out/ConstraintsSolverParcs.jar

run: out/Main.jar out/ConstraintsSolverParcs.jar
	@cd out && java -cp 'parcs.jar:Main.jar' Main
