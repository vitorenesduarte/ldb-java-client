package org.haslab.ldb;

import java.io.IOException;
import org.haslab.ldb.connection.LDBConnection;
import org.haslab.ldb.exceptions.KeyAlreadyExistsException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
}
