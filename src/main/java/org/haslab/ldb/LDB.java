package org.haslab.ldb;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.openmbean.KeyAlreadyExistsException;
import org.haslab.ldb.connection.LDBConnection;
import org.haslab.ldb.connection.LDBReply;
import static org.haslab.ldb.connection.LDBReplyStatus.KEY_NOT_FOUND;
import static org.haslab.ldb.connection.LDBReplyStatus.UNKNOWN;
import org.haslab.ldb.connection.LDBRequest;
import org.haslab.ldb.exceptions.KeyNotFoundException;
import org.haslab.ldb.exceptions.LDBException;
import org.haslab.ldb.objects.operations.Operation;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class LDB implements Closeable {

    private static final Logger LOGGER = Logger.getLogger(LDB.class.getName());
    private static final ThreadLocal<LDBConnection> CONNECTIONS = new ThreadLocal<LDBConnection>();

    public LDB() {
        try {
            LDBConnection connection = new LDBConnection();
            setConnection(connection);
        } catch (IOException ex) {
            throw new LDBException("Error connecting to the database. " + ex.getMessage());
        }
    }

    private static LDBConnection getConnection() {
        LDBConnection connection = CONNECTIONS.get();

        if (connection == null) {
            throw new LDBException("The database instance is not set in the current thread.");
        }

        return connection;
    }

    private static void setConnection(LDBConnection connection) {
        CONNECTIONS.set(connection);
    }

    public static void create(String key, LDBType type) throws KeyAlreadyExistsException {
        try {
            LDBRequest request = new LDBRequest();
            request.setMethod("create");
            request.setKey(key);
            request.setType(type.getType());
            getConnection().request(request);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public static LDBReply query(String key, LDBType type) {
        LDBReply reply = null;

        try {
            LDBRequest request = new LDBRequest();
            request.setMethod("query");
            request.setKey(key);
            request.setType(type.getType());
            reply = getConnection().request(request);

            if (reply.getCode() == KEY_NOT_FOUND.getStatusCode()) {
                throw new KeyNotFoundException();
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (KeyNotFoundException ex) {
            LOGGER.log(Level.INFO, null, ex);
        }

        return reply;
    }

    public static LDBReply update(String key, LDBType type, Operation operation) {
        LDBReply reply = null;

        try {
            LDBRequest request = new LDBRequest();
            request.setMethod("update");
            request.setKey(key);
            request.setType(type.getType());
            request.setOperation(operation);
            reply = getConnection().request(request);

            if (reply.getCode() == KEY_NOT_FOUND.getStatusCode()) {
                throw new KeyNotFoundException();
            }

            if (reply.getCode() == UNKNOWN.getStatusCode()) {
                throw new UnsupportedOperationException();
            }

            return reply;

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (KeyNotFoundException ex) {
            LOGGER.log(Level.INFO, null, ex);
        }

        return reply;
    }

    @Override
    public void close() throws IOException {
        LDBConnection connection = getConnection();
        setConnection(null);
        connection.close();
    }

}
