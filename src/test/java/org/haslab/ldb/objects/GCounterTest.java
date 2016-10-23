package org.haslab.ldb.objects;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.haslab.ldb.LDB;
import org.haslab.ldb.util.Runner;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class GCounterTest {

    private static final Logger LOGGER = Logger.getLogger(GCounterTest.class.getName());
    private static final int N = 5;
    private static final int EVENTS = 5;
    private static final String GCOUNTER_ID_1 = "gcounter_1";
    private static final String GCOUNTER_ID_2 = "gcounter_2";

    public GCounterTest() {
    }

    @Test
    public void singleThreadedTest() throws IOException {
        try (LDB ldb = new LDB()) {
            GCounter gcounter = new GCounter(GCOUNTER_ID_1);
            for (int i = 0; i < EVENTS; i++) {
                gcounter.increment();
            }

            int expected = EVENTS;
            int value = gcounter.value();
            assertEquals(expected, value);
        }
    }

    @Test
    public void multiThreadedTest() throws InterruptedException, IOException {
        Runnable R = new Runnable() {
            @Override
            public void run() {
                try (LDB ldb = new LDB()) {
                    GCounter gcounter = new GCounter(GCOUNTER_ID_2);
                    for (int i = 0; i < EVENTS; i++) {
                        gcounter.increment();
                    }
                } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }
        };

        Runner.run(N, R);

        try (LDB ldb = new LDB()) {
            GCounter gcounter = new GCounter(GCOUNTER_ID_2);
            int expected = EVENTS * N;
            int value = gcounter.value();
            assertEquals(expected, value);
        }
    }

}
