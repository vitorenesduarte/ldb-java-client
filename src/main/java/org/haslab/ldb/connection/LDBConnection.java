package org.haslab.ldb.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;
import static org.haslab.ldb.connection.LDBReplyStatus.INVALID;
import org.haslab.ldb.util.JSONManager;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class LDBConnection {

    private static final Logger LOGGER = Logger.getLogger(LDBConnection.class.getName());

    private static final String IP = "127.0.0.1";
    private static final Integer PORT = 6717;

    private final BufferedReader in;
    private final PrintWriter out;

    public LDBConnection() throws IOException {
        Socket socket = new Socket(IP, PORT);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public LDBConnection(String ip, Integer port) throws IOException {
        Socket socket = new Socket(IP, PORT);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public LDBReply request(LDBRequest request) throws IOException {
        String jsonRequest = JSONManager.toJSON(request);
        this.out.println(jsonRequest);
        String jsonReply = this.in.readLine();
        LDBReply reply = (LDBReply) JSONManager.fromJSON(jsonReply, LDBReply.class);

        if (reply.getCode() == INVALID.getStatusCode()) {
            throw new UnsupportedOperationException();
        }

        return reply;
    }

    public void close() throws IOException {
        this.in.close();
        this.out.close();
    }
}
