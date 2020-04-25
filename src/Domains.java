import java.io.Serializable;
import java.util.*;

public class Domains<T, V> implements Serializable {

    private Map<T, List<V>> domains;

    public Domains() {
        domains = new HashMap<>();
    }

    public void addDomain(T key, Collection<V> domain) {
        domains.put(key, new ArrayList<>(domain));
    }

    public Collection<V> getDomain(T key) {
        return domains.getOrDefault(key, Collections.emptyList());
    }

    public Collection<T> getDomainKeys() {
        return domains.keySet();
    }

    public int getNumOfDomains() {
        return domains.size();
    }

}
