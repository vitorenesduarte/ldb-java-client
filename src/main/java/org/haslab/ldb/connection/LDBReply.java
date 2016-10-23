package org.haslab.ldb.connection;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class LDBReply {

    private int code;

    public LDBReply() {
    }

    public int getCode() {
        return code;
    }

    public void setStatusCode(int code) {
        this.code = code;
    }
}
