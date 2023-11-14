package edu.hw6.task6;

public record PortInfo(
    ProtocolInfo tcpProtocol,
    ProtocolInfo udpProtocol,
    int port
) {

    private static final String SPACE = "   ";
    private static final String SEPARATOR = "|";

    @Override
    public String toString() {

        String tcpInfo = tcpProtocol.protocol.toString()
            + SPACE
            + tcpProtocol.status.toString();

        if (tcpProtocol.service != null && !tcpProtocol.service.isEmpty()) {
            tcpInfo += tcpProtocol.service;
        }

        String udpInfo = udpProtocol.protocol.toString()
            + SPACE
            + udpProtocol.status.toString();

        if (udpProtocol.service != null && !udpProtocol.service.isEmpty()) {
            udpInfo += tcpProtocol.service;
        }

        return port
            + SPACE
            + SEPARATOR
            + SPACE
            + tcpInfo
            + SPACE
            + SEPARATOR
            + SPACE
            + udpInfo;
    }

    public static PortInfo of(
        int port,
        Status tcpStatus, String tcpService,
        Status udpStatus, String udpService
    ) {

        return new PortInfo(
            new ProtocolInfo(Protocol.TCP, tcpStatus, tcpService),
            new ProtocolInfo(Protocol.UDP, udpStatus, udpService),
            port
        );
    }

    private record ProtocolInfo(
        Protocol protocol,
        Status status,
        String service
    ) {
    }

    public enum Status {
        OCCUPIED,
        FREE
    }

    public enum Protocol {
        TCP,
        UDP
    }

}
