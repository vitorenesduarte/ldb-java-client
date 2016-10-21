package org.haslab.ldb.objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.haslab.ldb.LDB;
import org.haslab.ldb.LDBType;
import org.haslab.ldb.connection.LDBReply;
import org.haslab.ldb.objects.operations.GSetOperation;
import org.haslab.ldb.objects.operations.Operation;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 * @param <T>
 */
public class GSet<T> extends CRDT {

    private HashSet<T> set;

    public GSet(String key) {
        super(key);
        this.set = new HashSet<>();
    }

    public void load() {
        LDBReply reply = LDB.query(this.getKey(), LDBType.GSET);
        List<T> list = (List<T>) reply.getObject();
        this.set = new HashSet<>(list);
    }

    public void add(T t) {
        Operation op = new GSetOperation(t);
        LDBReply reply = LDB.update(this.getKey(), LDBType.GSET, op);
        List<T> list = (List<T>) reply.getObject();
        this.set = new HashSet<>(list);
    }

    public boolean contains(T t) {
        return set.contains(t);
    }

    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }
}
