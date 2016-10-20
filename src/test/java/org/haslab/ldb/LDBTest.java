package org.haslab.ldb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.haslab.ldb.exceptions.KeyAlreadyExistsException;
import org.haslab.ldb.exceptions.KeyNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class LDBTest {

    private static LDB ldb;

    public LDBTest() {
    }

    @BeforeClass
    public static void setUpClass() throws IOException {
        LDBTest.ldb = new LDB();
    }

    @AfterClass
    public static void tearDownClass() throws IOException {
        LDBTest.ldb.close();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateOk() throws IOException, KeyAlreadyExistsException {
        ldb.create("create_ok_a", LDBType.GSet);
        ldb.create("create_ok_b", LDBType.GSet);
    }

    @Test(expected = KeyAlreadyExistsException.class)
    public void testCreateFail() throws IOException, KeyAlreadyExistsException {
        ldb.create("create_fail_a", LDBType.GSet);
        ldb.create("create_fail_a", LDBType.GSet);
    }

    @Test
    public void testUpdateOk() throws IOException, KeyAlreadyExistsException, KeyNotFoundException {
        List<Object> operation = new ArrayList<>();
        operation.add("add");
        operation.add("a");
        ldb.create("update_ok", LDBType.GSet);
        ldb.update("update_ok", operation);
    }

    @Test(expected = KeyNotFoundException.class)
    public void testUpdateFail() throws IOException, KeyNotFoundException {
        ldb.update("update_fail", null);
    }
}
