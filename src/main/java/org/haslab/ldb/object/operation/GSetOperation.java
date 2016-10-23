package org.haslab.ldb.object.operation;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 * @param <T>
 */
public class GSetOperation<T> extends Operation {

    private final T elem;

    public GSetOperation(T elem) {
        super("add");
        this.elem = elem;
    }

    public T getElem() {
        return elem;
    }
}
