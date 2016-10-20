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
        ldb.create("d", LDBType.GSet);
        ldb.create("c", LDBType.GSet);
        System.out.println("COOL");
    }
}
