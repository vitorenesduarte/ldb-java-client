package org.haslab.ldb.objects;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import org.haslab.ldb.LDB;
import org.haslab.ldb.object.MVMap;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class MVMapTest {

    private static final String MVMAP_ID = "mvmap";

    public MVMapTest() {
    }

    @Test
    public void singleThreadedTest() throws IOException {
        try (LDB ldb = new LDB()) {
            MVMap mvmap = new MVMap(MVMAP_ID);
            mvmap.set("a", "foo");
            mvmap.set("b", "b_value");
            mvmap.set("a", "a_value");
            mvmap.set("c", "c_value");

            Map<String, String> value = mvmap.value();
            assertEquals("a_value", value.get("a"));
            assertEquals("b_value", value.get("b"));
            assertEquals("c_value", value.get("c"));
        }
    }
}
