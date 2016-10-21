package org.haslab.ldb;

import org.haslab.ldb.objects.GCounter;
import org.haslab.ldb.objects.GSet;
import org.haslab.ldb.objects.CRDT;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public enum LDBType {

    GCOUNTER("gcounter"),
    GSET("gset");

    private final String type;

    public static LDBType fromType(String type) {
        if (type.equals(GCOUNTER.getType())) {
            return GCOUNTER;
        } else {
            assert type.equals(GSET.getType());
            return GSET;
        }
    }

    LDBType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public CRDT create(String key) {
        switch (this) {
            case GCOUNTER:
                return new GCounter(key);
            case GSET:
                return new GSet(key);
        }
        assert false;
        return null;
    }
}
