package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final int PORT = 8080;
    public static final String HOST = "localhost";
    private static final int MAX_CONNECTIONS = 5;
    private static final String[] QUOTES = {
        "Не переходи на личности там, где их нет.",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами.",
        "Все люди ведут себя некорректно, если их достаточно долго подождать.",
        "Когда хочется сказать что-то обидное, старайся сделать это так, чтобы это было смешно.",
        "Лучше быть злым, чем скучным.",
        "Многие люди считают себя неприемлемыми, потому что они не знают, как стать приемлемыми."
    };
    private static final String FAIL_MESSAGE = "Sorry, I don't have a quote for that keyword.";

    private static AtomicInteger connectedClients = new AtomicInteger(0);
    private boolean isStopped = false;

    public void runWithLatch(CountDownLatch startLatch) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            ExecutorService threadPool = Executors.newFixedThreadPool(MAX_CONNECTIONS);
            startLatch.countDown();

            while (!isStopped) {
                if (connectedClients.get() >= MAX_CONNECTIONS) {
                    LOGGER.info("Server is overloaded. Can't connect new client.");
                    continue;
                }

                Socket clientSocket = serverSocket.accept();
                threadPool.execute(new ClientHandler(clientSocket));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        isStopped = true;
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                connectedClients.incrementAndGet();

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    LOGGER.info("Received from client: {}", inputLine);
                    String response = findQuote(inputLine);
                    out.println(response);
                }

                connectedClients.decrementAndGet();

            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }

        private String findQuote(String inputLine) {
            for (String quote : QUOTES) {
                if (Arrays.stream(quote.toLowerCase().split(" "))
                    .anyMatch(word -> inputLine.toLowerCase().equals(word))) {
                    return quote;
                }
            }
            return FAIL_MESSAGE;
        }
    }
}
