package org.haslab.ldb.exception;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class LDBException extends RuntimeException {

    public LDBException() {
    }

    public LDBException(String msg) {
        super(msg);
    }
}
