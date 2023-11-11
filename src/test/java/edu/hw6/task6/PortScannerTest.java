package edu.hw6.task6;

import org.junit.jupiter.api.Test;
import java.util.List;

public class PortScannerTest {

    @Test
    void testPortScanner() {

        PortScanner portScanner = new PortScanner();

        //var portInfoList = portScanner.scanAllPorts(); // Heavy method

        //PortScanner.logPorts(portInfoList);

        PortScanner.logPorts(
            List.of(
                portScanner.scanPort(58935)
            )
        );
    }
}
