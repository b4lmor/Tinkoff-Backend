package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw8.task1.Server.HOST;
import static edu.hw8.task1.Server.PORT;

public class Client {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String EMPTY_RESPONSE = "";
    private Socket socket;

    public void connect() {
        try {
            socket = new Socket(HOST, PORT);

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public String sendAndGet(String word) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            out.println(word);
            return in.readLine();

        } catch (UnknownHostException e) {
            LOGGER.error("Unknown host: {}", HOST);

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        return EMPTY_RESPONSE;
    }

    public void disconnect() {
        try {
            socket.close();

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
