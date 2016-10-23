package org.haslab.ldb.object;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public abstract class CRDT {

    private final String key;

    public CRDT(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
