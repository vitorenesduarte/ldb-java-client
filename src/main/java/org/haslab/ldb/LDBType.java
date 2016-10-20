package org.haslab.ldb;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public enum LDBType {

    GCounter("gcounter"),
    GSet("gset");

    private final String type;

    LDBType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
