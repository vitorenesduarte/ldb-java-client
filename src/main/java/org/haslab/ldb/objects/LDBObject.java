package org.haslab.ldb.objects;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public abstract class LDBObject {

    private final String key;

    public LDBObject(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
