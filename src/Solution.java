import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Solution<T, V> implements Serializable {
    private Map<T, V> values;

    public Solution() {
        values = new HashMap<>();
    }

    public void setValue(T key, V value) {
        values.put(key, value);
    }

    public V getValue(T key) {
        return values.get(key);
    }

    public Solution<T, V> copy() {
        Solution<T, V> solution = new Solution<T, V>();
        for (Map.Entry<T, V> entry : values.entrySet()) {
            solution.setValue(entry.getKey(), entry.getValue());
        }
        return solution;
    }
}
