package org.haslab.ldb;

import java.io.IOException;
import java.util.logging.Logger;
import org.haslab.ldb.connection.LDBConnection;
import org.haslab.ldb.connection.LDBReply;
import static org.haslab.ldb.connection.LDBReplyStatus.KEY_ALREADY_EXISTS;
import static org.haslab.ldb.connection.LDBReplyStatus.KEY_NOT_FOUND;
import static org.haslab.ldb.connection.LDBReplyStatus.UNKNOWN;
import org.haslab.ldb.connection.LDBRequest;
import org.haslab.ldb.exceptions.KeyAlreadyExistsException;
import org.haslab.ldb.exceptions.KeyNotFoundException;
import org.haslab.ldb.objects.CRDT;
import org.haslab.ldb.objects.operations.Operation;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class LDB {

    private static LDBConnection connection;
    private static final Logger LOGGER = Logger.getLogger(LDB.class.getName());

    private static void initConnection() throws IOException {
        if (connection == null) {
            LDB.connection = new LDBConnection();
        }
    }

    public synchronized static CRDT create(String key, LDBType type) throws KeyAlreadyExistsException {
        try {
            initConnection();
            LDBRequest request = new LDBRequest();
            request.setMethod("create");
            request.setKey(key);
            request.setType(type.getType());
            LDBReply reply = LDB.connection.request(request);

            if (reply.getCode() == KEY_ALREADY_EXISTS.getStatusCode()) {
                throw new KeyAlreadyExistsException();
            } else {
                return type.create(key);
            }

        } catch (IOException ex) {
            LOGGER.severe(ex.getMessage());
            return null;
        }
    }

    public synchronized static LDBReply query(String key, LDBType type) {
        LDBReply reply = null;

        try {
            initConnection();
            LDBRequest request = new LDBRequest();
            request.setMethod("query");
            request.setKey(key);
            request.setType(type.getType());
            reply = LDB.connection.request(request);

            if (reply.getCode() == KEY_NOT_FOUND.getStatusCode()) {
                throw new KeyNotFoundException();
            }
        } catch (IOException ex) {
            LOGGER.severe(ex.getMessage());
        } catch (KeyNotFoundException ex) {
            LOGGER.info(ex.getMessage());
        }

        return reply;
    }

    public synchronized static LDBReply update(String key, LDBType type, Operation operation) {
        LDBReply reply = null;

        try {
            initConnection();
            LDBRequest request = new LDBRequest();
            request.setMethod("update");
            request.setKey(key);
            request.setType(type.getType());
            request.setOperation(operation);
            reply = LDB.connection.request(request);

            if (reply.getCode() == KEY_NOT_FOUND.getStatusCode()) {
                throw new KeyNotFoundException();
            }

            if (reply.getCode() == UNKNOWN.getStatusCode()) {
                throw new UnsupportedOperationException();
            }

            return reply;

        } catch (IOException ex) {
            LOGGER.severe(ex.getMessage());
        } catch (KeyNotFoundException ex) {
            LOGGER.info(ex.getMessage());
        }

        return reply;
    }

    public void close() throws IOException {
        LDB.connection.close();
    }

}
