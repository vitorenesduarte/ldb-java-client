package org.haslab.ldb.connection;

import java.io.BufferedReader;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.haslab.ldb.util.JSONManager;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class LDBConnection {

    private static final String IP = "127.0.0.1";
    private static final Integer PORT = 6717;

    private final BufferedReader in;
    private final PrintWriter out;

    public LDBConnection() throws UnknownHostException, IOException {
        Socket socket = new Socket(IP, PORT);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public LDBConnection(String ip, Integer port) throws UnknownHostException, IOException {
        Socket socket = new Socket(IP, PORT);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void send(LDBRequest request) {
        String json = JSONManager.toJSON(request);
        this.out.println(json);
    }

    public LDBReply receive() throws IOException {
        String json = this.in.readLine();
        return (LDBReply) JSONManager.fromJSON(json, LDBReply.class);
    }
}
