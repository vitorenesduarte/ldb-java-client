package org.haslab.ldb.exceptions;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class KeyAlreadyExistsException extends Exception {

    public KeyAlreadyExistsException() {
    }

    public KeyAlreadyExistsException(String msg) {
        super(msg);
    }
}
