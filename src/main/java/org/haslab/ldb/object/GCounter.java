package org.haslab.ldb.object;

import org.haslab.ldb.object.operation.GCounterOperation;
import org.haslab.ldb.LDB;
import org.haslab.ldb.LDBType;
import org.haslab.ldb.object.operation.Operation;
import org.haslab.ldb.object.reply.GCounterReply;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class GCounter extends CRDT {

    private int value;

    public GCounter(String key) {
        super(key);
        this.value = 0;

        LDB.create(key, LDBType.GCOUNTER);
    }

    public int value() {
        GCounterReply reply = (GCounterReply) LDB.query(this.getKey(), LDBType.GCOUNTER);
        load(reply);
        return this.value;
    }

    public void increment() {
        Operation op = new GCounterOperation();
        LDB.update(this.getKey(), LDBType.GCOUNTER, op);
        this.value += 1;
    }

    private void load(GCounterReply reply) {
        this.value = reply.getValue();
    }
}
