package org.haslab.ldb.object.reply;

import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import org.haslab.ldb.connection.LDBReply;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 * @param <V>
 */
public class MVMapReply<V> extends LDBReply {

    private List<Entry<V>> value;

    public MVMapReply() {
    }

    public HashMap<String, V> getValue() {
        HashMap<String, V> result = new HashMap<>();

        for (Entry<V> e : value) {
            // Client-side conflict resolution:
            // pick the lesser value
            // E.g. for strings, it will pick the
            // lesser value in the lexicographic order
            TreeSet<V> values = new TreeSet<>(e.getValues());
            result.put(e.getKey(), values.first());
        }

        return result;
    }

    private class Entry<V> {

        private String key;
        private List<V> values;

        public Entry() {
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<V> getValues() {
            return values;
        }

        public void setValues(List<V> values) {
            this.values = values;
        }
    }
}
