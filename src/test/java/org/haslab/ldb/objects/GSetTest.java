package org.haslab.ldb.objects;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.haslab.ldb.LDB;
import org.haslab.ldb.object.GSet;
import org.haslab.ldb.util.Runner;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class GSetTest {

    private static final Logger LOGGER = Logger.getLogger(GSetTest.class.getName());
    private static final int N = 5;
    private static final int EVENTS = 5;
    private static final String GSET_ID_1 = "gset_1";
    private static final String GSET_ID_2 = "gset_2";

    public GSetTest() {
    }

    @Test
    public void singleThreadedTest() throws IOException {
        try (LDB ldb = new LDB()) {
            GSet gset = new GSet(GSET_ID_1);
            gset.add("a");
            gset.add("b");
            gset.add("a");
            gset.add("c");

            Set<String> expected = new HashSet<>();
            expected.add("a");
            expected.add("b");
            expected.add("c");
            Set<String> value = gset.value();
            // assert equals
            assertTrue(value.containsAll(expected) && expected.containsAll(value));
        }
    }

    @Test
    public void multiThreadedTest() throws InterruptedException, IOException {
        Runnable R = new Runnable() {
            @Override
            public void run() {
                try (LDB ldb = new LDB()) {
                    GSet gset = new GSet(GSET_ID_2);
                    for (int i = 0; i < EVENTS; i++) {
                        String random = Thread.currentThread().getName() + "_" + i;
                        gset.add(random);
                    }
                } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }
        };

        Runner.run(N, R);

        try (LDB ldb = new LDB()) {
            GSet gset = new GSet(GSET_ID_2);
            int expected = EVENTS * N;
            int value = gset.value().size();
            assertEquals(expected, value);
        }
    }
}
