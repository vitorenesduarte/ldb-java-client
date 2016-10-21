package org.haslab.ldb;

import java.io.IOException;
import org.haslab.ldb.exceptions.KeyAlreadyExistsException;
import org.haslab.ldb.exceptions.KeyNotFoundException;
import org.haslab.ldb.objects.GSet;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class LDBTest {

    public LDBTest() {
    }

    @Test
    public void testCreateOk() throws KeyAlreadyExistsException {
        LDB.create("create_ok_a", LDBType.GSET);
        LDB.create("create_ok_b", LDBType.GSET);
    }

    @Test(expected = KeyAlreadyExistsException.class)
    public void testCreateFail() throws KeyAlreadyExistsException {
        LDB.create("create_fail_a", LDBType.GSET);
        LDB.create("create_fail_a", LDBType.GSET);
    }

    @Test
    public void testUpdateOk() throws IOException, KeyAlreadyExistsException, KeyNotFoundException {
        GSet gset = (GSet) LDB.create("update_ok", LDBType.GSET);
        gset.add("a");
        assertTrue(gset.contains("a"));
    }
}
