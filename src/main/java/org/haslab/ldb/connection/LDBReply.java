package org.haslab.ldb.connection;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class LDBReply {

    private int code;
    private Object object;

    public LDBReply() {
    }

    public int getCode() {
        return code;
    }

    public void setStatusCode(int code) {
        this.code = code;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
