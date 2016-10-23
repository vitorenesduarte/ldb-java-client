package org.haslab.ldb.object;

import java.util.HashMap;
import java.util.Map;
import org.haslab.ldb.LDB;
import org.haslab.ldb.LDBType;
import org.haslab.ldb.object.operation.MVMapOperation;
import org.haslab.ldb.object.operation.Operation;
import org.haslab.ldb.object.reply.MVMapReply;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 * @param <V>
 */
public class MVMap<V> extends CRDT {

    private HashMap<String, V> value;

    public MVMap(String key) {
        super(key);
        this.value = new HashMap<>();

        LDB.create(key, LDBType.MVMAP);
    }

    public Map<String, V> value() {
        MVMapReply reply = (MVMapReply) LDB.query(this.getKey(), LDBType.MVMAP);
        load(reply);
        return this.value;
    }

    public void set(String key, V v) {
        Operation op = new MVMapOperation(key, v);
        MVMapReply reply = (MVMapReply) LDB.update(this.getKey(), LDBType.MVMAP, op);
        load(reply);
    }

    private void load(MVMapReply reply) {
        this.value = reply.getValue();
    }
}
