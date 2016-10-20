package org.haslab.ldb;

import java.net.UnknownHostException;
import java.io.IOException;
import java.util.List;
import org.haslab.ldb.connection.LDBConnection;
import org.haslab.ldb.connection.LDBReply;
import static org.haslab.ldb.connection.LDBReplyStatus.KEY_ALREADY_EXISTS;
import static org.haslab.ldb.connection.LDBReplyStatus.KEY_NOT_FOUND;
import static org.haslab.ldb.connection.LDBReplyStatus.UNKNOWN;
import org.haslab.ldb.connection.LDBRequest;
import org.haslab.ldb.exceptions.KeyAlreadyExistsException;
import org.haslab.ldb.exceptions.KeyNotFoundException;

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
        LDBReply reply = this.connection.request(request);

        if (reply.getStatusCode() == KEY_ALREADY_EXISTS.getStatusCode()) {
            throw new KeyAlreadyExistsException();
        }
    }

    public void query() {

    }

    void update(String key, List<Object> operation) throws IOException, KeyNotFoundException {
        LDBRequest request = new LDBRequest();
        request.setMethod("update");
        request.setKey(key);
        request.setOperation(operation);
        LDBReply reply = this.connection.request(request);

        if (reply.getStatusCode() == KEY_NOT_FOUND.getStatusCode()) {
            throw new KeyNotFoundException();
        }

        if (reply.getStatusCode() == UNKNOWN.getStatusCode()) {
            throw new UnsupportedOperationException();
        }
    }

    public void close() throws IOException {
        this.connection.close();
    }

}
