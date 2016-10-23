package org.haslab.ldb.object.reply;

import java.util.List;
import org.haslab.ldb.connection.LDBReply;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 * @param <T>
 */
public class GSetReply<T> extends LDBReply {

    private List<T> value;

    public GSetReply() {
    }

    public List<T> getValue() {
        return value;
    }

    public void setValue(List<T> value) {
        this.value = value;
    }
}
