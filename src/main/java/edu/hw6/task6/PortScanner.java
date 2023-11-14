package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PortScanner {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int PORT_NUMBER = 49151;
    private static final String EMPTY_STRING = "";

    public PortScanner() {
    }

    public List<PortInfo> scanAllPorts() {

        List<PortInfo> portInfoList = new ArrayList<>();

        for (int port = 0; port <= PORT_NUMBER; port++) {
            portInfoList.add(scanPort(port));
        }

        return portInfoList;
    }

    public PortInfo scanPort(int port) {

        PortInfo.Status tcpStatus;
        PortInfo.Status udpStatus;
        String tcpService = EMPTY_STRING;
        String udpService = EMPTY_STRING;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.close();
            tcpStatus = PortInfo.Status.FREE;

        } catch (IOException e) {
            tcpStatus = PortInfo.Status.OCCUPIED;
            tcpService = e.getMessage();
        }

        try {
            DatagramSocket datagramSocket = new DatagramSocket(port);
            datagramSocket.close();
            udpStatus = PortInfo.Status.FREE;

        } catch (IOException e) {
            udpStatus = PortInfo.Status.OCCUPIED;
            udpService = e.getMessage();
        }

        return PortInfo.of(port, tcpStatus, tcpService, udpStatus, udpService);
    }

    public static void logPorts(List<PortInfo> ports) {
        ports.forEach(portInfo -> LOGGER.info(portInfo.toString()));
    }
}
