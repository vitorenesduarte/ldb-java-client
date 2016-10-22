package org.haslab.ldb;

import org.haslab.ldb.exceptions.KeyAlreadyExistsException;
import org.junit.Test;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class LDBTest {

    public LDBTest() {
    }

    @Test(expected = KeyAlreadyExistsException.class)
    public void testCreate() throws KeyAlreadyExistsException {
        LDB.create("create", LDBType.GSET);
        LDB.create("create", LDBType.GSET);
    }
}