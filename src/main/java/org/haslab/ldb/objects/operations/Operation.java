package org.haslab.ldb.objects.operations;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public abstract class Operation {

    private final String name;

    public Operation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
