package org.haslab.ldb.connection;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public enum LDBReplyStatus {

    OK(0),
    KEY_ALREADY_EXISTS(1),
    KEY_NOT_FOUND(2);

    private final int code;

    private LDBReplyStatus(int code) {
        this.code = code;
    }

    public int getStatusCode() {
        return this.code;
    }

}
