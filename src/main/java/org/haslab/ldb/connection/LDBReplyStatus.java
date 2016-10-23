package org.haslab.ldb.connection;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public enum LDBReplyStatus {

    UNKNOWN(-2),
    INVALID(-1),
    OK(0),
    KEY_NOT_FOUND(1);

    private final int code;

    private LDBReplyStatus(int code) {
        this.code = code;
    }

    public int getStatusCode() {
        return this.code;
    }
}
