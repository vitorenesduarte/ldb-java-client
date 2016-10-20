package org.haslab.ldb;

import java.net.UnknownHostException;
import java.io.IOException;
import org.haslab.ldb.connection.LDBConnection;
import org.haslab.ldb.connection.LDBReply;
import static org.haslab.ldb.connection.LDBReplyStatus.KEY_ALREADY_EXISTS;
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
        LDBReply reply = getConnection().request(request);

        if (reply.getStatusCode() == KEY_ALREADY_EXISTS.getStatusCode()) {
            throw new KeyAlreadyExistsException();
        }
    }

    protected LDBConnection getConnection() {
        return this.connection;
    }
}
