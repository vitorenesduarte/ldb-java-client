package org.haslab.ldb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    @Test(expected = KeyAlreadyExistsException.class)
    public void testCreate() throws KeyAlreadyExistsException {
        LDB.create("create", LDBType.GSET);
        LDB.create("create", LDBType.GSET);
    }

    @Test
    public void testUpdate() throws IOException, KeyAlreadyExistsException, KeyNotFoundException {
        GSet gset = (GSet) LDB.create("update", LDBType.GSET);
        gset.add("a");
        assertTrue(gset.contains("a"));
    }

    @Test
    public void testQuery() throws KeyAlreadyExistsException {
        GSet gset = (GSet) LDB.create("query", LDBType.GSET);
        gset.add("a");
        gset.add("b");
        gset.add("a");
        gset.add("c");
        gset.load();
        List<String> expected = new ArrayList<>();
        expected.add("a");
        expected.add("b");
        expected.add("c");
        assertTrue(gset.containsAll(expected));
    }
}
