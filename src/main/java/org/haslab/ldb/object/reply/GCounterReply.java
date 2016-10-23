package org.haslab.ldb.object.reply;

import org.haslab.ldb.connection.LDBReply;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class GCounterReply extends LDBReply {

    private int value;

    public GCounterReply() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
