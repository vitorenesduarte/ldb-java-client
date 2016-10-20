package org.haslab.ldb;

import java.io.IOException;
import org.haslab.ldb.exceptions.KeyAlreadyExistsException;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class Main {

    public static void main(String[] args) throws IOException, KeyAlreadyExistsException {
        LDB ldb = new LDB();
        ldb.create("a", LDBType.GSet);
        ldb.create("b", LDBType.GSet);

        try {
            ldb.create("a", LDBType.GSet);
        } catch (IOException | KeyAlreadyExistsException e) {
            assert e instanceof KeyAlreadyExistsException;
        }
    }
}
