import parcs.AMInfo;
import parcs.channel;
import parcs.point;
import parcs.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        task curtask = new task();
        curtask.addJarFile("ConstraintsSolverParcs.jar");

        Domains<String, String> domains = fromFile(curtask.findFile("input.txt"));
        Constraints<String, String> cs = new Constraints<>();
        cs.addConstraint(new NeqConstraint("x1", "x2"));
        cs.addConstraint(new NeqConstraint("x2", "x3"));
        cs.addConstraint(new NeqConstraint("x1", "x3"));
        cs.addConstraint(new NeqConstraint("x1", "x6"));
        cs.addConstraint(new NeqConstraint("x3", "x5"));
        cs.addConstraint(new NeqConstraint("x1", "x5"));
        cs.addConstraint(new NeqConstraint("x5", "x6"));
        cs.addConstraint(new NeqConstraint("x1", "x7"));
        cs.addConstraint(new NeqConstraint("x3", "x7"));
        cs.addConstraint(new NeqConstraint("x4", "x7"));
        cs.addConstraint(new NeqConstraint("x6", "x7"));
        cs.addConstraint(new EqConstraint("x5", "x7"));
        cs.addConstraint(new EqConstraint("x2", "x7"));

        List<Domains<String, String>> splitted = splitDomains(domains);

        List<AMInfo> infos = new ArrayList<>();
        List<point> points = new ArrayList<>();
        List<channel> channels = new ArrayList<>();

        for (Domains<String, String> dom : splitted) {
            AMInfo info = new AMInfo(curtask, null);
            point p = info.createPoint();
            channel c = p.createChannel();
            p.execute("ConstraintsSolverParcs");
            c.write(dom);
            c.write(cs);

            infos.add(info);
            points.add(p);
            channels.add(c);
        }

        int totalSolutions = 0;

        System.out.println("Waiting for solutions...");

        for (channel c : channels) {
            totalSolutions += c.readInt();
            System.out.println("Updated, solutions found: " + totalSolutions);
        }

        curtask.end();
    }

    private static List<Domains<String, String>> splitDomains(Domains<String, String> domains) {
        List<Domains<String, String>> splitted = new ArrayList<>();
        List<String> keys = new ArrayList<>(domains.getDomainKeys());
        for (String val : domains.getDomain(keys.get(0))) {
            Domains<String, String> part = new Domains<>();
            part.addDomain(keys.get(0), Collections.singletonList(val));
            for (int i = 1; i < keys.size(); i++) {
                part.addDomain(keys.get(i), domains.getDomain(keys.get(i)));
            }
            splitted.add(part);
        }
        return splitted;
    }

    private static Domains<String, String> fromFile(String filename) throws FileNotFoundException {
        Domains<String, String> domains = new Domains<>();
        Scanner sc = new Scanner(new File(filename));
        int keys = sc.nextInt();
        for (int i = 0; i < keys; i++) {
            String key = sc.next();
            int domainSize = sc.nextInt();
            List<String> domain = new ArrayList<>();
            for (int j = 0; j < domainSize; j++) {
                domain.add(sc.next());
            }
            domains.addDomain(key, domain);
        }
        return domains;
    }
}
