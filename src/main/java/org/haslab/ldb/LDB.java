package org.haslab.ldb;

import org.haslab.ldb.objects.LDBObject;
import java.io.BufferedReader;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import org.haslab.ldb.connection.LDBConnection;
import org.haslab.ldb.connection.LDBReply;
import org.haslab.ldb.connection.LDBRequest;
import org.haslab.ldb.exceptions.KeyAlreadyExistsException;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class LDB {

    private final LDBConnection connection;

    public LDB() throws UnknownHostException, IOException {
        this.connection = new LDBConnection();
    }

    public LDB(String ip, Integer port) throws UnknownHostException, IOException {
        this.connection = new LDBConnection(ip, port);
    }

    public void create(String key, LDBType type) throws IOException, KeyAlreadyExistsException {
        LDBRequest request = new LDBRequest();
        request.setMethod("create");
        request.setKey(key);
        request.setType(type.getType());
        connection.send(request);

        LDBReply reply = connection.receive();

        if (reply.getStatus() == 1) {
            throw new KeyAlreadyExistsException();
        }
    }
}
