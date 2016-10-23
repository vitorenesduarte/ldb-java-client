package org.haslab.ldb.object;

import java.util.HashSet;
import java.util.Set;
import org.haslab.ldb.LDB;
import org.haslab.ldb.LDBType;
import org.haslab.ldb.object.operation.GSetOperation;
import org.haslab.ldb.object.operation.Operation;
import org.haslab.ldb.object.reply.GSetReply;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 * @param <T>
 */
public class GSet<T> extends CRDT {

    private HashSet<T> value;

    public GSet(String key) {
        super(key);
        this.value = new HashSet<>();

        LDB.create(key, LDBType.GSET);
    }

    public Set<T> value() {
        GSetReply reply = (GSetReply) LDB.query(this.getKey(), LDBType.GSET);
        load(reply);
        return this.value;
    }

    public void add(T t) {
        Operation op = new GSetOperation(t);
        GSetReply reply = (GSetReply) LDB.update(this.getKey(), LDBType.GSET, op);
        load(reply);
    }

    private void load(GSetReply reply) {
        this.value = new HashSet<>(reply.getValue());
    }
}
