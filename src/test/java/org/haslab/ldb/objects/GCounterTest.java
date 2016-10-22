/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.haslab.ldb.objects;

import org.haslab.ldb.LDB;
import org.haslab.ldb.LDBType;
import org.haslab.ldb.exceptions.KeyAlreadyExistsException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class GCounterTest {

    public GCounterTest() {
    }

    @Test
    public void testGCounter() throws KeyAlreadyExistsException {
        GCounter gcounter = (GCounter) LDB.create("gcounter", LDBType.GCOUNTER);
        gcounter.increment();
        gcounter.increment();
        gcounter.increment();

        int expected = 3;
        int value = gcounter.value();

        assertEquals(expected, value);
    }

}
