package org.haslab.ldb;

import org.haslab.ldb.objects.LDBObject;
import java.io.BufferedReader;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.haslab.ldb.exceptions.KeyAlreadyExistsException;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public class LDB {

    private static final String IP = "127.0.0.1";
    private static final Integer PORT = 6717;

    private final Socket socket;

    public LDB() throws UnknownHostException, IOException {
        this.socket = new Socket(IP, PORT);
    }

    public LDB(String ip, Integer port) throws UnknownHostException, IOException {
        this.socket = new Socket(ip, port);
    }

    public LDBObject create(String key, LDBType type) throws IOException, KeyAlreadyExistsException {
        LDBMessage message = new LDBMessage();
        message.setMethod("create");
        message.setKey(key);
        message.setType(type.getType());
        send(message);
        processCreate();

        return null;
    }

    private void send(LDBMessage message) throws IOException {
        String json = JSONManager.toJSON(message);
        System.out.println(json);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(json);
    }

    private LDBResponse receive() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String json = in.readLine();
        return (LDBResponse) JSONManager.fromJSON(json, LDBResponse.class);
    }

    private void processCreate() throws KeyAlreadyExistsException, IOException {
        LDBResponse response = receive();
        if(response.getStatus() == 1) {
            throw new KeyAlreadyExistsException();
        }
    }

    private class LDBResponse {

        private Integer status;

        public LDBResponse() {
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
    }

    private class LDBMessage {

        private String method;
        private String key;
        private String type;

        public LDBMessage() {
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
