package org.haslab.ldb.objects;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

        LDB.create(key, LDBType.GSET);
    }

    public Set<T> value() {
        LDBReply reply = LDB.query(this.getKey(), LDBType.GSET);
        load(reply);
        return this.set;
    }

    public void add(T t) {
        Operation op = new GSetOperation(t);
        LDBReply reply = LDB.update(this.getKey(), LDBType.GSET, op);
        load(reply);
    }

    private void load(LDBReply reply) {
        List<T> list = (List<T>) reply.getObject();
        this.set = new HashSet<>(list);
    }
}
