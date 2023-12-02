package edu.hw8.task1;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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

    private static Server server;

    @BeforeAll
    static void startServer() {
        server = new Server();
        CountDownLatch serverStartLatch = new CountDownLatch(1);

        Thread thread = new Thread(() -> {
            server.runWithLatch(serverStartLatch);
        });

        thread.start();
        try {
            serverStartLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }

    @Test
    void testServerClientConnection() throws InterruptedException {

        Client client = new Client();
        client.connect();
        String response = client.sendAndGet("личности");

        assertEquals(
            "Не переходи на личности там, где их нет.",
            response
        );

        client.disconnect();
    }

    @Test
    void testServerConnectFiveClients() throws InterruptedException {

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
    }

    @Test
    void testServerConnectSixClients() throws InterruptedException {

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
    }
}
