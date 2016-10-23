package org.haslab.ldb.object.operation;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 * @param <V>
 */
public class MVMapOperation<V> extends Operation {

    private final String key;
    private final V value;

    public MVMapOperation(String key, V value) {
        super("set");
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
