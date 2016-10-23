package org.haslab.ldb.objects;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.haslab.ldb.LDB;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class GSetTest {

    public GSetTest() {
    }

    @Test
    public void singleThreadedTest() throws IOException {
        try (LDB ldb = new LDB()) {
            GSet gset = new GSet("gset");
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
}
