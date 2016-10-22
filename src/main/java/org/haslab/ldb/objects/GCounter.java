package org.haslab.ldb.objects;

import org.haslab.ldb.objects.operations.GCounterOperation;
import org.haslab.ldb.LDB;
import org.haslab.ldb.LDBType;
import org.haslab.ldb.connection.LDBReply;
import org.haslab.ldb.objects.operations.Operation;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class GCounter extends CRDT {

    private int counter;

    public GCounter(String key) {
        super(key);
    }

    public int value() {
        LDBReply reply = LDB.query(this.getKey(), LDBType.GCOUNTER);
        load(reply);
        return this.counter;
    }

    public void increment() {
        Operation op = new GCounterOperation();
        LDBReply reply = LDB.update(this.getKey(), LDBType.GCOUNTER, op);
        load(reply);
    }

    private void load(LDBReply reply) {
        this.counter = ((Double) reply.getObject()).intValue();
    }
}
