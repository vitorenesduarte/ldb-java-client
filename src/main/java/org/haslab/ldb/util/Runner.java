package org.haslab.ldb.util;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class Runner {
    
    public static void run(int N, Runnable R) throws InterruptedException {
        Thread[] ts = new Thread[N];
        for (int i = 0; i < N; i++) {
            ts[i] = new Thread(R);
        }

        for (int i = 0; i < N; i++) {
            ts[i].start();
        }

        for (int i = 0; i < N; i++) {
            ts[i].join();
        }
    }
}
