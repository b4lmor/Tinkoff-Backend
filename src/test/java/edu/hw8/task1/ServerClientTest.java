package edu.hw8.task1;

import edu.hw8.task1.Client;
import edu.hw8.task1.Server;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerClientTest {

    private static final List<Client> FIVE_CLIENTS
        = List.of(
        new Client(),
        new Client(),
        new Client(),
        new Client(),
        new Client()
    );
    private static final List<Client> SIX_CLIENTS
        = List.of(
        new Client(),
        new Client(),
        new Client(),
        new Client(),
        new Client(),
        new Client()
    );

    @Test
    void testServerClientConnection() throws InterruptedException {

        CountDownLatch serverStartLatch = new CountDownLatch(1);

        Server server = new Server();

        Thread serverThread = new Thread(() -> {
            server.runWithLatch(serverStartLatch);
        });

        serverThread.start();
        serverStartLatch.await();

        Client client = new Client();
        client.connect();
        String response = client.sendAndGet("личности");

        assertEquals(
            "Не переходи на личности там, где их нет.",
            response
        );

        client.disconnect();
        server.stop();
    }

    @Test
    void testServerConnectFiveClients() throws InterruptedException {

        CountDownLatch serverStartLatch = new CountDownLatch(1);

        Server server = new Server();

        Thread serverThread = new Thread(() -> {
            server.runWithLatch(serverStartLatch);
        });

        serverThread.start();
        serverStartLatch.await();

        assertDoesNotThrow(() -> {
            FIVE_CLIENTS.forEach(Client::connect);

            FIVE_CLIENTS.forEach(client -> {
                String response = client.sendAndGet("личности");

                assertEquals(
                    "Не переходи на личности там, где их нет.",
                    response
                );
            });

            FIVE_CLIENTS.forEach(Client::disconnect);
        });

        server.stop();
    }

    @Test
    void testServerConnectSixClients() throws InterruptedException {

        CountDownLatch serverStartLatch = new CountDownLatch(1);

        Server server = new Server();

        Thread serverThread = new Thread(() -> {
            server.runWithLatch(serverStartLatch);
        });

        serverThread.start();
        serverStartLatch.await();

        assertDoesNotThrow(() -> {
            SIX_CLIENTS.forEach(Client::connect);

            SIX_CLIENTS.forEach(client -> {
                String response = client.sendAndGet("личности");

                assertEquals(
                    "Не переходи на личности там, где их нет.",
                    response
                );
            });

            SIX_CLIENTS.forEach(Client::disconnect);
        });

        server.stop();
    }
}
